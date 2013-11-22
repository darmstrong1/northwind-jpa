package co.da.nw.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Region.class)
public abstract class Region_ {

    public static volatile SingularAttribute<Region, Long> id;
    public static volatile SingularAttribute<Region, String> regionDesc;
}
