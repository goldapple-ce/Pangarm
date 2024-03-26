import { http, HttpResponse } from "msw";

export const newsHandlers = [
  // 판례 검색
  http.post("/news", async () => {
    return HttpResponse.json({
      data: {
        content: [
          {
            id: "K6WFdI4B4w37sxqLPcwO",
            newsLink: "https://example.com/news/123",
            title: "News 3",
            content:
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien vitae velit lacinia lacinia non eu justo. Nulla facilisi. Nullam quis bibendum velit. Aliquam erat volutpat. Donec vehicula efficitur purus, eu tincidunt mauris rhoncus et.",
            imageUrl: "https://example.com/images/news/123.jpg",
            author: "John Doe",
            createdAt: "2024-03-25",
            categoryList: ["법률 개정", "아동 복지"],
          },
          {
            id: "LKWZdI4B4w37sxqL_MzI",
            newsLink: "https://example.com/news/123",
            title: "News 4",
            content:
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien vitae velit lacinia lacinia non eu justo. Nulla facilisi. Nullam quis bibendum velit. Aliquam erat volutpat. Donec vehicula efficitur purus, eu tincidunt mauris rhoncus et.",
            imageUrl: "https://example.com/images/news/123.jpg",
            author: "John Doe",
            createdAt: "2024-03-25",
            categoryList: ["법률 개정", "아동 복지"],
          },
          {
            id: "LKWZdI4B4w37sxqL_MzI",
            newsLink: "https://example.com/news/123",
            title: "News 4",
            content:
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien vitae velit lacinia lacinia non eu justo. Nulla facilisi. Nullam quis bibendum velit. Aliquam erat volutpat. Donec vehicula efficitur purus, eu tincidunt mauris rhoncus et.",
            imageUrl: "https://example.com/images/news/123.jpg",
            author: "John Doe",
            createdAt: "2024-03-25",
            categoryList: ["법률 개정", "아동 복지"],
          },
          {
            id: "LKWZdI4B4w37sxqL_MzI",
            newsLink: "https://example.com/news/123",
            title: "News 4",
            content:
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien vitae velit lacinia lacinia non eu justo. Nulla facilisi. Nullam quis bibendum velit. Aliquam erat volutpat. Donec vehicula efficitur purus, eu tincidunt mauris rhoncus et.",
            imageUrl: "https://example.com/images/news/123.jpg",
            author: "John Doe",
            createdAt: "2024-03-25",
            categoryList: ["법률 개정", "아동 복지"],
          },
          {
            id: "LKWZdI4B4w37sxqL_MzI",
            newsLink: "https://example.com/news/123",
            title: "News 4",
            content:
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et sapien vitae velit lacinia lacinia non eu justo. Nulla facilisi. Nullam quis bibendum velit. Aliquam erat volutpat. Donec vehicula efficitur purus, eu tincidunt mauris rhoncus et.",
            imageUrl: "https://example.com/images/news/123.jpg",
            author: "John Doe",
            createdAt: "2024-03-25",
            categoryList: ["법률 개정", "아동 복지"],
          },
        ],
        pageable: {
          pageNumber: 0,
          pageSize: 3,
          sort: {
            empty: true,
            sorted: false,
            unsorted: true,
          },
          offset: 0,
          paged: true,
          unpaged: false,
        },
        last: true,
        totalPages: 1,
        totalElements: 5,
        size: 5,
        number: 0,
        sort: {
          empty: true,
          sorted: false,
          unsorted: true,
        },
        first: true,
        numberOfElements: 5,
        empty: false,
      },
      message: "모든 뉴스를 조회했습니다.",
    });
  }),
];
