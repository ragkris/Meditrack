package com.airtribe.meditrack.util;


import java.util.Collection;
import java.util.LinkedHashMap;

public class DataStore<T> {

    private LinkedHashMap<String, T> store = new LinkedHashMap<>();

    public void add(String id, T obj) {
        store.put(id, obj);
    }

    public T get(String id) {
        return store.get(id);
    }


    public Collection<T> getAll() {
        System.out.println("total appt" + store.size());
        return store.values();
    }

    public void remove(String id) {
        store.remove(id);
    }

    public boolean exists(String id) {
        return store.containsKey(id);
    }
}