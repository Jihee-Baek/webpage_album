package com.prj.sbforsvc.Member;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prj.sbforsvc.DataNotFoundException;

@Service
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {

        this.groupRepository = groupRepository;
    }

    @Override
    public boolean addGroupPerUser(Model model, Group group) {

        groupRepository.save(group);

        return true;
    }

    @Override
    public Group getGroup(Long idx) {

        Optional<Group> uOptional = groupRepository.findById(idx);
        if (uOptional.isPresent()) {
            return uOptional.get();
        } else {
            throw new DataNotFoundException("Group Not Found");
        }
    }
}
