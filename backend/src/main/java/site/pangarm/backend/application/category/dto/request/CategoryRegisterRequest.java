package site.pangarm.backend.application.category.dto.request;

import jakarta.validation.constraints.NotNull;
import site.pangarm.backend.domain.category.Category;


public record CategoryRegisterRequest(@NotNull String name) {

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .build();
    }

}
