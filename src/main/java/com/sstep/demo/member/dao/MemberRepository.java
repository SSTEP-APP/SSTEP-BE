package com.sstep.demo.member.dao;


import com.sstep.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //<Entity 클래스, PK 타입>
}
