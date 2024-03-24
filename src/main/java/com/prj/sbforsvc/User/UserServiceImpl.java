package com.prj.sbforsvc.User;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prj.sbforsvc.DataNotFoundException;
import com.prj.sbforsvc.Member.Group;
import com.prj.sbforsvc.Member.GroupRepository;
import com.prj.sbforsvc.Member.Member;
import com.prj.sbforsvc.Member.MemberRepository;
import com.prj.sbforsvc.Member.Relation;
import com.prj.sbforsvc.Member.RelationRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    GroupRepository groupRepository;
    MemberRepository memberRepository;
    RelationRepository relationRepository;

    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
            MemberRepository memberRepository, RelationRepository relationRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public boolean checkUser(String email, String password, User user, Model model) {

        Optional<User> u = userRepository.findByEmail(email);
        if (u.isPresent()) {
            user.setValue(u.get());
            if (user.getPw().equals(password)) {
                model.addAttribute("user", user);
                return true;
            } else {
                model.addAttribute("error", true);
                return false;
            }
        } else {
            model.addAttribute("error", true);
            return false;
        }

    }

    @Override
    public RetRegister registerUser(String username, String email, String password, String confirmPassword,
            Model model) {

        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", true);
            model.addAttribute("message", "비밀번호가 일치하지 않습니다.");
            return RetRegister.DIFFPW;
        }

        User user = User.builder()
                .name(username)
                .email(email)
                .pw(password)
                .created_at(LocalDateTime.now())
                .build();

        userRepository.save(user);

        Group group = Group.builder()
                .user(user)
                .name("우리 가족")
                .build();
        groupRepository.save(group);

        Relation relation = Relation.builder()
                .user(user)
                .name("본인")
                .build();
        relationRepository.save(relation);

        Member member = Member.builder()
                .user(user)
                .name(user.getName())
                .relation(relation)
                .group(group)
                .build();
        memberRepository.save(member);

        return RetRegister.SUCCESS;
    }

    @Override
    public User getUser(Long idx) {

        Optional<User> uOptional = userRepository.findById(idx);
        if (uOptional.isPresent()) {
            return uOptional.get();
        } else {
            throw new DataNotFoundException("User Not Found");
        }
    }
}
