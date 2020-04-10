package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkPlan.class)
public abstract class WorkPlan_ {

	public static volatile SingularAttribute<WorkPlan, Integer> request_id;
	public static volatile SingularAttribute<WorkPlan, Integer> is_completed;
	public static volatile SingularAttribute<WorkPlan, Integer> plan_id;
	public static volatile SingularAttribute<WorkPlan, Integer> worker_id;

	public static final String REQUEST_ID = "request_id";
	public static final String IS_COMPLETED = "is_completed";
	public static final String PLAN_ID = "plan_id";
	public static final String WORKER_ID = "worker_id";

}

