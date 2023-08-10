package com.sstep.demo.member;


import com.sstep.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //<Entity 클래스, PK 타입>
    Member findByUsername(String username); //아이디로 회원찾기
}
