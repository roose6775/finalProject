package project.dto;

import java.util.Date;

public class Task extends ParkObject {

    private Date date;
    private String treat = "";
    private String plant = "";
    private String work = "";
    private int isConfirmed;
    private int isCompleted;
    private String taskComment = "";
    private String ownerComment = "";
    private String foresterComment = "";
    private int plantId;
    private int workId;
    private int treatId;


    public String getTreatName() {
        return treat;
    }

    public void setTreatName(String treat) {
        this.treat = treat;
    }

    public Date getTaskDate() {
        return date;
    }

    public void setTaskDate(Date date) {
        this.date = date;
    }

    public int getTreatId() {
        return treatId;
    }

    public void setTreatId(int treatId) {
        this.treatId = treatId;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return work;
    }

    public void setWorkName(String work) {
        this.work = work;
    }

    public String getPlantName() {
        return plant;
    }

    public void setPlantName(String plant) {
        this.plant = plant;
    }

    public int getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(int isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getTaskComment() {
        return taskComment;
    }

    public void setTaskComment(String comment) {
        this.taskComment = comment;
    }

    public String getOwnerComment() {
        return ownerComment;
    }

    public void setOwnerComment(String comment) {
        this.ownerComment = comment;
    }

    public String getForesterComment() {
        return foresterComment;
    }

    public void setForesterComment(String comment) {
        this.foresterComment = comment;
    }
}
