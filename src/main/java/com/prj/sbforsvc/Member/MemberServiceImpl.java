package com.prj.sbforsvc.Member;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prj.sbforsvc.DataNotFoundException;
import com.prj.sbforsvc.Member.Group;
import com.prj.sbforsvc.Member.GroupRepository;
import com.prj.sbforsvc.Member.Member;
import com.prj.sbforsvc.Member.MemberRepository;
import com.prj.sbforsvc.Member.Relation;
import com.prj.sbforsvc.Member.RelationRepository;
import com.prj.sbforsvc.User.User;
import com.prj.sbforsvc.User.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    UserRepository userRepository;
    GroupRepository groupRepository;
    MemberRepository memberRepository;
    RelationRepository relationRepository;

    public MemberServiceImpl(UserRepository userRepository, GroupRepository groupRepository,
            MemberRepository memberRepository, RelationRepository relationRepository) {

        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public boolean SetUserInfo(Model model, User user) {

        Long user_idx = user.getIdx();

        List<Group> groups = groupRepository.findByUser_idx(user_idx);
        // for (Group group : groups) {
        // System.out.println(group.getName());
        // }

        List<Relation> relations = relationRepository.findByUser_idx(user_idx);
        // for (Relation relation : relations) {
        // System.out.println(relation.getName());
        // }

        List<Member> members = memberRepository.findByUser_idx(user_idx);
        // for (Member member : members) {
        // System.out.println(member.getName());
        // }

        model.addAttribute("groups", groups);
        model.addAttribute("relations", relations);
        model.addAttribute("members", members);

        return true;
    }

    @Override
    public boolean addMemberPerUser(Model model, Member member) {

        memberRepository.save(member);

        return true;
    }
}
