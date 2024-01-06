package ui;

import model.*;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Store application
public class StoreApp {
    private Scanner input;
    private Customer acc;
    private Parts gpu1;
    private Parts gpu2;
    private Parts cpu2;
    private Parts cpu1;
    private Parts ram1;
    private AddStore cpu;
    private AddStore gpu;
    private AddStore ram;
    private List<Customer> orders;
    private List<Appointments> appointments;
    private Appointments app;
    private int total;
    private JsonWrite jsonWrite;
    private JsonRead jsonRead;
    private static final String JSON_STORE = "./data/customerCart.json";





    //MODIFIES: this
    //EFFECTS: runs the main methods for the store to run.
    public StoreApp() { // Source: TellerApp (edited)
        String command = null;
        initialStore();
        initialAccount();
        runStore();
    }

    //MODIFIES: AddStore, Gpu, Cpu, Parts, Appointments
    //EFFECTS: initializes the stock for the store, adds parts to their respective sections
    // and creates empty lists of orders and appointments to be filled.
    public void initialStore() {
        gpu = new AddStore();
        cpu = new AddStore();
        ram = new AddStore();
        gpu1 = new Gpu("nvidia","GTX 1060", 1111, 2222);
        gpu2 = new Gpu("nvidia", "GTX 3060", 22222, 3000);
        cpu1 = new Gpu("intel", "i7 6700k", 100, 1500);
        cpu2 = new Cpu("intel","i7 7700k", 300, 1000);
        ram1 = new Ram("Tradesman", "16GB DDR4", 222, 150);
        gpu.addTo(gpu1);
        gpu.addTo(gpu2);
        cpu.addTo(cpu2);
        cpu.addTo(cpu1);
        ram.addTo(ram1);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        orders = new ArrayList<>();
        appointments = new ArrayList<>();
        app = new Appointments();
        this.total = total;


    }


    //MODIFIES: Customer
    //EFFECTS: initializes customer account with password and username, and an empty cart.
    public void initialAccount() {
        acc = new Customer("blake02", "password123", new ArrayList<>(), total);
        jsonWrite = new JsonWrite(JSON_STORE);
        jsonRead = new JsonRead(JSON_STORE);

    }

