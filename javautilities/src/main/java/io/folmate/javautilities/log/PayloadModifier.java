package io.folmate.javautilities.log;

public interface PayloadModifier<T> {
    T modify(T old);
}
