package model.entities;

import javax.persistence.*;

/**
 * Class that represents tenant.
 * @version 1.0
 */

@Entity(name = "Tenant")
@Table(name = Tenant.tableName, schema = "public")
@NamedQueries({
        @NamedQuery(
                name = "deleteTenants",
                query = "delete from Tenant"),
        @NamedQuery(
                name = "deleteTenant",
                query = "delete from Tenant t where t.tenant_id = :id"),
        @NamedQuery(
                name = "readTenants",
                query = "select t from Tenant t"),
        @NamedQuery(
                name = "readTenant",
                query = "select t from Tenant t where t.tenant_id = :id"),
})
public class Tenant {
    public static final String tableName = "Tenant";
    /**
     * Tenant id
     * */
    @Id
    @GeneratedValue
    private int tenant_id;

    /**
     * Tenant name
     * */
    private String tenant_name;

    public Tenant(){}
    /**
     * Creates tenant
     * */
    public Tenant(int id, String name){
        this.tenant_id = id;
        this.tenant_name = name;
    }

    public void setTenant_id(int tenantID) {
        this.tenant_id = tenantID;
    }

    public void setTenant_name(String tenantName) {
        this.tenant_name = tenantName;
    }

    public int getTenant_id() {
        return tenant_id;
    }

    public String getTenant_name() {
        return tenant_name;
    }

    @Override
    public String toString() {
        return "Tenant: id: " + this.tenant_id +
                "\nname: " + this.tenant_name + "\n";
    }
}
