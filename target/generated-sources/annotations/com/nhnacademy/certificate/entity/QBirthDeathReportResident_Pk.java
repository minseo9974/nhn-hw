package com.nhnacademy.certificate.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBirthDeathReportResident_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBirthDeathReportResident_Pk extends BeanPath<BirthDeathReportResident.Pk> {

    private static final long serialVersionUID = -1280616123L;

    public static final QBirthDeathReportResident_Pk pk = new QBirthDeathReportResident_Pk("pk");

    public final StringPath birthDeathTypeCode = createString("birthDeathTypeCode");

    public final NumberPath<Integer> residentSerialNumber = createNumber("residentSerialNumber", Integer.class);

    public QBirthDeathReportResident_Pk(String variable) {
        super(BirthDeathReportResident.Pk.class, forVariable(variable));
    }

    public QBirthDeathReportResident_Pk(Path<? extends BirthDeathReportResident.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBirthDeathReportResident_Pk(PathMetadata metadata) {
        super(BirthDeathReportResident.Pk.class, metadata);
    }

}

