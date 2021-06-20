package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class LoggerImplementation implements LogClient {
    private final TreeMap<Long, Process> queue;
    private final Map<String, Process> processes;
    private final List<CompletableFuture> futures;

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
    public String poll() {
        //Issue we need to find process which has smallest start time
        //This is difficult with map as there is no ordering
        //This can be resolved with TreeMap, which has order
        final CompletableFuture result = new CompletableFuture<String>();

        if (queue.isEmpty()){
            System.out.println("Queue is empty");
            // STAGE2 requirement: WAIT for end
             futures.add(result);
            return null;
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
        // STAGE2 requirement: WAIT for end of first process.
        try {
            return (String) result.get(3, TimeUnit.SECONDS);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
