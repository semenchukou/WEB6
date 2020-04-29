package model.entities;

import javax.persistence.*;

/**
 * Class that represents request.
 * @version 1.0
 */
@Entity(name = "Request")
@Table(name = Request.tableName, schema = "public")
@NamedQueries({
        @NamedQuery(
                name = "deleteRequest",
                query = "delete from Request r where r.request_id = :id"),
        @NamedQuery(
                name = "readRequests",
                query = "select r from Request r"),
        @NamedQuery(
                name = "readRequest",
                query = "select r from Request r where r.request_id = :id"),
        @NamedQuery(
                name = "updateRequestStatus",
                query = "update Request r set r.status = :status where r.request_id = :id"),
        @NamedQuery(
                name = "getOverdueRequests",
                query = "SELECT r FROM Request r WHERE r.to_date < CURRENT_DATE and r.status in (0, 1)")
})
public class Request {

    public static final String tableName = "request";

    /**
     * Request id
     * */
    @Id
    @GeneratedValue
    private int request_id;

    /**
     * Job type
     * */
    private String job_type;

    /**
     * Job description
     * */
    private String job_descr;

    /**
     * Expected date to finish work
     * */
    private String to_date;

    /**
     * Tenant id (foreign key)
     * */
    private int tenant_id;


    /**
     * Work status(0 - new, 1 - in process, 2 - completed, 3 - canceled)
     * */
    private int status;

    public Request(){}
    /**
     * Creates request
     * */
    public Request(int requestID,
                   String jobType,
                   String jobDescription,
                   String toDate,
                   int tenantID,
                   int status){
        this.request_id = requestID;
        this.job_type = jobType;
        this.job_descr = jobDescription;
        this.to_date = toDate;
        this.tenant_id = tenantID;
        this.status = status;
    }

    public void setTenant_id(int tenantID) {
        this.tenant_id = tenantID;
    }

    public void setJob_descr(String jobDescription) {
        this.job_descr = jobDescription;
    }

    public void setJob_type(String jobType) {
        this.job_type = jobType;
    }

    public void setRequest_id(int requestID) {
        this.request_id = requestID;
    }

    public void setTo_date(String toDate) {
        this.to_date = toDate;
    }

    public int getTenant_id() {
        return tenant_id;
    }

    public int getRequest_id() {
        return request_id;
    }

    public String getJob_descr() {
        return job_descr;
    }

    public String getJob_type() {
        return job_type;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Request: id: " + request_id +
                "\nstatus: " + status +
                "\njobType: " + job_type +
                "\njobDescription: " + job_descr +
                "\ntoDate: " + to_date +
                "\ntenantID: " + tenant_id + "\n";
    }
}
