package com.onlypankaj.model;

public class Process {
    private final String id;
    private final long startTime;
    private long endTime;

    public Process(String id, long startTime, long endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


}
