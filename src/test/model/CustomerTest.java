package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {
    private Customer blake;
    private List<Parts> cart;
    private Parts gpu1;


    @BeforeEach
    void setup() {
        cart = new ArrayList<>();
        blake = new Customer("blake", "1234Pass", cart, 0);
        gpu1 = new Gpu("Nvidia", "GTX 1060", 1111, 5000);

    }

    @Test
    void testSetTotal() {
        assertEquals(0, blake.getTotal());
        blake.setTotal(400);
        assertEquals(400, blake.getTotal());

    }


    @Test
    void testCustomer() {
        assertEquals("blake", blake.getName());
        assertEquals("1234Pass", blake.getPassword());
        assertEquals(0, cart.size());
    }

    @Test
    void testGetCart() {
        assertEquals(new ArrayList<>(), blake.getCart());
        blake.addToCart(gpu1);
        List<String> test = blake.getCart();
        assertEquals(test, blake.getCart());
    }

    @Test
    void testAddToCart() {
        blake.addToCart(gpu1);
        List<String> test = new ArrayList<>();
        test.add(gpu1.getName());
        assertEquals(test, blake.getCart());
        assertEquals(1, blake.getCart().size());
    }

    @Test
    void testRemoveFromCart() {
        blake.addToCart(gpu1);
        assertEquals(1, blake.getCart().size());
        blake.removeFromCart(0);
        assertEquals(0, blake.getCart().size());
    }

    @Test
    void testGetTotal() {
        blake.addToCart(gpu1);
        assertEquals(5000, blake.getTotal());
        blake.addToCart(gpu1);
        assertEquals(10000, blake.getTotal());
    }

    @Test
    void testClaerCart() {
        blake.addToCart(gpu1);
        assertEquals(5000, blake.getTotal());
        blake.clearCart();
        assertEquals(0, blake.getTotal());
    }

    @Test
    void testRemoveFromCartPart() {
        blake.addToCart(gpu1);
        List<String> test = new ArrayList<>();
        test.add("GTX 1060");
        assertEquals(test, blake.getCart());
        blake.removeFromCartPart(gpu1);
        assertEquals(0, blake.getCart().size());
    }


}