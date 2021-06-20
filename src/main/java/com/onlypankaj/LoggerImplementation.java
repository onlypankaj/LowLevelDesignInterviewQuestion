package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LoggerImplementation implements LogClient {
    private final TreeMap<Long, Process> queue;
    private final Map<String, Process> processes;

    public LoggerImplementation() {
        this.queue = new TreeMap<>(Comparator.comparingLong(startTime -> startTime));
        this.processes = new HashMap<>();
    }

    @Override
    public void start(String processId) {
        final long now = System.currentTimeMillis();
        final Process process = new Process(processId, now);
        processes.put(processId, process);
        queue.put(now,process);

    }

    @Override
    public void end(String processId) {
        final long now = System.currentTimeMillis();
        processes.get(processId).setEndTime(now); // List issue with search, resolved with map
        //queue update is not required as update is at object level, so will be updated inside queue as well.
    }

    @Override
    public void poll() {
        //Issue we need to find process which has smallest start time
        //This is difficult with map as there is no ordering
        //This can be resolved with TreeMap, which has order

        if (queue.isEmpty()){
            System.out.println("Queue is empty");
            return;
        }
        final Process process = queue.firstEntry().getValue();

        //Remove Process
        if(process.getEndTime()!=-1){
            System.out.println(process.getId() + " started at " + process.getStartTime() + " and ended at "+ process.getEndTime());
            queue.pollFirstEntry();
            processes.remove(process.getId());
        }else {
            System.out.println("No Completed Task in queue size: " + queue.size());
        }

    }
}
