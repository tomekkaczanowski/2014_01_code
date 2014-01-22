package com.practicalunittesting.restlet;

import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class UserRestlet extends ServerResource {

    private int id;


    @Override
    protected void doInit() throws ResourceException {
        this.id = Integer.parseInt(getRequestAttributes().get("id").toString());
    }

    @Get("json")
    public String toString() {
        User u = new User();
        if (id >= 1 && id <= 5) {
            u.setId("" + id);
            u.setName("user " + id);
        }
        else {
            throw new ResourceException(404);
        }
        return u.toJSON().toString();
    }
}