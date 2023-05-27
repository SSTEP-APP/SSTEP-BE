package com.sstep.demo;

import com.sstep.demo.member.MemberMapper;
import com.sstep.demo.member.MemberRepository;
import com.sstep.demo.member.domain.Member;
import com.sstep.demo.member.dto.MemberRequestDto;
import com.sstep.demo.member.dto.MemberResponseDto;
import com.sstep.demo.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MemberService memberService;



	@Test
	void contextLoads() {
	}

	@Test
	void save(){
		MemberRequestDto member = new MemberRequestDto();
		member.setId(1);
		member.setMemberId("hi2");
		member.setName("hi");
		member.setPassword("12345");
		member.setPhoneNum("010-1111-1111");

		memberService.save(member);
	}



}
