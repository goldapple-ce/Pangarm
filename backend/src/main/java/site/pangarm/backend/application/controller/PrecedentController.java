package site.pangarm.backend.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.dto.response.PrecedentSearchDetailClientResponse;
import site.pangarm.backend.application.dto.response.PrecedentSearchSummaryClientResponse;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;
import site.pangarm.backend.application.dto.response.*;
import site.pangarm.backend.application.facade.PrecedentFacade;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

@RequiredArgsConstructor
@RequestMapping("/precedent")
@RestController
@Slf4j
public class PrecedentController {

    private final PrecedentFacade precedentFacade;

    @Operation(summary = "판례 검색", description = "판례 검색 기능입니다. 로그인 유무와 상관없이 사용할 수 있습니다. 페이지별로 볼 수 있습니다.")
    @PostMapping("/search")
    public ResponseEntity<ApiResponse<PrecedentSearchResponse>> searchPrecedent(@ParameterObject @PageableDefault Pageable pageable, @RequestBody @Valid PrecedentSearchRequest request, @AuthenticationPrincipal User user) {
        PrecedentSearchResponse response = precedentFacade.searchPrecedent(user, request, pageable);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH, response));
    }

    @GetMapping("/bookmarked")
    @Operation(summary = "북마크된 판례 찾기", description = "북마크된 판례 찾기입니다. 로그인이 필요합니다.")
    public ResponseEntity<ApiResponse<PrecedentBookmarkedResponse>> getBookmarkedPrecedent(@AuthenticationPrincipal(errorOnInvalidType = true) User user, @ParameterObject @PageableDefault Pageable pageable) {
        PrecedentBookmarkedResponse response = precedentFacade.getBookmarkedPrecedent(user, pageable);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH_BOOKMARKED, response));
    }

    @GetMapping("/viewed")
    @Operation(summary = "본 판례 찾기", description = "본 판례 찾기입니다. 로그인이 필요합니다.")
    public ResponseEntity<ApiResponse<PrecedentViewedResponse>> getViewedPrecedent(@AuthenticationPrincipal(errorOnInvalidType = true) User user, @ParameterObject @PageableDefault Pageable pageable) {
        PrecedentViewedResponse response = precedentFacade.getViewedPrecedent(user, pageable);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH_VIEWED, response));
    }

    @GetMapping("/search/summary")
    @Operation(summary = "판례요약 찾기", description = "판례요약 찾기입니다. 로그인 유무와 상관없이 사용할 수 있습니다. 페이지별로 볼 수 있습니다.")
    public ResponseEntity<ApiResponse<PrecedentSearchSummaryClientResponse>> getPrecedentSummary(@AuthenticationPrincipal User user, @NotBlank @RequestParam("caseNumber") @Schema(description = "사건번호", defaultValue = "2017도10601") String caseNumber) {
        PrecedentSearchSummaryClientResponse response = precedentFacade.getPrecedentSummary(user, caseNumber);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH_SUMMARY, response));
    }

    @GetMapping("/search/detail")
    @Operation(summary = "판례상세 찾기", description = "판례상세 찾기입니다. 로그인 유무와 상관없이 사용할 수 있습니다. 페이지별로 볼 수 있습니다.")
    public ResponseEntity<ApiResponse<PrecedentSearchDetailClientResponse>> getPrecedentDetail(@AuthenticationPrincipal User user, @NotBlank @RequestParam("caseNumber") @Schema(description = "사건번호", defaultValue = "2017도10601") String caseNumber) {
        PrecedentSearchDetailClientResponse response = precedentFacade.getPrecedentDetail(user, caseNumber);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_PRECEDENT_SEARCH_DETAIL, response));
    }
}
