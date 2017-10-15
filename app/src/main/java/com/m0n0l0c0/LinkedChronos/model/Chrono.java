package com.m0n0l0c0.LinkedChronos.model;

import android.os.CountDownTimer;

/**
 * Created by juanje on 22/05/16.
 */
public class Chrono {

    private long miliseconds;
    private String name;
    private CountDownTimer countDownTimer;

    public Chrono() {
    }

    public Chrono(long milisecond, String name, CountDownTimer countDownTimer) {
        this.miliseconds = milisecond;
        this.name = name;
        this.countDownTimer = countDownTimer;
    }

    public long getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }
}
