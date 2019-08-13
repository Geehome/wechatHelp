package com.pojo;

import java.sql.Timestamp;
import java.util.List;

public class Answer {
    private int aid;
    private String answer;
    private int qid;
    private int quid;
    private int auid;
    private Timestamp atime;
    private int accepted;
    private int doctorId;
    private int like_sum;
    private int ans_pv;
    private int ans_reply_sum;


    //与user表连接使用
    private List<User> users;
    private User user;

    //格式化的日期
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAns_pv() {
        return ans_pv;
    }

    public void setAns_pv(int ans_pv) {
        this.ans_pv = ans_pv;
    }

    public int getAns_reply_sum() {
        return ans_reply_sum;
    }

    public void setAns_reply_sum(int ans_reply_sum) {
        this.ans_reply_sum = ans_reply_sum;
    }

    public int getLike_sum() {
        return like_sum;
    }

    public void setLike_sum(int like_sum) {
        this.like_sum = like_sum;
    }

    public Timestamp getAtime() {
        return atime;
    }

    public void setAtime(Timestamp atime) {
        this.atime = atime;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getQuid() {
        return quid;
    }

    public void setQuid(int quid) {
        this.quid = quid;
    }

    public int getAuid() {
        return auid;
    }

    public void setAuid(int auid) {
        this.auid = auid;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "aid=" + aid +
                ", answer='" + answer + '\'' +
                ", qid=" + qid +
                ", quid=" + quid +
                ", auid=" + auid +
                ", atime=" + atime +
                ", users=" + users +
                ", user=" + user +
                ", accepted=" + accepted +
                ", doctorId=" + doctorId +
                '}';
    }
}
