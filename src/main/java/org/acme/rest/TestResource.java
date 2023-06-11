package org.acme.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import org.jboss.resteasy.reactive.RestPath;
import static io.quarkus.arc.ComponentsProvider.LOG;

import java.util.HashMap;
import java.util.Map;

@Path("/health")
public class TestResource {

    @GET
    @Path("/{check}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> isServiceAlive(@RestPath String check, @Context SecurityContext securityContext) {
        LOG.infof("path value: %s", check);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("server status", "server is alive");
        resultMap.put("logged in username", String.valueOf(securityContext.getUserPrincipal() != null?
                securityContext.getUserPrincipal().getName(): ""));
        return resultMap;
    }
}
