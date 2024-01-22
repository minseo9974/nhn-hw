package com.nhnacademy.certificate.service.impl;

import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.repository.FamilyRelationshipRepository;
import com.nhnacademy.certificate.service.FamilyRelationshipService;
import org.springframework.stereotype.Service;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
    }

    @Override
    public void createFamilyRelationship(FamilyRelationship familyRelationship) {
        familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public boolean exists(FamilyRelationship.Pk pk) {
        return familyRelationshipRepository.existsById(pk);
    }

    @Override
    public void updateFamilyRelationship(FamilyRelationship familyRelationship) {
        familyRelationshipRepository.save(familyRelationship);
    }

    @Override
    public FamilyRelationship getFamily(FamilyRelationship.Pk pk) {
        return familyRelationshipRepository.findById(pk).orElse(null);
    }

    @Override
    public void deleteFamily(FamilyRelationship familyRelationship) {
        familyRelationshipRepository.delete(familyRelationship);
    }
}
