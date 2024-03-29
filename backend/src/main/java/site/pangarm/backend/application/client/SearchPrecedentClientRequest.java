package site.pangarm.backend.application.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SearchPrecedentClientRequest {
    private final String content;
    private final int count;

    public static SearchPrecedentClientRequest of(String content){
        return new SearchPrecedentClientRequest(content,100);
    }
}
