package com.moneycare.coreservice.transactions;

import com.moneycare.coreservice.model.ApprovalRequest;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@Component
public class PendingRequests implements CommonStore {
    static Map<String, ApprovalRequest> pendingRequests = new HashMap<>();
    private static PendingRequests pendingRequestsInstance = null;
    public static PendingRequests getPendingRequestsInstance() {

        if (pendingRequestsInstance == null) {
            pendingRequestsInstance = new PendingRequests();
        }
        return pendingRequestsInstance;
    }


    public int size() {
        return pendingRequests.size();
    }

    public boolean isEmpty() {
        return pendingRequests.isEmpty();
    }

    public boolean containsKey(Object key) {
        return pendingRequests.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return pendingRequests.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return pendingRequests.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public ApprovalRequest get(String key) {
        return pendingRequests.get(key);
    }

    public ApprovalRequest put(String key, ApprovalRequest value) {

        return pendingRequests.putIfAbsent(key, value);
    }

    public ApprovalRequest remove(Object key) {
        return pendingRequests.remove(key);
    }


    public void putAll(Map m) {
        pendingRequests.putAll(m);
    }


    public void clear() {
        pendingRequests.clear();
    }

    public Set keySet() {
        return pendingRequests.keySet();
    }

    public Collection values() {
        return pendingRequests.values();
    }

    public Set<Map.Entry<String, ApprovalRequest>> entrySet() {
        return pendingRequests.entrySet();
    }

    public void putIfAbsent(String user,  ApprovalRequest user1) {
        pendingRequests.putIfAbsent(user,user1);

    }
}
