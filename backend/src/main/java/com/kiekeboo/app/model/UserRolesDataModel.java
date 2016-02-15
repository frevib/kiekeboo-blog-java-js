package com.kiekeboo.app.model;

/**
 * Created by hdv on 05/02/16.
 */
public class UserRolesDataModel {

    private int roleId;
    private String role;
    private String roleDescription;


    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
