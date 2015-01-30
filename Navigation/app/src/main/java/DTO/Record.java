package DTO;

/**
 * Created by mj on 15. 1. 30.
 */
public class Record {

    String userName;
    String rivalName;

    String runningDate;
    String runningTime;
    String startTime;
    String endTime;

    boolean isWin;
    Double distance;

    public Record(String userName, String rivalName, String runningDate, String endTime, boolean isWin) {
        this.userName = userName;
        this.rivalName = rivalName;
        this.runningDate = runningDate;
        this.endTime = endTime;
        this.isWin = isWin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRivalName() {
        return rivalName;
    }

    public void setRivalName(String rivalName) {
        this.rivalName = rivalName;
    }

    public String getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(String runningDate) {
        this.runningDate = runningDate;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
