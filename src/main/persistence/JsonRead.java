package persistence;

import model.Customer;
import model.Gpu;
import model.Parts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// A reader that reads a customer from JSON data stored in a file
// Citation: JsonSerializationDemo
public class JsonRead {
    private String location;

    // EFFECTS: constructs read to read from source file
    public JsonRead(String location) {
        this.location = location;
    }


    // EFFECTS: reads customer from file and returns it;
    // throws IOException if an error occurs when reading data.
    public Customer read() throws IOException {
        String jsonData = readFile(location);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomer(jsonObject);
    }


    // EFFECTS: reads source file as string and returns it.
    private String readFile(String location) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(location), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }


    // MODIFIES: c
    // EFFECTS: goes through the lift of each component of the customers part and sends
    // them through to addItem.
    private void addCart(Customer c, JSONObject jsonObject) {
        JSONArray partName = jsonObject.getJSONArray("partName");
        JSONArray model = jsonObject.getJSONArray("model");
        JSONArray id = jsonObject.getJSONArray("id");
        JSONArray price = jsonObject.getJSONArray("price");

        for (int i = 0; i < partName.length(); i++) {
            addPartName(c, partName.get(i), model.get(i), id.get(i), price.get(i));
        }

    }

    // MODIFIES: c
    // EFFECTS: convert each JSON object to a string / int respectively and adds them as
    // a part, which they are then added to the customer cart.
    private void addPartName(Customer c, Object p, Object m, Object i, Object pr) {
        String name = p.toString();
        String model = m.toString();
        int id = Integer.parseInt(i.toString());
        int price = Integer.parseInt(pr.toString());
        Parts part = new Gpu(model, name, id, price);
        c.addToCart(part);
    }


    // EFFECTS: parses customer from JSON object and returns it.
    private Customer parseCustomer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String pass = jsonObject.getString("pass");
        Customer c = new Customer(name, pass, new ArrayList<>(), 0);
        addCart(c, jsonObject);
        return c;
    }


}
