package model.entities;

import javax.persistence.*;

/**
 * Class that represents work plan.
 * @version 1.0
 */
@Entity(name = "WorkPlan")
@Table(name = WorkPlan.tableName, schema = "public")
@NamedQueries({
        @NamedQuery(
                name = "deleteWorkPlan",
                query = "delete from WorkPlan wp where wp.plan_id = :id"),
        @NamedQuery(
                name = "readWorkPlans",
                query = "select wp from WorkPlan wp"),
        @NamedQuery(
                name = "readWorkPlan",
                query = "select wp from WorkPlan wp where wp.plan_id = :id"),
        @NamedQuery(
                name = "changeWorkPlanStatus",
                query = "UPDATE WorkPlan wp SET wp.is_completed = :status WHERE wp.plan_id = :plan_id")
})
public class WorkPlan {
    public static final String tableName = "work_plan";

    /**
     * Plan id
     * */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int plan_id;

    /**
     * Request id(foreign key)
     * */
    private int request_id;

    /**
     * Worker id(foreign key)
     * */
    private int worker_id;

    /**
     * Shows if work completed(0 - in process, 1 - completed)
     * */
    private int is_completed;

    public WorkPlan() {}

    /**
     * Creates work plan
     * */
    public WorkPlan(int request_id, int worker_id) {
        this.request_id = request_id;
        this.worker_id = worker_id;
    }

    public void setRequest_id(int requestID) {
        this.request_id = requestID;
    }

    public void setPlan_id(int planID) {
        this.plan_id = planID;
    }

    public void setWorker_id(int workerID) {
        this.worker_id = workerID;
    }

    public int getRequest_id() {
        return request_id;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setIs_completed(int isCompleted) {
        this.is_completed = isCompleted;
    }

    public int getIs_completed() {
        return is_completed;
    }

    @Override
    public String toString() {
        return "WorkPlan: id: " + plan_id +
                "\nisCompleted: " + (is_completed == 1) +
                "\nrequestID: " + request_id +
                "\nworkerID: " + worker_id + "\n";
    }
}
