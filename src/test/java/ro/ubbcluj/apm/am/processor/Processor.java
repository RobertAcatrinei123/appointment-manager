package ro.ubbcluj.apm.am.processor;

@FunctionalInterface
public interface Processor<T, E> {
    T process(E element);
}
