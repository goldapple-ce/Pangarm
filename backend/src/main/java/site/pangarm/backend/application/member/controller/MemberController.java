package site.pangarm.backend.application.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.member.dto.request.MemberSignUpRequest;
import site.pangarm.backend.domain.member.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/sign-up")
    public String signUp(@RequestBody MemberSignUpRequest memberJoinDto) {
        memberService.signup(memberJoinDto);
        return "회원가입 성공";
    }

}
