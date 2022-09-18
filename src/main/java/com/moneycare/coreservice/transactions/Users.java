package com.moneycare.coreservice.transactions;

import com.moneycare.coreservice.model.ApprovalRequest;
import com.moneycare.coreservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class Users implements CommonStore {
    static Map<String, User> users = new HashMap<>();
    private static Users usersInstance = null;
    public static Users getUsersInstance() {

        if (usersInstance == null) {
            usersInstance = new Users();
        }
        return usersInstance;
    }


    public int size() {
        return users.size();
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }

    public boolean containsKey(Object key) {
        return users.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return users.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return users.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public User get(String key) {
        return users.get(key);
    }

    public User put(String key, User value) {

        return users.putIfAbsent(key, value);
    }

    public User remove(Object key) {
        return users.remove(key);
    }


    public void putAll(Map m) {
        users.putAll(m);
    }


    public void clear() {
        users.clear();
    }

    public Set keySet() {
        return users.keySet();
    }

    public Collection values() {
        return users.values();
    }

    public Set<Map.Entry<String, User>> entrySet() {
        return users.entrySet();
    }

    public void putIfAbsent(String user,  User user1) {
        users.putIfAbsent(user,user1);

    }
}
