package site.pangarm.backend.global.response.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class ApiResponse<T>{

    private final String message;
    private T data;

    @JsonCreator
    public ApiResponse(ResponseCode responseCode) {
        this.message = responseCode.getMessage();
    }

    @JsonCreator
    public ApiResponse(ResponseCode responseCode, T data) {
        this.message = responseCode.getMessage();
        this.data = data;
    }
}