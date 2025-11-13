package com.bookstoreswing.data;

import com.bookstoreswing.model.User;

/**
 * Placeholder for user data - currently single demo user
 */
public class UserData {
    public static User getDemoUser() {
        return new User("guest","Guest User","");
    }
}
