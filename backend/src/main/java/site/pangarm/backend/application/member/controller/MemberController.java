package site.pangarm.backend.application.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.domain.member.dto.MemberJoinDto;
import site.pangarm.backend.domain.member.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/sign-up")
    public String signUp(@RequestBody MemberJoinDto memberJoinDto) {
        memberService.signup(memberJoinDto);
        return "회원가입 성공";
    }

}
