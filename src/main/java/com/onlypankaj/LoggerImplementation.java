package com.onlypankaj;

import com.onlypankaj.model.Process;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class LoggerImplementation implements LogClient {
    private final TreeMap<Long, Process> queue;
    private final Map<String, Process> processes;
    private final List<CompletableFuture> futures;

    public LoggerImplementation() {
        this.queue = new TreeMap<>(Comparator.comparingLong(startTime -> startTime));
        this.processes = new HashMap<>();
        this.futures = new CopyOnWriteArrayList<>();        //List future
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

        //Remove Process
        if(!queue.isEmpty() && queue.firstEntry().getValue().getEndTime()!=-1){
            final Process process = queue.firstEntry().getValue();
            System.out.println(process.getId() + " started at " + process.getStartTime() + " and ended at "+ process.getEndTime());
            queue.pollFirstEntry();
            processes.remove(process.getId());
        } else {
            //Future Added
            futures.add(result);
        }

        //Wait and Wake up condition
        try {
            return (String) result.get(3, TimeUnit.SECONDS);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
