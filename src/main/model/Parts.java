package model;


// Represents a part that the store has in stock, can be one of:
// gpu, cpu, ram.
public abstract class Parts {
    private String name;
    private int price;
    private int id;
    private String model;

    //MODIFIES: this
    //EFFECTS: creates a Part object with model, name, id, and price.
    public Parts(String model, String name, int id, int price) {
        this.name = name;
        this.price = price;
        this.model = model;
        this.id = id;
    }


    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getId() {
        return id;
    }




}
