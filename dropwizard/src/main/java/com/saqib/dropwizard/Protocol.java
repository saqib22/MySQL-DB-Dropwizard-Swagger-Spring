package com.saqib.dropwizard;

import com.hashirahmad.spring.model.Person;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Protocol {
    public static final String url = "http://localhost:18080/person";
    private Client client;

    public Protocol() {
        this.client = ClientBuilder.newClient();
    }

    public String delete(long id, String username, String password) {
        byte[] authHeader = Base64.getEncoder().encode((username + '|' + password).getBytes());
        Response response = null;
        try {
            response = client.target(url)
                    .path(Long.toString(id))
                    .request()
                    .header(HttpHeaders.AUTHORIZATION, new String(authHeader, "UTF-8"))
                    .delete();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return response.readEntity(String.class);
    }

    public Object get(long id, String username, String password) {
        byte[] authHeader = Base64.getEncoder().encode((username + '|' + password).getBytes());
        Response response = null;
        try {
            response = client.target(url)
                    .path(Long.toString(id))
                    .request()
                    .header(HttpHeaders.AUTHORIZATION, new String(authHeader, "UTF-8"))
                    .get();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (response.getStatus() != 200)
            return response.readEntity(String.class);
        else
            return response.readEntity(Person.class);
    }

    public String post(Person person) {
        Response response = client.target(url)
                .request()
                .post(Entity.entity(person, MediaType.APPLICATION_JSON));
        return response.readEntity(String.class);
    }
}
