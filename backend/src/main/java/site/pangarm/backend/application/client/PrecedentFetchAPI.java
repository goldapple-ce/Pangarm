package site.pangarm.backend.application.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;

@RequiredArgsConstructor
@Component
public class PrecedentFetchAPI {

    private final WebClient precedentClient;
    private final String PATH = "/api/precedent/search";

    public SearchPrecedentClientResponse fetchAPI(PrecedentSearchRequest request) {
        return precedentClient
                .method(HttpMethod.POST)
                .uri(PATH)
                .bodyValue(SearchPrecedentClientRequest.of(request.getSituation()))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SearchPrecedentClientResponse.class)
                .block();
    }

    public <T> T fetchAPI(String path ,String caseNumber,Class<T> responseType) {
        return precedentClient
                .method(HttpMethod.GET)
                .uri(PATH + path + caseNumber)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }


}
