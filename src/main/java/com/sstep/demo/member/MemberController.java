package com.sstep.demo.member;

import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberJoinDto;
import com.sstep.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController(value = "/member")
public class MemberController {
    private final MemberService memberService;

    //회원가입
//    @PostMapping("/join")
//    public ResponseEntity<Member> Register(@RequestBody Member member) {
//        Member saveMember = MemberService.save(member);
//        return ResponseEntity.ok().body(saveMember);
//    }

    //아이디로 회원 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberJoinDto> findByMemberId(@PathVariable(value = "memberId") String memberId) {
        return ResponseEntity.ok().body(memberService.findByMemberId(memberId));
    }

}
