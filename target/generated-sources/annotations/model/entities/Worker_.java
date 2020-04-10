package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Worker.class)
public abstract class Worker_ {

	public static volatile SingularAttribute<Worker, String> worker_profile;
	public static volatile SingularAttribute<Worker, String> worker_name;
	public static volatile SingularAttribute<Worker, Integer> worker_id;

	public static final String WORKER_PROFILE = "worker_profile";
	public static final String WORKER_NAME = "worker_name";
	public static final String WORKER_ID = "worker_id";

}

