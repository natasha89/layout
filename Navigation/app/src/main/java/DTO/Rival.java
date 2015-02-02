package DTO;

import android.graphics.Bitmap;

public class Rival {
    String name;

    //drawble resource
    int pic;

    //Basic, Normal, hard, Event?
    String mode;

    //초반, 중반, 종반 각각의 변수로 나눌건지 배열같은 걸로 할건지~
    String[] runningStyle = new String[3]; //String speed;

    String goalTime;
    String speed;
    String ment;

    public Rival(String name, int pic, String mode, String goalTime, String speed) {
        this.name = name;
        this.pic = pic;
        this.mode = mode;
        this.goalTime = goalTime;
        this.speed = speed;
    }

    public Rival(String name, int pic, String mode, String[] runningStyle, String speed, String goalTime) {
        this.name = name;
        this.pic = pic;
        this.mode = mode;
        this.runningStyle = runningStyle;
        this.speed = speed;
        this.goalTime = goalTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String[] getRunningStyle() {
        return runningStyle;
    }

    public void setRunningStyle(String[] runningStyle) {
        this.runningStyle = runningStyle;
    }

    public String getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(String goalTime) {
        this.goalTime = goalTime;
    }


    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMent() {
        return ment;
    }

    public void setMent(String ment) {
        this.ment = ment;
    }
}
