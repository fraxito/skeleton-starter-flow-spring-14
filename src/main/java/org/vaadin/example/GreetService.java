package org.vaadin.example;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.google.gson.Gson;
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

    public ArrayList<Character> getCharList(String tipo) throws URISyntaxException, IOException, InterruptedException {
        API api = new API();
        String resultsAPI = api.getCharacterList(tipo);
        Gson gson = new Gson();
        CharacterList charList = gson.fromJson(resultsAPI, CharacterList.class);
        return charList.getResults();
    }
}
