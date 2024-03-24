package com.prj.sbforsvc.Member;

import org.springframework.ui.Model;

public interface RelationService {

    public boolean addRelationPerUser(Model model, Relation relation);

    public Relation getRelation(Long idx);
}
