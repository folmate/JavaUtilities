package io.folmate.javautilities.log;

public interface LogEntry<T> {
    String header();
    String value();
    T getPayload();
    void setPayload(T value);
    default void modifyPayload(PayloadModifier<T> modifier){
        setPayload(modifier.modify(getPayload()));
    }
    LogOptions getOptions();
}
