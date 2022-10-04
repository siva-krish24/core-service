package com.moneycare.coreservice.transactions;

import com.moneycare.coreservice.model.ApprovalRequest;
import com.moneycare.coreservice.model.ApprovalWithdrawRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PendingWithdrawRequests implements CommonStore{

    public static Map<String, ApprovalWithdrawRequest> pendingWithdrawRequests = new HashMap<>();
    public static PendingWithdrawRequests pendingWithdrawRequestsInstance = null;

    public static PendingWithdrawRequests getPendingWithdrawRequestsInstance(){
        if (pendingWithdrawRequestsInstance == null){
            pendingWithdrawRequestsInstance = new PendingWithdrawRequests();
        }
        return pendingWithdrawRequestsInstance;
    }


    @Override
    public int size() {
        return pendingWithdrawRequests.size();
    }

    @Override
    public boolean isEmpty() {
        return pendingWithdrawRequests.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return pendingWithdrawRequests.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return pendingWithdrawRequests.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return pendingWithdrawRequests.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    public ApprovalWithdrawRequest get(String key) {
        return pendingWithdrawRequests.get(key);
    }

    public ApprovalWithdrawRequest put(String key, ApprovalWithdrawRequest value) {

        return pendingWithdrawRequests.putIfAbsent(key, value);
    }

    public ApprovalWithdrawRequest remove(Object key) {
        return pendingWithdrawRequests.remove(key);
    }


    @Override
    public void putAll(Map m) {
        pendingWithdrawRequests.putAll(m);
    }

    @Override
    public void clear() {
        pendingWithdrawRequests.clear();
    }

    @Override
    public Set keySet() {
        return pendingWithdrawRequests.keySet();
    }

    @Override
    public Collection values() {
        return pendingWithdrawRequests.values();
    }

    @Override
    public Set<Map.Entry<String, ApprovalWithdrawRequest>> entrySet() {
        return pendingWithdrawRequests.entrySet();
    }


    public void putIfAbsent(String user,  ApprovalWithdrawRequest user1) {
        pendingWithdrawRequests.putIfAbsent(user,user1);

    }
}

