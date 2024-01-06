package model;

import org.json.JSONWriter;
import org.junit.jupiter.api.Test;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriteTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            Customer customer = new Customer("blake", "1234", new ArrayList<>(), 0);
            JsonWrite writer = new JsonWrite("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // should pass!
        }
    }


    @Test
    void testWriterEmptyCustomer() {
        try {
            Customer customer = new Customer("blake", "1234", new ArrayList<>(), 0);
            JsonWrite writer = new JsonWrite("./data/testWriterEmptyCustomer1.json");
            writer.open();
            writer.write(customer);
            writer.close();

            JsonRead read = new JsonRead("./data/testWriterEmptyCustomer1.json");
            customer = read.read();
            assertEquals("blake", customer.getName());
            assertEquals(0, customer.getTotal());
            assertEquals("1234", customer.getPassword());
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown");
        }
    }


    @Test
    void testWriterGeneralCustomer() {
        try {
            Parts gpu1 = new Gpu("Nvidia", "GTX 1060", 1111, 5000);
            Customer customer = new Customer("jim", "3214", new ArrayList<>(), 0);
            customer.addToCart(gpu1);
            JsonWrite writer = new JsonWrite("./data/testWriterGeneralCustomer1.json");
            writer.open();
            writer.write(customer);
            writer.close();

            JsonRead reader = new JsonRead("./data/testWriterGeneralCustomer1.json");
            customer = reader.read();
            assertEquals("jim", customer.getName());
            List<String> test = new ArrayList<>();
            test.add("GTX 1060");
            assertEquals(test, customer.getCart());
            assertEquals(5000, customer.getTotal());
        } catch (IOException e) {
            fail("Should not have been thrown!");
        }
    }



}
