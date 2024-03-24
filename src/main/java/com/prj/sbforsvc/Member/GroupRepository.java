package com.prj.sbforsvc.Member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByUser_idx(Long user_idx);
}
