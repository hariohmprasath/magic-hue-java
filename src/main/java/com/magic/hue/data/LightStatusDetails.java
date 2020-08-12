package com.magic.hue.data;

public class LightStatusDetails {
    private Status status;
    private String rbg;
    private String warm;

    public LightStatusDetails(){
        super();
    }

    public LightStatusDetails(Status status){
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRbg() {
        return rbg;
    }

    public void setRbg(String rbg) {
        this.rbg = rbg;
    }

    public String getWarm() {
        return warm;
    }

    public void setWarm(String warm) {
        this.warm = warm;
    }

    public String toString(){
        return String.format("status=%s, rgb=%s, warm=%s", this.status, this.rbg, this.warm);
    }
}
