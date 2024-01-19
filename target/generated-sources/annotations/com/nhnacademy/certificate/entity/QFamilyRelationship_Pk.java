package com.nhnacademy.certificate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFamilyRelationship_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFamilyRelationship_Pk extends BeanPath<FamilyRelationship.Pk> {

    private static final long serialVersionUID = 121412962L;

    public static final QFamilyRelationship_Pk pk = new QFamilyRelationship_Pk("pk");

    public final NumberPath<Integer> baseResidentSerialNumber = createNumber("baseResidentSerialNumber", Integer.class);

    public final NumberPath<Integer> familyResidentSerialNumber = createNumber("familyResidentSerialNumber", Integer.class);

    public QFamilyRelationship_Pk(String variable) {
        super(FamilyRelationship.Pk.class, forVariable(variable));
    }

    public QFamilyRelationship_Pk(Path<? extends FamilyRelationship.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFamilyRelationship_Pk(PathMetadata metadata) {
        super(FamilyRelationship.Pk.class, metadata);
    }

}

