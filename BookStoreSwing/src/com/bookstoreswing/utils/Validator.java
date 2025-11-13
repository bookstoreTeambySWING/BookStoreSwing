package com.bookstoreswing.utils;

import com.bookstoreswing.model.User;

/**
 * Validates user data
 */
public class Validator {
    public static boolean validateCustomer(User u){
        if(u == null) return false;
        if(u.getName() == null || u.getName().trim().isEmpty()) return false;
        return true;
    }
}
