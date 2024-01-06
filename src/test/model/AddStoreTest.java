package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddStoreTest {
    private Parts gpu;
    private Parts gpu2;
    private List<Parts> list;
    private AddStore store;

    @BeforeEach
    void setup() {
        store = new AddStore();
        gpu = new Gpu("Nvidia", "GTX 1060", 1111, 1000);
        gpu2 = new Gpu("Nvidia", "GTX 1080", 2222, 500);
        list = new ArrayList<>();
    }


    @Test
    void testAddTo() {
        List<Parts> test = new ArrayList<>();
        test.add(gpu);
        store.addTo(gpu);
        assertEquals(test.get(0), store.getItem(0));

    }

    @Test
    void testGiveList() {
        store.addTo(gpu);
        List<String> test = store.giveList();
        assertEquals(test, store.giveList());
        assertEquals(1, test.size());
    }

    @Test
    void testInStock() {
        assertFalse(store.inStock());
        store.addTo(gpu);
        assertTrue(store.inStock());

    }

    @Test
    void testGetItem() {
        store.addTo(gpu);
        assertEquals(gpu, store.getItem(0));
    }

    @Test
    void testRemoveFrom() {
        store.addTo(gpu);
        assertTrue(store.inStock());
        store.removeFrom(0);
        assertFalse(store.inStock());
    }

}


