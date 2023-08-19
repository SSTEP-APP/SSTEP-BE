package com.sstep.demo.member;


import com.sstep.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //<Entity 클래스, PK 타입>
    @Query("SELECT m FROM Member m WHERE m.username = :username")
    Member findByUsername(String username); //아이디로 회원찾기

    @Query("SELECT m FROM Member m WHERE m.name = :name and m.phoneNum = :phoneNum")
    Member findByNameAndPhoneNum(String name, String phoneNum); //이름과 전화번호로 회원 찾기
}
