package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.ArrayList;
import java.util.List;

public class LoggerImplementation implements LogClient {
    private final List<Process> processes;

    public LoggerImplementation() {
        this.processes = new ArrayList<>();
    }

    @Override
    public void start(String processId) {
        processes.add(new Process(processId,System.currentTimeMillis()));

    }

    @Override
    public void end(String processId) {
        processes.get(); // issue with search

    }

    @Override
    public void poll() {

    }
}
