package DTO;

import android.graphics.Bitmap;

/**
 * Created by mj on 15. 1. 30.
 */
public class RivalInfo {
    String name;
    Bitmap avatar;

    String mode;

    //초반, 중반, 종반 각각의 변수로 나눌건지 배열같은 걸로 할건지~
    String[] runningStyle;

    String goalTime;

    //바뀔 수 있음

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getAvatar() {
        return avatar;
    }

    public void setAvatar(Bitmap avatar) {
        this.avatar = avatar;
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

    String speed;
}
