package io.folmate.javautilities.log.loggers;

import io.folmate.javautilities.log.LogOptions;

public class RuntimeLogEntry extends SimpleLogEntry<Long>{
    public RuntimeLogEntry(String name, LogOptions options) {
        super(name, 0L, options);
    }
    public <R> R measureAndIncrement(Function0<R> function){
        long start = System.nanoTime();
        try{
            return function.execute();
        } finally {
            long end = System.nanoTime();
            this.modifyPayload(count -> count+(end-start));
        }
    }
    public <R> R measure(Function0<R> function){
        long start = System.nanoTime();
        try{
            return function.execute();
        } finally {
            long end = System.nanoTime();
            this.setPayload(end-start);
        }
    }
}
