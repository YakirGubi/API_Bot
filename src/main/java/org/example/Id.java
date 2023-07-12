package org.example;

import java.sql.Time;

public class Id {

    private String name;
    private Time lastRequestT;
    private String lastRequest;
    private int sumOfRequests;

    public Id(){

    }

    public Id(String name, Time lastRequestT, String lastRequest) {
        this.name = name;
        this.lastRequestT = lastRequestT;
        this.lastRequest = lastRequest;
        this.sumOfRequests = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastRequestT() {
        if(this.lastRequestT == null){
            return "null";
        }
        return lastRequestT.toString();
    }

    public void setLastRequestT(Time lastRequestT) {
        this.lastRequestT = lastRequestT;
    }

    public String getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(String lastRequest) {
        this.lastRequest = lastRequest;
    }

    public int getSumOfRequests() {
        return sumOfRequests;
    }

    public void setSumOfRequests() {
        this.sumOfRequests++;
    }
}
