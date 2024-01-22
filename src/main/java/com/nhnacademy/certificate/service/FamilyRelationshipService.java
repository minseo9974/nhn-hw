package com.nhnacademy.certificate.service;

import com.nhnacademy.certificate.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    void createFamilyRelationship(FamilyRelationship familyRelationship);

    boolean exists(FamilyRelationship.Pk pk);

    void updateFamilyRelationship(FamilyRelationship familyRelationship);

    FamilyRelationship getFamily(FamilyRelationship.Pk pk);

    void deleteFamily(FamilyRelationship familyRelationship);
}
