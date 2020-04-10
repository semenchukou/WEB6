package model.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Request.class)
public abstract class Request_ {

	public static volatile SingularAttribute<Request, String> job_type;
	public static volatile SingularAttribute<Request, Integer> tenant_id;
	public static volatile SingularAttribute<Request, String> to_date;
	public static volatile SingularAttribute<Request, String> job_descr;
	public static volatile SingularAttribute<Request, Integer> request_id;
	public static volatile SingularAttribute<Request, Integer> status;

	public static final String JOB_TYPE = "job_type";
	public static final String TENANT_ID = "tenant_id";
	public static final String TO_DATE = "to_date";
	public static final String JOB_DESCR = "job_descr";
	public static final String REQUEST_ID = "request_id";
	public static final String STATUS = "status";

}

