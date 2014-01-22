package com.practicalunittesting.restlet;

import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

public class UsersRestlet extends ServerResource {

    @Put("json")
    public String store(JsonRepresentation userJson) throws JSONException {
        org.json.JSONObject userObj = userJson.getJsonObject();
        System.out.println(userObj.getInt("id"));
        System.out.println(userObj.getString("name"));
        return userObj.toString();
    }
}