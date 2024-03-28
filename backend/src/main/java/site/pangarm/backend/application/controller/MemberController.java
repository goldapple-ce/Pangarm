package site.pangarm.backend.application.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.facade.MemberFacade;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;


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
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SIGNUP));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Member>> getById(@AuthenticationPrincipal User user) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_GET_BY_ID, memberFacade.getById(Integer.parseInt(user.getUsername()))));
    }

    @PostMapping("/category-subscribe")
    public ResponseEntity<ApiResponse<Void>> subscribe(@AuthenticationPrincipal User user, @RequestBody int categoryId) {
        memberFacade.subscribe(Integer.parseInt(user.getUsername()), categoryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_MEMBER_SUBSCRIBE));
    }

}
