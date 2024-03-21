package io.folmate.javautilities.parameters;

public interface ParameterOptions {
    default String getComment(){return "#";}
    default String getSeparator(){return "=";}
    default String getDefault(){return "true";}
}
