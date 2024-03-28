import Slider from "react-slick";
import { MdArrowBackIos, MdArrowForwardIos } from "react-icons/md";

import LatestNewsListItem from "@/components/News/LatestNewsListItem";

export default function CategoryNewsList() {
  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 2,
    slidesToScroll: 2,
    rows: 2,
    arrows: true,
    nextArrow: <MdArrowForwardIos color="navy" />,
    prevArrow: <MdArrowBackIos color="navy" />
  };

  return (
    <Slider {...settings}>
      {Array.from({ length: 12 }).map((_, index) => (
        <LatestNewsListItem key={index} />
      ))}
    </Slider>
  );
}
