package ro.ubbcluj.apm.am.repository;

import ro.ubbcluj.apm.am.domain.Identifiable;

import java.io.IOException;

public abstract class FileRepository<K, V extends Identifiable<K> & Comparable<V>> extends MemoryRepository<K, V> {
    protected String filePath;

    protected abstract void readFromFile() throws IOException;

    protected abstract void writeToFile() throws IOException;

    public V add(V value) {
        V addedValue = super.add(value);
        try {
            writeToFile();
            return addedValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(V value) {
        super.update(value);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteById(K id) {
        super.deleteById(id);
        try {
            writeToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
