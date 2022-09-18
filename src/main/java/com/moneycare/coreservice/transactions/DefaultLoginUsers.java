package com.moneycare.coreservice.transactions;



import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DefaultLoginUsers implements CommonStore {
    static Map<String, String> defaultUsers = new HashMap<>();
    private static DefaultLoginUsers defaultLoginUsersInstance = null;
    public static DefaultLoginUsers getDefaultLoginUsersInstance() {

        if (defaultLoginUsersInstance == null) {
            defaultLoginUsersInstance = new DefaultLoginUsers();
        }
        return defaultLoginUsersInstance;
    }
    public int size() {
        return defaultUsers.size();
    }

    public boolean isEmpty() {
        return defaultUsers.isEmpty();
    }

    public boolean containsKey(Object key) {
        return defaultUsers.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return defaultUsers.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return defaultUsers.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public String get(String key) {
        return defaultUsers.get(key);
    }

    public String put(String key, String value) {
        return defaultUsers.putIfAbsent(key, value);
    }

    public String remove(Object key) {
        return defaultUsers.remove(key);
    }


    public void putAll(Map m) {
        defaultUsers.putAll(m);
    }


    public void clear() {
        defaultUsers.clear();
    }

    public Set keySet() {
        return defaultUsers.keySet();
    }

    public Collection values() {
        return defaultUsers.values();
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return defaultUsers.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public void putIfAbsent(String user,  String user1) {
        defaultUsers.putIfAbsent(user,user1);

    }
}