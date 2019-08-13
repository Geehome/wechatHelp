package com.pojo;


import java.sql.Timestamp;
import java.util.List;

public class Question {
    private int qid;
    private int uid;
    private String description;
    private String img;
    private int pv;
    private int acceptAid;
    private int stat;
    private int reward;
    private Timestamp qtime;
    private String title;
    private int reply_sum;

    private String userName;

    //与answer表连接查询使用
    private List<Answer> answers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getReply_sum() {
        return reply_sum;
    }

    public void setReply_sum(int reply_sum) {
        this.reply_sum = reply_sum;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Timestamp getQtime() {
        return qtime;
    }

    public void setQtime(Timestamp qtime) {
        this.qtime = qtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getAcceptAid() {
        return acceptAid;
    }

    public void setAcceptAid(int acceptAid) {
        this.acceptAid = acceptAid;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qid=" + qid +
                ", uid=" + uid +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", pv=" + pv +
                ", acceptAid=" + acceptAid +
                ", stat=" + stat +
                ", reward=" + reward +
                ", qtime=" + qtime +
                ", title='" + title + '\'' +
                '}';
    }
}
