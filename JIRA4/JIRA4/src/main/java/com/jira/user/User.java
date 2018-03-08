package com.jira.user;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String insertuser;

    private Date insertdate;

    private String lastedituser;

    private Date lasteditdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getInsertuser() {
        return insertuser;
    }

    public void setInsertuser(String insertuser) {
        this.insertuser = insertuser == null ? null : insertuser.trim();
    }

    public Date getInsertdate() {
        return insertdate;
    }

    public void setInsertdate(Date insertdate) {
        this.insertdate = insertdate;
    }

    public String getLastedituser() {
        return lastedituser;
    }

    public void setLastedituser(String lastedituser) {
        this.lastedituser = lastedituser == null ? null : lastedituser.trim();
    }

    public Date getLasteditdate() {
        return lasteditdate;
    }

    public void setLasteditdate(Date lasteditdate) {
        this.lasteditdate = lasteditdate;
    }
}