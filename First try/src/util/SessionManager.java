/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import models.Client;

/**
 *
 * @author yacin
 */
public final class SessionManager {

    private Client user;
    private final static SessionManager INSTANCE = new SessionManager();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public Client getUser() {
        return user;
    }

    public void setUser(Client user) {
        this.user = user;
    }

    public void Logout() {
        setUser(null);
    }

    @Override
    public String toString() {
        return "SessionManager{" + "Client=" + user + '}';
    }

}
