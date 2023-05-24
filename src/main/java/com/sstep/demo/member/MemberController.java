package com.sstep.demo.member;

import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.save(memberRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //아이디로 회원 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> findByMemberId(@PathVariable(value = "memberId") String memberId) {
        return ResponseEntity.ok().body(memberService.findByMemberId(memberId));
    }

    //아이디를 통한 중복 체크
    @GetMapping("/checkDuplicate")
    public ResponseEntity<String> checkDuplicateMemberId(@RequestParam String memberId) {
        if(memberService.isMemberIdDuplicate(memberId)){
            return ResponseEntity.badRequest().body("중복된 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용가능한 아이디입니다.");
        }
    }
}
