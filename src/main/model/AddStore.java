package model;

import java.util.ArrayList;
import java.util.List;

// Creates the ability to create a store section for each part
public class AddStore {
    private List<Parts> list;

    public AddStore() {
        list = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a part to the stock
    public void addTo(Parts e) {
        list.add(e);
    }


    //MODIFIES: this
    //EFFECTS: adds a part from the stock
    public void removeFrom(int index) {
        list.remove(index);
    }

    //MODIFIES: this
    //EFFECTS: returns list of part names
    public List<String> giveList() {
        List<String> output = new ArrayList<>();
        int index = 1;
        for (Parts part : list) {
            output.add(index + ": " + part.getName());
            index++;
        }
        return output;
    }

    //EFFECTS: returns if the given part is in stock
    public boolean inStock() {
        return list.size() > 0;
    }


    //EFFECTS: returns part from given index
    public Parts getItem(int i) {
        return list.get(i);
    }


}
