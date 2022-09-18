package com.moneycare.coreservice.transactions;


import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class UserCredentials implements CommonStore {
    static Map<String, String> userCredentials = new HashMap<>();
    private static UserCredentials userCredentialsInstance = null;
    public static UserCredentials getUserCredentialsInstance() {

        if (userCredentialsInstance == null) {
            userCredentialsInstance = new UserCredentials();
        }
        return userCredentialsInstance;
    }
    public int size() {
        return userCredentials.size();
    }

    public boolean isEmpty() {
        return userCredentials.isEmpty();
    }

    public boolean containsKey(Object key) {
        return userCredentials.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return userCredentials.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return userCredentials.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public String get(String key) {
        return userCredentials.get(key);
    }

    public String put(String key, String value) {
        return userCredentials.putIfAbsent(key, value);
    }

    public String remove(Object key) {
        return userCredentials.remove(key);
    }


    public void putAll(Map m) {
        userCredentials.putAll(m);
    }


    public void clear() {
        userCredentials.clear();
    }

    public Set keySet() {
        return userCredentials.keySet();
    }

    public Collection values() {
        return userCredentials.values();
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return userCredentials.entrySet();
    }

    public void putIfAbsent(String user,  String user1) {
        userCredentials.putIfAbsent(user,user1);

    }
}