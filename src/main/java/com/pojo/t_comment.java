package com.pojo;

import java.sql.Timestamp;

public class t_comment {

    int comment_uid;
    Timestamp c_time;
    int c_aid;
    String description;
    User user;

    //界面用
    String time;

    public int getComment_uid() {
        return comment_uid;
    }

    public void setComment_uid(int comment_uid) {
        this.comment_uid = comment_uid;
    }

    public Timestamp getC_time() {
        return c_time;
    }

    public void setC_time(Timestamp c_time) {
        this.c_time = c_time;
    }

    public int getC_aid() {
        return c_aid;
    }

    public void setC_aid(int c_aid) {
        this.c_aid = c_aid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
