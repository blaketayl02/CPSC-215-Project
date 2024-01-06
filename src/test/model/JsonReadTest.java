package model;

import org.junit.jupiter.api.Test;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReadTest extends JsonTest{



    @Test
    void testReaderNonExistentFile() {
        JsonRead reader = new JsonRead("./data/noFile.json");
        try {
            Customer customer = reader.read();
            fail("IOException expected!");
        } catch (IOException e) {
            // should pass!
        }
    }

    @Test
    void testWriterEmptyCustomer() {
        JsonRead reading = new JsonRead("./data/testReaderEmptyCustomer.json");
        try {
            Customer customer = reading.read();
            assertEquals("blake02", customer.getName());
            assertEquals("password123", customer.getPassword());
        } catch (IOException e) {
            fail("Couldn't read from file!");
        }
    }

    @Test
    void testReaderGeneralCustomer() {
        JsonRead reader = new JsonRead("./data/testReaderGeneralCustomer.json");
        try {
            Customer customer = reader.read();
            assertEquals("blake", customer.getName());
            List<String> parts = customer.getCart();
            assertEquals(1, customer.getCart().size());
            assertEquals(parts, customer.getCart());
        } catch (IOException e) {
            fail("Couldn't read this file!");
        }
    }

}
