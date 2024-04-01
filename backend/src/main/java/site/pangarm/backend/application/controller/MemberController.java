package site.pangarm.backend.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.dto.response.MemberFindByIdResponse;
import site.pangarm.backend.application.dto.response.PrecedentSearchHistoryResponse;
import site.pangarm.backend.application.facade.MemberFacade;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(@RequestBody MemberSignUpRequest memberJoinDto) {
        memberFacade.signup(memberJoinDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SIGNUP));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<MemberFindByIdResponse>> getMemberById(@AuthenticationPrincipal(errorOnInvalidType = true) User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_GET_BY_ID, memberFacade.getById(Integer.parseInt(user.getUsername()))));
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

    @GetMapping("/category")
    public ResponseEntity<ApiResponse<List<String>>> getCategoryList(@AuthenticationPrincipal(errorOnInvalidType = true) User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_CATEGORY_LIST, memberFacade.getCategoryList(Integer.parseInt(user.getUsername()))));
    }

    @PostMapping("/category-subscribe")
    public ResponseEntity<ApiResponse<Void>> subscribe(@AuthenticationPrincipal(errorOnInvalidType = true) User user, @RequestParam("category_id") int categoryId) {
        memberFacade.subscribe(Integer.parseInt(user.getUsername()), categoryId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SUBSCRIBE));
    }

    @PostMapping("/category-unsubscribe")
    public ResponseEntity<ApiResponse<Void>> unsubscribe(@AuthenticationPrincipal User user, @RequestParam("category_id") int categoryId) {
        memberFacade.unsubscribe(Integer.parseInt(user.getUsername()), categoryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_UNSUBSCRIBE));
    }
}
