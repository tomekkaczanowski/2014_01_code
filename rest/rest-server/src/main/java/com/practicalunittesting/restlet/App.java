package com.practicalunittesting.restlet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

public class App extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("http://localhost:8182/user/{id}", UserRestlet.class);
        router.attach("http://localhost:8182/users", UsersRestlet.class);
        return router;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(Protocol.HTTP, 8182);
        server.setNext(new App());
        server.start();

    }
}
