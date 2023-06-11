package org.acme.security.jpa;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;


@Singleton
public class Startup {
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
//        User.deleteAll();
        EntityUsers.add("admin", "admin", "admin");
        EntityUsers.add("user", "user", "user");
    }
}