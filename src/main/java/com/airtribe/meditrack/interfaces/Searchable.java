package com.airtribe.meditrack.interfaces;

import com.airtribe.meditrack.entity.id.EntityID;

import java.util.List;

public interface Searchable<T> {

    T search(EntityID id);

    List<T> search(String keyword);

    List<T> search(int keyword);

    default void printSearchHeader() {
        System.out.println("Searching records...");
    }
}