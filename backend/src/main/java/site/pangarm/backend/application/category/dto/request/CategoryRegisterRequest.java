package site.pangarm.backend.application.category.dto.request;


import site.pangarm.backend.domain.category.Category;


public record CategoryRegisterRequest(String name) {

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .build();
    }
    public static Category of(String name) {
        return Category.builder()
                .name(name)
                .build();

    }

}
