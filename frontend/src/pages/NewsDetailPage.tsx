import SimilarNewsList from "@/components/NewsDetail/SimilarNewsList";

export default function NewsDetailPage() {
  return (
    <>
      {/* 카테고리 */}
      <div className="ml-[300px] flex gap-3 text-lg text-gray">
        <div>#형사</div>
        <div>#사기</div>
      </div>
      <div className="mx-[300px] flex gap-5">
        {/* 메인 뉴스 */}
        <div className="flex w-8/12 flex-col">
          {/* 뉴스 제목 */}
          <p className="font-TitleMedium text-3xl">
            뉴스 제목 뉴스 제목 뉴스 제목 뉴스 제목
          </p>

          <div className="flex w-full justify-between">
            {/* 날짜 */}
            <p className="text-lightgray">2024. 03. 08 오전 10:31</p>
            {/* 기자 이름 */}
            <p className="text-gray">김민준 기자</p>
          </div>

          <hr className="text my-2" />

          <div className="my-6 h-64 w-full bg-lightgray" />

          <p className="break-all">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Facilis
            doloribus tenetur commodi vitae qui accusamus odio modi ratione
            vero, delectus tempora libero dolore consequatur quaerat eius eaque.
            Blanditiis, error eius! Rem blanditiis ex nulla animi odio a dolorum
            vel consequatur consequuntur? Est reiciendis dolorum fugit expedita
            magni? Laudantium dolore unde perspiciatis quod, officia ea, placeat
            saepe aperiam aspernatur harum quae? Expedita eligendi incidunt
            alias consectetur deleniti corrupti deserunt eos maiores blanditiis
            quo, at facere rerum dolor suscipit consequuntur possimus fuga ipsum
            quos! Dicta libero aut beatae perferendis non! Ut, reprehenderit.
            Hic voluptates eveniet iste officiis rerum illum fugiat odio quidem
            maxime, consectetur distinctio veritatis non adipisci molestiae ex
            facere dolorem, aut laborum quis dicta sit esse consequuntur? Alias,
            eligendi est! Doloremque, necessitatibus perspiciatis. Veritatis
            perspiciatis numquam et asperiores tempore inventore tempora eos
            magni, explicabo odit aperiam doloribus exercitationem placeat.
            Molestiae eos beatae ducimus ipsam nisi at temporibus consequatur
            enim et? Esse repellat aliquid, cupiditate minus nemo tenetur
            voluptatem ipsa labore tempore impedit suscipit perspiciatis! A
            magni saepe voluptas sunt. Dolorem earum perferendis molestiae
            asperiores accusantium explicabo ut, voluptatibus sit quis? Deleniti
            excepturi aut velit incidunt ea, voluptatum tempora recusandae vero
            cum eveniet doloremque porro alias sequi tempore eos expedita illum
            dicta. Magnam labore enim deleniti aspernatur fuga soluta ipsum
            animi? Provident deleniti qui delectus architecto. Nemo alias
            cupiditate id quia voluptates molestias possimus magni libero
            facilis necessitatibus, ea, unde recusandae repellat eius. Non
            voluptatem veniam minus amet repellendus? Ipsa, delectus. Laboriosam
            minus labore veniam, blanditiis officiis similique eveniet quaerat
            nihil nesciunt vitae consequatur maxime. Voluptas, facilis.
            Voluptas, aliquam tempora sint consectetur quisquam, dolorem,
            incidunt nihil cumque perspiciatis esse velit ratione? Non
            reiciendis suscipit amet esse tenetur necessitatibus obcaecati
            beatae est, perferendis odio quis iste odit laboriosam quidem a
            doloribus id nesciunt numquam fugit dolore maxime ipsa. Blanditiis
            neque ex sit?
          </p>
        </div>

        <div className="w-[1px] bg-slate-100" />

        {/* 비슷한 카테고리 기사 */}
        <div className="sticky top-[120px] h-fit w-4/12">
          <div className="mb-6 font-TitleMedium">
            <p className="text-3xl text-navy">비슷한 카테고리 기사</p>
            <p className="text-xl">도 함께 보세요</p>
          </div>

          <SimilarNewsList />
        </div>
      </div>
    </>
  );
}
