package io.folmate.javautilities.log;

import java.util.ArrayList;
import java.util.List;

public class TreeLogEntry<T> extends LogEntry<T>{
    final List<TreeLogEntry<?>> childrens = new ArrayList<>();

    TreeLogEntry(TreeLogEntry<?> parent, String name, T initial, LogOptions options) {
        super(name, initial, options);
        parent.addChildren(this);
    }
    TreeLogEntry(TreeLogEntry<?> parent, String name, T initial) {
        super(name, initial);
        parent.addChildren(this);
    }
    private void addChildren(TreeLogEntry<?> child){
        childrens.add(child);
    }
    TreeLogEntry(String name, T initial, LogOptions options) {
        super(name, initial, options);
    }
    TreeLogEntry(String name, T initial) {
        super(name, initial);
    }
    public String header(){
        StringBuilder builder = new StringBuilder();
        builder.append(super.header());
        for(TreeLogEntry<?> child : childrens){
            builder.append(options.getDelimiter());
            builder.append(child.header());
        }
        return builder.toString();
    }
    public String value(){
        StringBuilder builder = new StringBuilder();
        builder.append(super.value());
        for(TreeLogEntry<?> child : childrens){
            builder.append(options.getDelimiter());
            builder.append(child.value());
        }
        return builder.toString();
    }
}
