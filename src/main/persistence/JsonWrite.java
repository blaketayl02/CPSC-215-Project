package persistence;

import model.Customer;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes the customer as a JSON file.
// Citation: JsonSerializationDemo
public class JsonWrite {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: constructs writer to write to path destination as a file.
    public JsonWrite(String path) {
        this.path = path;
    }


    // MODIFIES: this
    // EFFECTS: writes customer as a JSON object to file.
    public void write(Customer customer) {
        JSONObject json = customer.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }


    // MODIFIES: this
    // EFFECTS: opens writer;
    // throws FileNotFoundException of path of the file
    // can't be accessed for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }


    // MODIFIES: this
    // EFFECTS: writes string to file.
    private void saveToFile(String json) {
        writer.print(json);
    }

}
