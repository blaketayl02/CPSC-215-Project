package model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkCustomer(String name, String pass, List<Parts> cart, Customer customer) {
        assertEquals(name, customer.getName());
        assertEquals(pass, customer.getPassword());
    }

}
