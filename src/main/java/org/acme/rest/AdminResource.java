package org.acme.rest;

import io.quarkus.runtime.util.StringUtil;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.security.jpa.EntityUsers;
import org.jboss.resteasy.reactive.*;

import java.util.List;

import static io.quarkus.arc.ComponentsProvider.LOG;

@Path("/admin")
public class AdminResource {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntityUsers> adminResource(@RestPath String path,
                                           @RestMatrix String variant,
                                           @RestQuery String username,
                                           @RestHeader("Authorization") String authorization) {
        LOG.infof("Request path %s query parameters - user %s authorization header %s", path, username, authorization);
        List<EntityUsers> entityUsersList = null;
        if (StringUtil.isNullOrEmpty(username))
            entityUsersList = EntityUsers.findAll().list();
        else
            entityUsersList = EntityUsers.find("username",username).list();

        return entityUsersList;
    }

    @POST
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntityUsers> adminResource(@RestPath String path,
                                           @RestMatrix String variant,
                                           @RestForm String username,
                                           @RestForm String password,
                                           @RestForm String role,
                                           @RestHeader("Authorization") String authorization) {
        LOG.infof("Request path %s query parameters - user %s authorization header %s", path, username, authorization);
        EntityUsers.add(username, password, role);
        List<EntityUsers> entityUsersList = null;
        entityUsersList = EntityUsers.findAll().list();

        return entityUsersList;
    }
}