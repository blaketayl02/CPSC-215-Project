package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartsTest {
    private Parts gpu;
    private Parts cpu;
    private Parts ram;



    @BeforeEach
    void setup() {
        gpu = new Gpu("Nvidia", "GTX 1060", 1111, 1000);
        cpu = new Cpu("Intel", "i7 7700k", 2222, 1400);
        ram = new Ram("Tradesman", "16GB DDR4", 3333, 100);

    }


    @Test
    void testModel() {
        assertEquals("Nvidia", gpu.getModel());
        assertEquals("Intel", cpu.getModel());
        assertEquals("Tradesman", ram.getModel());

    }

    @Test
    void testName() {
        assertEquals("GTX 1060", gpu.getName());
        assertEquals("i7 7700k", cpu.getName());
        assertEquals("16GB DDR4", ram.getName());

    }

    @Test
    void testId() {
        assertEquals(1111, gpu.getId());
        assertEquals(2222, cpu.getId());
        assertEquals(3333, ram.getId());

    }

    @Test
    void testPrice() {
        assertEquals(1000, gpu.getPrice());
        assertEquals(1400, cpu.getPrice());
        assertEquals(100, ram.getPrice());

    }


}
