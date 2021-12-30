package com.gomes.dataregister.core.dataobject;

import com.gomes.dataregister.core.utils.DateUtils;

public class LogDataObject extends GenericDataObject {

    private int id;
    private String description;
    private int user_id;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        String date = this.date;
        if(date == null) {
            date = new DateUtils().getCurrentDate();
        }
        if(date.contains("/") && date.split("/").length == 3) {
            String[] dateParts = date.split("/");
            date = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
