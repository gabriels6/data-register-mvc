package com.gomes.dataregister.dataobject;

import com.gomes.dataregister.model.Branch;

public class BranchDataObject extends GenericDataObject {

    private int Id;
    private String name;
    private int user_id;
    private int repo_id;
    private int status_id;

    public BranchDataObject() {

    }

    public BranchDataObject(Branch branch) {
        this.Id = branch.getId();
        this.name = branch.getName();
        this.user_id = branch.getUser().getId();
        this.repo_id = branch.getRepository().getId();
        this.status_id = branch.getStatus().getId();
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRepo_id() {
        return repo_id;
    }

    public void setRepo_id(int repo_id) {
        this.repo_id = repo_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
