package com.prj.sbforsvc.Member;

import com.prj.sbforsvc.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "membertbl")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_idx") // , nullable = false, columnDefinition = "BIGINT UNSIGNED"
    private Long idx;

    @Column(name = "name") // , nullable = false, length = 50
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_idx")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_idx")
    private Relation relation;
}
