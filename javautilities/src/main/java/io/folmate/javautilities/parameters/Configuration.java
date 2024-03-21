package io.folmate.javautilities.parameters;

import io.folmate.javautilities.log.LogOptions;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Configuration {
    public Configuration(String[] args, ParameterOptions options) {
        data = Arrays.stream(args)
                .filter(entry -> entry.startsWith(options.getComment()))
                .map(entry ->{
                    String[] payload = entry.split(options.getSeparator(), 1);
                    return switch (payload.length) {
                        case 1 -> Map.entry(payload[0], options.getDefault());
                        case 2 -> Map.entry(payload[0], payload[1]);
                        default -> throw new IllegalArgumentException("Invalid entry in args: $entry");
                    };
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    final Map<String,String> data;

    public String asString(String param){
        return data.get(param);
    }
    public String asString(String param, String defaultValue){
        return data.getOrDefault(param,defaultValue);
    }
    public int asInt(String param){
        return Integer.parseInt(data.get(param));
    }
    public int asInt(String param, int defaultValue){
        try{
            return asInt(param);
        } catch (NumberFormatException e){
            return defaultValue;
        }
    }
    public boolean asBoolean(String param){
        return Boolean.parseBoolean(data.get(param));
    }
    public boolean isDefined(String param){
        return data.containsKey(param);
    }
}