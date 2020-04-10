package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tenant.class)
public abstract class Tenant_ {

	public static volatile SingularAttribute<Tenant, Integer> tenant_id;
	public static volatile SingularAttribute<Tenant, String> tenant_name;

	public static final String TENANT_ID = "tenant_id";
	public static final String TENANT_NAME = "tenant_name";

}

