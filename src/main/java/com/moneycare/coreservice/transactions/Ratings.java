package com.moneycare.coreservice.transactions;

import com.moneycare.coreservice.model.Rating;
import com.moneycare.coreservice.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ratings implements CommonStore{

    public static Map<String, Rating> ratings = new HashMap<>();

    @Override
    public int size() {
        return ratings.size();
    }

    @Override
    public boolean isEmpty() {
        return ratings.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return ratings.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return ratings.containsValue(value);
    }

    public Rating get(Object key) {
        return ratings.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public Rating put(String key, Rating value) {
        return ratings.putIfAbsent(key,value);
    }

    @Override
    public Object remove(Object key) {
        return ratings.remove(key);
    }

    @Override
    public void putAll(Map m) {
        ratings.putAll(m);
    }

    @Override
    public void clear() {
        ratings.clear();
    }

    @Override
    public Set keySet() {
        return ratings.keySet();
    }

    @Override
    public Collection values() {
        return ratings.values();
    }

    @Override
    public Set<Map.Entry<String, Rating>> entrySet() {
        return ratings.entrySet();
    }

    public void putIfAbsent(String user,  Rating rating1) {
        ratings.putIfAbsent(user,rating1);

    }
}
