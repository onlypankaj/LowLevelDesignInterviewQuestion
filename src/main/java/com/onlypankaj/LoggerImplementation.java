package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.TreeMap;

public class LoggerImplementation implements LogClient {
    private final TreeMap<String, Process> processes;

    public LoggerImplementation() {
        this.processes = new TreeMap<>();
    }

    @Override
    public void start(String processId) {
        processes.put(processId, new Process(processId,System.currentTimeMillis()));

    }

    @Override
    public void end(String processId) {
        processes.get(processId).setEndTime(System.currentTimeMillis()); // List issue with search, resolved with map

    }

    @Override
    public void poll() {
        //Issue we need to find process which has smallest start time
        //This is difficult with map as there is no ordering
        //This can be resolved with TreeMap, which has order

        final Process process = processes.firstEntry().getValue();

        //Remove Process
        if(process.getEndTime()!=1){
            System.out.println(process.getId() + "started at " + process.getStartTime() + " and ended at "+ process.getEndTime());
            processes.remove(process.getId());
        }

    }
}
