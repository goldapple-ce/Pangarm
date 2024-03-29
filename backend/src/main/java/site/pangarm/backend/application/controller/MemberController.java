package site.pangarm.backend.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.dto.response.PrecedentSearchHistoryResponse;
import site.pangarm.backend.application.facade.MemberFacade;
import site.pangarm.backend.application.runner.InitialFacade;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
    private final InitialFacade initialFacade;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<ResponseCode>> signup(@RequestBody MemberSignUpRequest memberJoinDto) {
        memberFacade.signup(memberJoinDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SIGNUP));
    }

    @GetMapping("/test")
    public String test() {
        return "접근 성공";
    }

    @Deprecated
    @GetMapping("/setup")
    public void setup() {
        initialFacade.setUp();
    }

    @Operation(summary = "검색 히스토리 찾기", description = "검색 히스토리 찾기입니다. 로그인이 필요합니다.")
    @GetMapping("/search/history")
    public ResponseEntity<ApiResponse<PrecedentSearchHistoryResponse>> findAllSearchHistory(@AuthenticationPrincipal(errorOnInvalidType = true) User user) {
        PrecedentSearchHistoryResponse response = memberFacade.findAllSearchHistory(user);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_NEWS_FIND_BY_ID, response));
    }

    @Operation(summary = "판례 북마크", description = "판례 북마크입니다. 로그인이 필요합니다.")
    @PutMapping("/precedent")
    public ResponseEntity<ApiResponse<Void>> bookMark(@AuthenticationPrincipal(errorOnInvalidType = true) User user, @RequestParam("id") int precedentId) {
        memberFacade.bookmarkPrecedent(user, precedentId);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_BOOKMARK));
    }


}
