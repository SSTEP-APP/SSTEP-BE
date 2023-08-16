package com.sstep.demo.member;

import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.member.service.MemberService;
import com.sstep.demo.store.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
    @GetMapping("/{username}")
    public MemberResponseDto getMemberByUsername(@PathVariable String username) {
        return memberService.getMemberByUsername(username);
    }

    //아이디를 통한 중복 체크
    @GetMapping("/check/duplicate")
    public ResponseEntity<String> checkDuplicateUsername(@RequestBody MemberRequestDto memberRequestDto) {
        if (memberService.isUsernameDuplicate(memberRequestDto)) {
            return ResponseEntity.badRequest().body("중복된 아이디입니다.");
        } else {
            return ResponseEntity.ok("사용가능한 아이디입니다.");
        }
    }

    //회원이 소속된 사업장 리스트
    @GetMapping("/{username}/stores")
    public Set<StoreResponseDto> getStoresBelongMember(@PathVariable String username) {
        return memberService.getStoresBelongMember(username);
    }
}
