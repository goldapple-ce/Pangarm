import { useQuery } from "@tanstack/react-query";
import Slider from "react-slick";
import { MdArrowBackIos, MdArrowForwardIos } from "react-icons/md";

import { getNewsList } from "@/services/newsService";

import LatestNewsListItem from "@/components/News/LatestNewsListItem";

export default function LatestNewsList() {
  const size = 20;

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["latestNewsList"],
    queryFn: () => getNewsList(0, size),
  });

  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    autoplay: true,
    slidesToShow: 2,
    slidesToScroll: 2,
    rows: 2,
    arrows: true,
    nextArrow: (
      <MdArrowForwardIos
        color="navy"
      />
    ),
    prevArrow: (
      <MdArrowBackIos
        color="navy"
      />
    ),
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }
  if (isError) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <Slider {...settings}>
      {data?.data.data.map(
        ({
          id,
          title,
          content,
          imageUrl,
          createdAt,
        }: {
          id: string;
          title: string;
          content: string;
          imageUrl: string;
          createdAt: string;
        }) => (
          <LatestNewsListItem
            key={id}
            id={id}
            title={title}
            content={content}
            imageUrl={imageUrl}
            createdAt={createdAt}
          />
        ),
      )}
    </Slider>
  );
}
