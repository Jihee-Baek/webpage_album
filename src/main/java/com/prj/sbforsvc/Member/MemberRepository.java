package com.prj.sbforsvc.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public List<Member> findByUser_idx(Long user_idx);
}
