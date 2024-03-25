package com.prj.sbforsvc.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.prj.sbforsvc.Member.Group;
import com.prj.sbforsvc.Member.Member;
import com.prj.sbforsvc.Member.Relation;
import com.prj.sbforsvc.Photo.Photo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Entity(name = "usertbl")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "email" })
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Long idx;

    @Column(name = "email")
    private String email;

    @Column(name = "pw")
    private String pw;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "user")
    private final List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Relation> relations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Photo> photos = new ArrayList<>();

    public void setValue(User user) {
        this.idx = user.getIdx();
        this.pw = user.getPw();
        this.name = user.getName();
        this.created_at = user.getCreated_at();
        this.email = user.getEmail();
    }
}
