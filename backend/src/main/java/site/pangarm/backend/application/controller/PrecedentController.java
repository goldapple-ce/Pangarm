package site.pangarm.backend.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;
import site.pangarm.backend.application.dto.response.PrecedentSearchResponse;
import site.pangarm.backend.application.facade.PrecedentFacade;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/precedent")
@RestController
public class PrecedentController {

    private final PrecedentFacade precedentFacade;

    @Operation(summary = "판례 검색",description = "판례 검색 기능입니다. 로그인 유무와 상관없이 사용할 수 있습니다. 페이지별로 볼 수 있습니다.")
    @PostMapping("")
    public ResponseEntity<ApiResponse<PrecedentSearchResponse>> searchPrecedent(@ParameterObject @PageableDefault Pageable pageable, @RequestBody @Valid PrecedentSearchRequest request, @AuthenticationPrincipal User user){
        PrecedentSearchResponse response = precedentFacade.searchPrecedent(user,request,pageable);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH,response));
    }
}
