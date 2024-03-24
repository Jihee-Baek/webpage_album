package com.prj.sbforsvc.Member;

import org.springframework.ui.Model;

public interface GroupService {

    public boolean addGroupPerUser(Model model, Group group);

    public Group getGroup(Long idx);
}
