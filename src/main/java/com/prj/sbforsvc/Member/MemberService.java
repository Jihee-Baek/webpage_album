package com.prj.sbforsvc.Member;

import org.springframework.ui.Model;

import com.prj.sbforsvc.User.User;

public interface MemberService {

    public boolean SetUserInfo(Model model, User user);

    public boolean addMemberPerUser(Model model, Member member);
}
