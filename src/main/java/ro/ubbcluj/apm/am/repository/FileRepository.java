package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Identifiable;

import java.io.IOException;

public abstract class FileRepository<K, V extends Identifiable<K> & Comparable<V>> extends MemoryRepository<K, V> {
    protected String filePath;

    protected abstract void readFromFile() throws IOException;

    protected abstract void writeToFile() throws IOException;
}
