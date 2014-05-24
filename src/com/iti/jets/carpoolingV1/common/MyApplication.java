package com.iti.jets.carpoolingV1.common;

import android.app.Application;

public class MyApplication extends Application {

    private User currentUser = new User();

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurretUser(User currentUser) {
        this.currentUser = currentUser;
    }
}