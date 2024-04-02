import schedule
from datetime import datetime
import requests
import os
from dotenv import load_dotenv
load_dotenv()

MAIL_SERVER_URL = os.getenv("MAIL_SERVER_URL")
ELASTIC_SEARCH_URL = os.getenv("ELASTIC_SEARCH_URL")
SERVER_URL = os.getenv("SERVER_URL")

assert MAIL_SERVER_URL not in [None, ""]
assert ELASTIC_SEARCH_URL not in [None, ""]
assert SERVER_URL not in [None, ""]


class ElasticSearch:

    def __init__(self, url):
        self.FETCH_URL = url + "/news/_search"

    def _make_json_template(self, category):
        json_data = {
            "query": {
                "bool": {
                    "must": [
                        {
                            "match": {
                                "categoryList": category
                            }
                        }
                    ]
                }
            },
            "sort": [
                {
                    "createdAt": {
                        "order": "desc"
                    }
                }
            ],
            "size": 3
        }

        return json_data

    def _get_category_news(self, category):
        json_data = self._make_json_template(category)

        response = requests.post(self.FETCH_URL, json=json_data).json()

        data = []
        if response['hits']['total']['value'] == 0:
            return data

        for news in response['hits']['hits']:
            data.append({
                "redirect_url": f"{SERVER_URL}/news/{news['_id']}",
                "title": news["_source"]["title"],
                "image_url": news["_source"]["imageUrl"],
                "created_at": news["_source"]["createdAt"],
                "content": news["_source"]["content"]
            })
        return data

    def getNewsByCategoryList(self, category_list):
        return {category: self._get_category_news(
            category) for category in category_list}
        # for category in category_list:
        #     data[category] = self._get_category_news(category)
        # return data


class Mail:
    def __init__(self, news_json, email, category_list, title) -> None:
        self.email = email
        self.category_list = category_list
        self.json_data = {
            "email": email,
            "title": title
        }
        self.news_json = news_json

    def _make_html_template(self, redirect_url, title, image_url, content):
        return f"""
        <div style="box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2); transition: 0.3s; width: calc(33.33% - 20px); margin-bottom: 20px;"
       onmouseover="this.style.boxShadow='0 8px 16px 0 rgba(0,0,0,0.2)';"
       onmouseout="this.style.boxShadow='0 4px 8px 0 rgba(0,0,0,0.2)';">
       <a href="{redirect_url}" style="color: inherit; text-decoration: none;">
    <img src="{image_url}" alt="Avatar" style="width: 100%; height: 300px;">
    <div style="padding: 2px 16px;">
      <h4><b>{title}</b></h4> 
      <p>{content[:100]}...</p> 
    </div>
    </a>
  </div>"""

    def _make_html(self):
        html = ""
        for category in self.category_list:
            news_list = self.news_json[category]
            temp = f"""<h3>{category}</h3><div style="display: flex; justify-content: space-between; width: 100%;">"""
            for news in news_list:
                temp += self._make_html_template(news["redirect_url"], news["title"],
                                                 news["image_url"], news["content"])
            temp += "</div>"

            html += temp

        return html

    def send(self):
        html = self._make_html()
        self.json_data["body"] = html
        response = requests.post(
            f"{MAIL_SERVER_URL}/api/mail/send", json=self.json_data)


def main():
    category_list = requests.get(
        f"{SERVER_URL}/api/news/category-list").json()['data']

    es = ElasticSearch(ELASTIC_SEARCH_URL)
    news_json = es.getNewsByCategoryList(category_list)

    user_list = requests.get(
        f"{SERVER_URL}/api/member/all-member-subscribe-info").json()["data"]

    now = datetime.now()
    title = now.strftime("[판가름 뉴스레터] %Y년%m월%d일 구독 뉴스를 알려드립니다.")
    for user in user_list:
        user_email = user["email"]
        user_category_list = user["categoryList"]
        if (len(user_category_list) == 0):
            continue

        mail = Mail(news_json, user_email, user_category_list, title)
        mail.send()


schedule.every().day.at("08:30:00").do(lambda: main())
# schedule.every(1).seconds.do(lambda: main())
while True:
    schedule.run_pending()
