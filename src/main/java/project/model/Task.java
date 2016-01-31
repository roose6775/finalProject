package project.model;

public class Task extends ParkObject {

    private String plant = "";
    private String work = "";
    private boolean isConfirmed;
    private boolean isCompleted;
    private String taskComment = "";
    private String ownerComment = "";
    private String foresterComment = "fffff";




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

    public boolean getIsConfirmed() {
        return isConfirmed;
    }

    public void setIsConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
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
