package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

@Service
public class GreetService implements Serializable {

    public String greet(String name) {
        if (name == null || name.isEmpty()) {
            return "Hello anonymous user";
        } else {
            return "Hello " + name;
        }
    }

    public String getSWAPI(String tipo, int id) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        return api.getCharacter(tipo, id);
    }

}
