package com.prj.sbforsvc.Member;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.prj.sbforsvc.DataNotFoundException;

@Service
public class RelationServiceImpl implements RelationService {

    RelationRepository relationRepository;

    public RelationServiceImpl(RelationRepository relationRepository) {

        this.relationRepository = relationRepository;
    }

    @Override
    public boolean addRelationPerUser(Model model, Relation relation) {

        relationRepository.save(relation);

        return true;
    }

    @Override
    public Relation getRelation(Long idx) {

        Optional<Relation> uOptional = relationRepository.findById(idx);
        if (uOptional.isPresent()) {
            return uOptional.get();
        } else {
            throw new DataNotFoundException("Group Not Found");
        }
    }
}
