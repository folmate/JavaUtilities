package io.folmate.javautilities.log.loggers;

import io.folmate.javautilities.log.LogEntry;
import io.folmate.javautilities.log.LogOptions;
import io.folmate.javautilities.log.PayloadModifier;

public class SimpleLogEntry<T> implements LogEntry<T> {
    private final LogOptions options;
    private T payload;
    final String name;
    public SimpleLogEntry(String name, T initial, LogOptions options){
        payload = initial;
        this.name = name;
        this.options = options;
    }
    public SimpleLogEntry(String name, T initial){
        payload = initial;
        this.name = name;
        this.options = new LogOptions(){};
    }
    public String header(){
        return name;
    }
    public String value(){
        StringBuilder builder = new StringBuilder();
        String value = payload.toString();

        boolean hasDelimiter = value.contains(options.getDelimiter());
        if(hasDelimiter){
            builder.append("\"");
        }
        if (value.contains("\"")) {
            builder.append(value
                    .replace("\"", "\"\"")
            );
        }
        if(hasDelimiter){
            builder.append("\"");
        }
        return builder.toString();
    }
    public T getPayload(){
        return payload;
    }
    public void setPayload(T payload){
        this.payload = payload;

    }
    public void modifyPayload(PayloadModifier<T> modifier){
        payload = modifier.modify(payload);
    }

    public LogOptions getOptions(){return options;}
}
