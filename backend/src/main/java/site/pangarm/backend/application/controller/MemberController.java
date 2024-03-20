package site.pangarm.backend.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.facade.MemberFacade;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;
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


}
