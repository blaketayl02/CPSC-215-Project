package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.WriteFile;

import java.util.ArrayList;
import java.util.List;

// Represents a customer who has the ability to interact with the store.
public class Customer implements WriteFile {

    private String username;     //account username
    private String password;     //account password
    private List<Parts> cart;  //account cart
    private int total;


    //REQUIRES: valid username and password. Strings > 0
    //MODIFIES: this
    //EFFECTS: creates a customer with username, and password
    public Customer(String usr, String pass, List<Parts> cart, int total) {
        this.username = usr;
        this.password = pass;
        this.cart = cart;
        this.total = total;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    // MODIFIES: this
    // EFFECTS: clears customer cart fully once customer has placed order in.
    public void clearCart() {
        EventLog.getInstance().logEvent((
                new Event("Successfully Placed Order in! " + getCart())));
        cart.clear();
        total = 0;

    }


    //MODIFIES: this
    //EFFECTS: removes a given part from cart.
    public void removeFromCartPart(Parts part) {
        cart.remove(part);
        this.total = total - part.getPrice();
        EventLog.getInstance().logEvent((new Event("Item removed! " + part.getName())));
    }


    //MODIFIES: this
    //EFFECTS: returns a list of part names in the given customers cart.
    public List<String> getCart() {
        List<String> output = new ArrayList<>();
        for (Parts part : cart) {
            output.add(part.getName());
        }
        return output;
    }


    //REQUIRES: valid part
    //MODIFIES: this
    //EFFECTS: adds part to customer cart
    public void addToCart(Parts item) {
        cart.add(item);
        total = total + item.getPrice();
        EventLog.getInstance().logEvent((new Event("Item added! " + item.getName())));
    }


    //REQUIRES: cart has at least one part
    //MODIFIES: this
    //EFFECTS: removes part from customer cart
    public void removeFromCart(int index) {
        total = total - cart.get(index).getPrice();
        cart.remove(index);


    }

    //MODIFIES: this
    //EFFECTS: creates a customer as a JSON Object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", username);
        json.put("pass", password);
        json.put("model", getModelToJson());
        json.put("partName", getNameToJson());
        json.put("id", getIdToJson());
        json.put("price", getPriceToJson());
        //json.put("total", total);
        return json;
    }


    //MODIFIES: this
    //EFFECTS: creates a JSON Array with the part models
    private JSONArray getModelToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Parts p : cart) {
            jsonArray.put(p.getModel());
        }
        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: creates a JSON Array with the part names
    private JSONArray getNameToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Parts p : cart) {
            jsonArray.put(p.getName());
        }
        return jsonArray;
    }

    //MODIFIES: this
    //EFFECTS: creates a JSON Array with the part IDs
    private JSONArray getIdToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Parts p : cart) {
            jsonArray.put(p.getId());
        }
        return jsonArray;
    }


    //MODIFIES: this
    //EFFECTS: creates a JSON Array with the part prices
    private JSONArray getPriceToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Parts p : cart) {
            jsonArray.put(p.getPrice());
        }
        return jsonArray;
    }


}
