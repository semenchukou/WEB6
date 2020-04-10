package model.entities;

import javax.persistence.*;

/**
 * Class that represents worker.
 * @version 1.0
 */
@Entity(name = "Worker")
@Table(name = Worker.tableName, schema = "public")
@NamedQueries({
        @NamedQuery(
                name = "deleteWorker",
                query = "delete from Worker w where w.worker_id = :id"),
        @NamedQuery(
                name = "readWorkers",
                query = "select w from Worker w"),
        @NamedQuery(
                name = "readWorker",
                query = "select w from Worker w where w.worker_id = :id")
})
public class Worker {
    public static final String tableName = "Worker";
    /**
     * Worker id
     * */
    @Id
    @GeneratedValue
    private int worker_id;

    /**
     * Worker's name
     * */
    private String worker_name;

    /**
     * Worker's job profile
     * */
    private String worker_profile;

    public Worker() {}
    /**
     * Create's worker
     * */
    public Worker(int worker_id, String worker_name, String worker_profile){
        this.worker_id = worker_id;
        this.worker_name = worker_name;
        this.worker_profile = worker_profile;
    }

    public void setWorker_id(int workerID) {
        this.worker_id = workerID;
    }

    public void setWorker_name(String workerName) {
        this.worker_name = workerName;
    }

    public void setWorker_profile(String workerProfile) {
        this.worker_profile = workerProfile;
    }

    public int getWorker_id() {
        return worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public String getWorker_profile() {
        return worker_profile;
    }

    @Override
    public String toString() {
        return "Worker: id: " + worker_id +
                "\nworkerName: " + worker_name +
                "\nworkerProfile: " + worker_profile + "\n";
    }
}
