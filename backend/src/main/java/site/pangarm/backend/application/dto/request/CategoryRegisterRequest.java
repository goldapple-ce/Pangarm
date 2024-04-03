package site.pangarm.backend.application.dto.request;
import site.pangarm.backend.domain.category.entity.Category;


public record CategoryRegisterRequest(String name) {

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .build();
    }

}
