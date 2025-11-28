package com.bookstoreswing.utils;

import com.bookstoreswing.model.User;

public class Validator {

    /** Validates basic customer object */
    public static boolean validateCustomer(User u){
        if(u == null) return false;
        if(u.getName() == null || u.getName().trim().isEmpty()) return false;
        return true;
    }

    /** NEW: validates email or phone */
    public static boolean isValidContact(String contact){
        if (contact == null || contact.trim().isEmpty()) return false;

        // simple email check
        if (contact.contains("@") && contact.contains("."))
            return true;

        // simple phone check (only digits, min length 8)
        if (contact.matches("[0-9]{8,15}"))
            return true;

        return false;
    }
}