    //MODIFIES: this
    //EFFECTS: runs the store, displays menu, and keeps the loop of the store going until user quits out.
    private void runStore() { // Source: TellerApp (edited)
        boolean keepGoing = true;
        String command = null;
       // displayLogin(command);
        displayMenu();
        while (keepGoing) {
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                menu(command);
            }
        }
        System.out.println("Thanks for Shopping!");
    }


    //MODIFIES: this
    //EFFECTS: brings user to respective section
    private void menu(String command) { // Source: TellerApp (edited)
        if (command.equals("parts")) {
            goParts(command);
        } else if (command.equals("appointments")) {
            goAppointments(command);
        } else if (command.equals("cart")) {
            goCart(command);
        } else if (command.equals("save")) {
            saveCustomer();
        } else if (command.equals("load")) {
            loadCustomer();
        } else {
            System.out.println("Please select a valid option...");
        }
    }

    //REQUIRES: there is a valid customer running application
    //MODIFIES: JsonWrite
    //EFFECTS:  saves all info about customer, including their cart. Prints saved cart.
    private void saveCustomer() {
        try {
            jsonWrite.open();
            jsonWrite.write(acc);
            jsonWrite.close();
            System.out.println("Saved " + acc.getCart());
            runStore();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save your cart!" + JSON_STORE);
        }
    }

    //REQUIRES: there is a valid customer running application
    //MODIFIES: JsonRead
    //EFFECTS:  loads the customer file, and displays their cart.
    private void loadCustomer() {
        try {
            acc = jsonRead.read();
            System.out.println("Restored " + acc.getCart());
            runStore();
        } catch (IOException e) {
            System.out.println("Unable to read file! " + JSON_STORE);
        }
    }

    //REQUIRES: there is at least one item within the cart in order to remove.
    //MODIFIES: Customers cart of parts
    //EFFECTS:  displays cart, allows user to remove an item
    private void goCart(String command) {
        System.out.println("Here is your cart: " + acc.getCart());
        System.out.println("Total Price: " + acc.getTotal());
        System.out.println("Would you like to remove an item? :");
        command = input.next();
        if (command.equals("yes")) {
            System.out.println("Please select the index you would like to remove starting from 1: ");
            command = input.next();
            acc.removeFromCart(Integer.parseInt(command) - 1);
            System.out.println("Success!");
            runStore();
        } else if (command.equals("no")) {
            placeOrder(command);
        } else {
            System.out.println("Please select valid option: ");
            runStore();
        }

    }

    //REQUIRES: there is at least one item in the cart to place order in
    //MODIFIES: Customers cart of parts and list of orders.
    //EFFECTS:  adds customers cart to the list of orders.
    private void placeOrder(String command) {
        System.out.println("Do you want to place this order in?");
        command = input.next();
        if (command.equals("yes")) {
            orders.add(acc);
            acc = new Customer(acc.getName(), acc.getPassword(), new ArrayList<>(), acc.getTotal());
            System.out.println("Success!");
            runStore();
        } else if (command.equals("no")) {
            runStore();
        } else {
            System.out.println("Please select valid option: ");
            placeOrder(command);
        }
    }

    //REQUIRES: the month, day, and time are valid dates.
    //MODIFIES: appointments
    //EFFECTS: adds an appointment to list of appointments
    private void goAppointments(String command) {
        System.out.println("Please type a date in which you would like to set up an appointment: ");
        command = input.next();
        app.setMonth(command);
        System.out.println("Please type the day of the month : ");
        command = input.next();
        app.setDay(Integer.parseInt(command));
        System.out.println("What time would you like the appointment? Please use 24hr time: (ex: 1400) ");
        command = input.next();
        app.setTime(Integer.parseInt(command));
        System.out.println("What is your issue? :");
        command = input.next();
        app.setIssue(command);
        appointments.add(app);
        System.out.println("Successfully added!");
        System.out.println(app.getMonth() + " " + app.getDay() + " " + app.getTime() + " " + app.getIssue());
        runStore();

    }


    //MODIFIES: this
    //EFFECTS: brings user to respective part
    private void goParts(String command) {
        System.out.println("---------------------------------");
        System.out.println("What are you looking for? :");
        System.out.println("gpu -> GPU");
        System.out.println("cpu -> CPU");
        System.out.println("ram -> RAM");
        command = input.next();
        if (command.equals("gpu")) {
            goGPU(command);
        } else if (command.equals("cpu")) {
            goCPU(command);
        } else if (command.equals("ram")) {
            goRam(command);
        } else if (command.equals("back")) {
            runStore();
        } else {
            System.out.println("Please select a valid option: ");
            runStore();
        }

    }


    //MODIFIES: customer cart, addstore
    //EFFECTS: adds part to customer cart when selected. Removes selected part from RAM store
    private void goRam(String command) {
        if (ram.inStock()) {
            System.out.println("Here are our RAM in stock: ");
            System.out.println(ram.giveList());
            System.out.println("---------------------------------");
            System.out.println("If you would like to add one to your cart, please select the number:");
            command = input.next();
            if (command.equals(command)) {
                acc.addToCart(ram.getItem(Integer.parseInt(command) - 1));
                ram.removeFrom(Integer.parseInt(command) - 1);
                System.out.println("Successfully Added!");
                goParts(command);
            } else if (command.equals("back")) {
                goParts(command);
            } else {
                System.out.println("Please select a valid option: ");
            }
        }
        System.out.println("Sorry, we're out of stock!");
        goParts(command);
    }


    //MODIFIES: customer cart, addstore
    //EFFECTS: adds part to customer cart when selected. Removes selected part from GPU store
    private void goGPU(String command) {
        if (gpu.inStock()) {
            System.out.println("Here are our GPUs in stock: ");
            System.out.println(gpu.giveList());
            System.out.println("---------------------------------");
            System.out.println("If you would like to add one to your cart, please select the number:");
            command = input.next();
            if (command.equals(command)) {
                acc.addToCart(gpu.getItem(Integer.parseInt(command) - 1));
                gpu.removeFrom(Integer.parseInt(command) - 1);
                System.out.println("Successfully Added!");
                goParts(command);
            } else if (command.equals("back")) {
                goParts(command);
            } else {
                System.out.println("Please select a valid option: ");
            }
        }
        System.out.println("Sorry, we're out of stock!");
        goParts(command);
    }



    //MODIFIES: customer cart, addstore
    //EFFECTS: adds part to customer cart when selected. Removes selected part from CPU store
    private void goCPU(String command) {
        if (cpu.inStock()) {
            System.out.println("Here are our CPUs in stock: ");
            System.out.println(cpu.giveList());
            System.out.println("---------------------------------");
            System.out.println("If you would like to add one to your cart, please select the number:");
            command = input.next();
            if (command.equals(command)) {
                acc.addToCart(cpu.getItem(Integer.parseInt(command) - 1));
                cpu.removeFrom(Integer.parseInt(command) - 1);
                System.out.println("Successfully Added!");
                goParts(command);
            } else if (command.equals("back")) {
                goParts(command);
            } else {
                System.out.println("Please select a valid option: ");
            }
        }
        System.out.println("Sorry, we're out of stock!");
        goParts(command);
    }





    //EFFECTS: displays menu
    private void displayMenu() { // Source: TellerApp (edited)
        System.out.println("What can we do for you?:");
        System.out.println("parts?");
        System.out.println("appointments?");
        System.out.println("cart?");
        System.out.println("save?");
        System.out.println("load?");
        System.out.println("---------------------------------");
        System.out.println("If you would like to leave at any point, type 'quit'");
        System.out.println("You can type 'back' to return to previous menu");
    }


}