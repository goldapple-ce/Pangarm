import { useState } from "react";

import { useQuery } from "@tanstack/react-query";
import Slider from "react-slick";
import { MdArrowBackIos, MdArrowForwardIos } from "react-icons/md";

import { getNewsList } from "@/services/newsService";

import LatestNewsListItem from "@/components/News/LatestNewsListItem";

export default function CategoryNewsList() {
  const [page, setPage] = useState(0);
  const size = 4;

  const { data, isLoading, isError, error } = useQuery({
    queryKey: ["latestNewsList", page],
    queryFn: () => getNewsList(page, size),
  });

  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 2,
    rows: 2,
    arrows: true,
    nextArrow: (
      <MdArrowForwardIos
        color="navy"
        onClick={() => setPage((prev) => prev + 1)}
      />
    ),
    prevArrow: (
      <MdArrowBackIos
        color="navy"
        onClick={() => setPage((prev) => Math.max(1, prev - 1))}
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
