import requests
from bs4 import BeautifulSoup
import math
import re
import json
import logging
from datetime import datetime
import os
from dotenv import load_dotenv
load_dotenv()
ELASTIC_SEARCH_URL = os.environ["ELASTIC_SEARCH_URL"]


headers = {
    'Content-Type': 'application/json',
}

json_data = {
    '_class': 'site.pangarm.backend.domain.news.entity.News',
    'newsLink': 'https://asdfasdfasdf.com/news/123',
    'title': 'News 3',
    'content': 'Loremfdasdadsfasdfus et.',
    'imageUrl': 'https://example.com/images/news/123.jpg',
    'author': 'John Doe',
    'createdAt': '2024-03-25T15:00:00.000Z',
    'categoryList': [
        '법률 개정',
        '아동 복지',
    ],
}

# 로거 생성
logger = logging.getLogger(__name__)
logger.setLevel(logging.INFO)  # 로그 레벨 설정

# 핸들러 생성 및 로거에 추가
handler = logging.StreamHandler()
formatter = logging.Formatter(
    '%(asctime)s - %(name)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)
logger.addHandler(handler)


# 검색어 리스트
keyword_list = ['법률 개정', '사법 판결', '법정 소송',
                '규제 변경', '범죄 사건', '인권 보호', '법적 사례', '국제 법률']

# 한 페이지당 기사 갯수
ARTICLE_CNT_PER_PAGE = 10

# 총 얻을 기사 갯수
TOTAL_ARTICLE_CNT = 1  # 변경 가능

# 총 카운트
total_count = 0


def crawl_news():
    for keyword in keyword_list:

        crawl_news_each_keyword(keyword)  # 분기 1 : 각 키워드에 대해 분산

    logger.info(f"총 갯수: {total_count}")


def crawl_news_each_keyword(keyword):

    for i in range(math.ceil(TOTAL_ARTICLE_CNT / ARTICLE_CNT_PER_PAGE)):

        # 분기 2 : 페이지 별 뉴스 리스트(10개)를 가져도로록 분산
        crawl_news_per_page(keyword, "{}".format(i * ARTICLE_CNT_PER_PAGE))


def crawl_news_per_page(keyword, start_num):

    response = naverSearchRequest(keyword, start_num)  # HTTP GET 요청 보내기

    if response.status_code == 200:

        news_content_html_list = pick_news_content_list_from_JSON_response(
            response)

        # 여기서 크롤링 타겟의 뉴스의 링크가 튀어나온다.
        link_list_naver_news = get_link_list_by_BeautifulSoup(
            news_content_html_list)

        for link in link_list_naver_news:  # 분기 3 : 각 뉴스에 대해 크롤링 하도록 분산
            scrap_naver_news(keyword, link)

        updateCount(len(link_list_naver_news))

    else:
        logger.error('네이버 검색 페이지 요청을 받을 수 없습니다.')


def get_link_list_by_BeautifulSoup(news_content_html_list):

    link_list = []

    for content in news_content_html_list:

        # HTML 페이지를 파싱하여 BeautifulSoup 객체 생성
        soup = BeautifulSoup(content, 'html.parser')

        try:
            article_list = soup.find_all('div', {'class': 'news_area'})
            if article_list:

                for article in article_list:

                    a_tag_list = article.find(
                        'div', {'class': 'info_group'}).find_all('a')

                    if a_tag_list:

                        for a_tag in a_tag_list:  # 모든 a 태그를 순회하면서 innerText가 "네이버뉴스"인 경우 href 속성 가져오기

                            if a_tag.text.strip() == "네이버뉴스":

                                if isSuitableLink(a_tag['href']):
                                    link_list.append(a_tag['href'])

        except Exception as e:
            logger.error(f"파싱 에러 : {e}")
    return link_list


def scrap_naver_news(keyword, naverNewsLink):

    article_info = {}  # 기사 정보를 담을 리스트
    article_info['keyword'] = keyword
    article_info["naverNewsLink"] = naverNewsLink
    article_title = None
    article_content = None
    article_created_at = None
    article_img_url = None
    article_writer = None

    response = requests.get(naverNewsLink)

    if response.status_code == 200:
        naver_news_soup = BeautifulSoup(response.text, 'html.parser')

        # with open("test.html","w", encoding="utf-8") as f:
        #     f.write(naver_news_soup.prettify())

        # 뉴스 제목
        title_element = naver_news_soup.find(
            'h2', class_='media_end_head_headline')
        if title_element:
            spanTag = title_element.find('span')
            article_title = spanTag.text.strip() if spanTag else None
        else:
            logger.debug(f'뉴스의 제목을 찾을 수 없습니다. {naverNewsLink}')

        # 뉴스 내용
        article_content_element = naver_news_soup.find(
            'div', class_='newsct_article _article_body')
        if article_content_element:
            article_content = ''.join([child.text.strip(
            ) for child in article_content_element.children if child.name])  # 모든 자식 요소의 innerText를 모두 합치기
        else:
            logger.debug(f"기사 내용을 찾을 수 없습니다. {naverNewsLink}")
            return

        # 이미지 URL
        article_img_url_element = article_content_element.find('img')
        if article_img_url_element:
            article_img_url = article_img_url_element['data-src']
        else:
            article_vedio_img_url_element = naver_news_soup.find(
                'div', class_='_VOD_PLAYER_WRAP')
            if article_vedio_img_url_element:
                article_img_url = article_vedio_img_url_element['data-cover-image-url']
            else:
                logger.debug(f"이미지 또는 비디오를 찾을 수 없습니다. {naverNewsLink}")

        # 작성일시
        temp = naver_news_soup.find(
            class_='media_end_head_info_datestamp_bunch')
        article_created_at_elements = temp.find_all('span')
        if article_created_at_elements:
            article_created_at = article_created_at_elements[0].text.strip()
        else:
            logger.debug(f"작성일을 찾을 수 없습니다. {naverNewsLink}")

        # 작성자
        article_writer_element = naver_news_soup.find(
            class_='media_end_head_journalist_name')
        if (article_writer_element):
            article_writer = article_writer_element.text
        else:
            logger.debug(f"작성자을 찾을 수 없습니다. {naverNewsLink}")

    else:
        logger.warning(f"네이버뉴스 페이지를 가져오지 못했습니다. {naverNewsLink}")

    article_info["title"] = article_title
    article_info["content"] = article_content
    article_info['imgUrl'] = article_img_url
    article_info['createdAt'] = article_created_at
    article_info['writer'] = article_writer

    save_data(article_info)  # 가져온 제목과 내용을 출력 -> save

    # return article_info


def naverSearchRequest(search_query, start_page_num):
    cookies = {
        'NNB': '7XRIV2K6Z6QGK',
        'NID_AUT': 'MnPcZ0eb+sWgPDKitm71VGK7jFyd2xprWKbkI/MO/CI+YveYS8yeCfyQlRnAZHHC',
        'NID_JKL': 'vap44RM2adwXpPhN7pqukbHyi9NEa+lnam4+lCYD21s=',
        '_ga': 'GA1.2.936580697.1706165340',
        'ba.uuid': '0fbaefd1-82c9-4e4d-bd12-f0bc1bb7d7b6',
        'ASID': 'd2dd6e6c0000018dfe1ca41700000066',
        'NID_SES': 'AAABlzVc5M83Giiymy0ZyClR1UJUAmhVO4Z7MS5nm1fHJvX9SQcH2OaZnc+ksCai3Pl9W9FFXawofzTpKLFpcE9y3cu64/TKleXdutvGQTnFCG6BVxGIVX/e1a2FaJn2Ds/F2rrET67nfanjVmDSu4eeFeRw3cJIBlI7VGji14s7GhtoFtRPP9ZRktS8g927MUCKRQ8OJ87j2ODjrpQGJb8xZw/toeAtQ/OK53zFnsncIONgnSRCYFN1NW26aWxK4TMKnYVx/CwhkSVuQwvE5wAajFGUrcxlkMR+SWvuiUUTg3Ff9dvxlVG1W+kOsmRyE7oAvvimu5X3h5IQmjBSbgW1Whe/gKU8+rbuUxRIdGcUXWPdcMggltaPComJXGfCSfor/YCaucjQaM+a0rEy3uMRVMO5E79p1W4BFCz5sghI4bZQhS3Qo39FKC/v85LysO8Fywzlgb5rz+rabrlM4kwXKER6kOK1ebzKzp2k0XI+9f1Y6L+eaQ75NUEi+h8saUGn/NNYcBrznI4wW820TNivZ0D/AZ/6NqU/+CtcUXNYelVp',
        '_naver_usersession_': 'GwVIFaR/u/YvvOVMakLGGw==',
        'page_uid': 'iPRQadqo15Vsslc9YPwsssssttV-516601',
    }

    headers = {
        'authority': 's.search.naver.com',
        'accept': '*/*',
        'accept-language': 'ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7',
        # 'cookie': 'NNB=7XRIV2K6Z6QGK; NID_AUT=MnPcZ0eb+sWgPDKitm71VGK7jFyd2xprWKbkI/MO/CI+YveYS8yeCfyQlRnAZHHC; NID_JKL=vap44RM2adwXpPhN7pqukbHyi9NEa+lnam4+lCYD21s=; _ga=GA1.2.936580697.1706165340; ba.uuid=0fbaefd1-82c9-4e4d-bd12-f0bc1bb7d7b6; ASID=d2dd6e6c0000018dfe1ca41700000066; NID_SES=AAABlzVc5M83Giiymy0ZyClR1UJUAmhVO4Z7MS5nm1fHJvX9SQcH2OaZnc+ksCai3Pl9W9FFXawofzTpKLFpcE9y3cu64/TKleXdutvGQTnFCG6BVxGIVX/e1a2FaJn2Ds/F2rrET67nfanjVmDSu4eeFeRw3cJIBlI7VGji14s7GhtoFtRPP9ZRktS8g927MUCKRQ8OJ87j2ODjrpQGJb8xZw/toeAtQ/OK53zFnsncIONgnSRCYFN1NW26aWxK4TMKnYVx/CwhkSVuQwvE5wAajFGUrcxlkMR+SWvuiUUTg3Ff9dvxlVG1W+kOsmRyE7oAvvimu5X3h5IQmjBSbgW1Whe/gKU8+rbuUxRIdGcUXWPdcMggltaPComJXGfCSfor/YCaucjQaM+a0rEy3uMRVMO5E79p1W4BFCz5sghI4bZQhS3Qo39FKC/v85LysO8Fywzlgb5rz+rabrlM4kwXKER6kOK1ebzKzp2k0XI+9f1Y6L+eaQ75NUEi+h8saUGn/NNYcBrznI4wW820TNivZ0D/AZ/6NqU/+CtcUXNYelVp; _naver_usersession_=GwVIFaR/u/YvvOVMakLGGw==; page_uid=iPRQadqo15Vsslc9YPwsssssttV-516601',
        'dnt': '1',
        'referer': 'https://search.naver.com/search.naver?where=news&ie=utf8&sm=nws_hty&query=%EB%B2%95',
        'sec-ch-ua': '"Google Chrome";v="118", "Chromium";v="118", "Not=A?Brand";v="24"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"Windows"',
        'sec-fetch-dest': 'script',
        'sec-fetch-mode': 'no-cors',
        'sec-fetch-site': 'same-site',
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36',
    }

    params = {
        'ssc': 'tab.news.all',
        'where': 'news',
        'sm': 'tab_jum',
        'cluster_rank': '26',
        'field': '0',
        'is_dts': '0',
        'is_sug_officeid': '0',
        'mynews': '0',
        'nso': '%26nso%3Dso%3Ar%2Cp%3Aall%2Ca%3Aall',  # '&nso=so:r,p:all,a:all',
        'office_category': '0',
        'office_section_code': '0',
        'office_type': '0',
        'pd': '0',
        'photo': '0',
        'query': search_query,
        'service_area': '0',
        'sort': '0',
        'spq': '0',
        'start': start_page_num,
        'where': 'news_tab_api',
        '_callback': 'jQuery112407111922761923908_1709514517340',
        '_': '1709514517341'
    }

    url = 'https://s.search.naver.com/p/newssearch/search.naver'
    response = requests.get(
        url, params=params, cookies=cookies, headers=headers)
    print(response.url)
    return response


def save_data(article_info):

    categoryList = [article_info['keyword']]
    newsLink = article_info['naverNewsLink']
    title = article_info['title']
    content = article_info['content']
    imageUrl = article_info['imgUrl']
    author = article_info["writer"]
    createdAt = article_info['createdAt']
    createdAt = createdAt.replace("오후", "PM")
    createdAt = createdAt.replace("오전", "AM")
    date_time_obj = datetime.strptime(createdAt, "%Y.%m.%d. %p %I:%M")
    iso_formatted_string = date_time_obj.strftime('%Y-%m-%dT%H:%M:%S.000Z')

    # iso8601_string = date_time_obj.isoformat()

  #  print(createdAt)

    # assert True
    # print(categoryList,newsLink,title,content,imageUrl,createdAt)
    json_data['newsLink'] = newsLink
    json_data['title'] = title
    json_data['imageUrl'] = imageUrl
    json_data["author"] = author
    json_data["categoryList"] = categoryList
    json_data["content"] = content
    json_data["createdAt"] = iso_formatted_string
    # print(json_data["createdAt"])
    # assert False
    response = requests.post(
        f'{ELASTIC_SEARCH_URL}/news/_doc', headers=headers, json=json_data)


def isSuitableLink(link):
    return link.startswith("https://n.news.naver.com")


def updateCount(num):
    global total_count
    total_count += num


def pick_news_content_list_from_JSON_response(response):
    resultJson = json.loads(re.sub(
        r'^jQuery\d+_\d+\(', '', response.text).strip()[:-1])  # 역직렬화를 통해 이스케이프 자동 처리
    return resultJson["contents"]  # json의 contents 내부값만 받아옴


crawl_news()
