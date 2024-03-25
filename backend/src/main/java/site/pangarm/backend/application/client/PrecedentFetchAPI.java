package site.pangarm.backend.application.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;

@RequiredArgsConstructor
@Service
public class PrecedentFetchAPI {

    private final WebClient precedentClient;
    private final String PATH = "/api/search";

    public PrecedentClientResponse fetchAPI(PrecedentSearchRequest request){
        return precedentClient
                .method(HttpMethod.POST)
                .uri(PATH)
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PrecedentClientResponse.class)
                .block();
    }


}
