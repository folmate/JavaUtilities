package io.folmate.javautilities.log.hierarchy;

import io.folmate.javautilities.log.LogEntry;
import io.folmate.javautilities.log.LogOptions;
import io.folmate.javautilities.log.PayloadModifier;

import java.util.ArrayList;
import java.util.List;

public class TreeLogWrapper<L extends LogEntry<T>,T> implements LogEntry<T>{
    private final L logger;
    private final List<TreeLogWrapper<?,?>> childrens = new ArrayList<>();


    TreeLogWrapper(TreeLogWrapper<?,?> parent, L logger) {
        this.logger = logger;
        parent.addChildren(this);
    }
    TreeLogWrapper(L logger) {
        this.logger = logger;
    }
    private void addChildren(TreeLogWrapper<?,?> child){
        childrens.add(child);
    }

    public String header(){
        StringBuilder builder = new StringBuilder();
        builder.append(logger.header());
        for(TreeLogWrapper<?,?> child : childrens){
            builder.append(logger.getOptions().getDelimiter());
            builder.append(child.header());
        }
        return builder.toString();
    }
    public String value(){
        StringBuilder builder = new StringBuilder();
        builder.append(logger.value());
        for(TreeLogWrapper<?,?> child : childrens){
            builder.append(logger.getOptions().getDelimiter());
            builder.append(child.value());
        }
        return builder.toString();
    }

    @Override
    public T getPayload() {
        return logger.getPayload();
    }

    @Override
    public void setPayload(T value) {
        logger.setPayload(value);
    }

    @Override
    public void modifyPayload(PayloadModifier<T> modifier) {
        logger.modifyPayload(modifier);
    }

    @Override
    public LogOptions getOptions() {
        return logger.getOptions();
    }
    public L getLogger(){
        return logger;
    }
}
