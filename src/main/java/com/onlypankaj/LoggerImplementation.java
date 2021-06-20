package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.HashMap;
import java.util.Map;

public class LoggerImplementation implements LogClient {
    private final Map<String, Process> processes;

    public LoggerImplementation() {
        this.processes = new HashMap<>();
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

    }
}
