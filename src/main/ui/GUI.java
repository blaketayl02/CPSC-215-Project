package ui;

import model.*;
import persistence.JsonRead;
import persistence.JsonWrite;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;

//GUI for the Store

public class GUI extends JFrame implements ActionListener {
    private static final String CUSTOMER_FILE = "./data/customer.txt";
    private JPanel menu;
    private JPanel categoryPanel;
    private JPanel gpuPanel;
    private JPanel cpuPanel;
    private JPanel ramPanel;
    private JPanel cartPanel;
    private JPanel appPanel;
    private JLabel gpu1;
    private JLabel gpu2;
    private JLabel cpu1;
    private JLabel cpu2;
    private JLabel ram1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton gpu1Button;
    private Parts gtx1060;
    private Parts rtx3060;
    private Parts i76700k;
    private Parts i77700k;
    private Parts ram16GB;
    private Customer customer;
    private int total;
    private JLabel tot;
    private JButton gpu1Remove;
    private JButton gpu2Remove;
    private JButton cpu1Remove;
    private JButton cpu2Remove;
    private JButton ram1Remove;
    private JTextField date;
    private JTextField issue;
    private Appointments appointments;
    private JsonWrite jsonWrite;
    private JsonRead jsonRead;

    //Constructs the GUI for the store.
    public GUI() {
        super("Store App!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.customer = new Customer("user", "password", new ArrayList<>(), 0);
        this.appointments = new Appointments();
        setPreferredSize(new Dimension(600, 700));
        runMenu();
        addCategories();
        addGPU();
        addCPU();
        addRAM();
        showCart();
        addAppointments();

        JLabel welcomeLabel = new JLabel("Welcome to Parts4Less!");
        JLabel mainScreenImage = new JLabel();
        addWelcome(welcomeLabel);
        addImage(mainScreenImage);

        menuButtons();
        addButtons(b1, b2, b3, b4, b5, b6);
        addFunctionToButton();
        menu.setVisible(true);

    }

    //EFFECTS: Creates the main menu panel.
    public void runMenu() {
        menu = new JPanel();
        menu.setBackground(Color.lightGray);
        add(menu);
    }


    //EFFECTS: Creates the main menu buttons.
    public void menuButtons() {
        b1 = new JButton("Parts");
        b2 = new JButton("Appointments");
        b3 = new JButton("View Cart");
        b4 = new JButton("Save");
        b5 = new JButton("Load");
        b6 = new JButton("Exit App!");
        gpu1Remove = new JButton("Remove");
        gpu2Remove = new JButton("Remove");
        cpu1Remove = new JButton("Remove");
        cpu2Remove = new JButton("Remove");
        ram1Remove = new JButton("Remove");

    }

    //MODIFIES: this
    //EFFECTS: method for adding buttons to a given panel.
    public void addButton(JButton button, JPanel panel) {
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.white);
        panel.add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    //EFFECTS: Calls addButton for multiple buttons at a time.
    public void addButtons(JButton button, JButton button2, JButton button3,
                                 JButton button4, JButton button5, JButton button6) {
        addButton(button, menu);
        addButton(button2, menu);
        addButton(button3, menu);
        addButton(button4, menu);
        addButton(button5, menu);
        addButton(button6, menu);
    }


    //EFFECTS: Adds welcome screen text to the menu
    public void addWelcome(JLabel j) {
        j.setFont(new Font("Arial", Font.BOLD, 40));
        menu.add(j);
    }

    //EFFECTS: Adds welcome screen image to the menu
    public void addImage(JLabel j) {
        j.setIcon(new ImageIcon("./data/stockgpu.png"));
        j.setMinimumSize(new Dimension(30, 30));
        menu.add(j);
    }


    //EFFECTS: Adds functions to some main buttons in the menu.
    public void addFunctionToButton() {
        b1.addActionListener(this);
        b1.setActionCommand("Parts");
        b2.addActionListener(this);
        b2.setActionCommand("Appointments");
        b3.addActionListener(this);
        b3.setActionCommand("View Cart");
        b4.addActionListener(this);
        b4.setActionCommand("Save");
        b5.addActionListener(this);
        b5.setActionCommand("Load");
        b6.addActionListener(this);
        b6.setActionCommand("Exit App!");
        gpu1Remove.addActionListener(this);
        gpu1Remove.setActionCommand("remove gpu1");
        gpu2Remove.addActionListener(this);
        gpu2Remove.setActionCommand("remove gpu2");
        cpu1Remove.addActionListener(this);
        cpu1Remove.setActionCommand("remove cpu1");
        cpu2Remove.addActionListener(this);
        cpu2Remove.setActionCommand("remove cpu2");
        ram1Remove.addActionListener(this);
        ram1Remove.setActionCommand("remove ram1");
    }



    //EFFECTS: calls methods when a specific button is clicked on
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void actionPerformed(ActionEvent act) {
        LogPrinter logs = new LogPrinter();
        if (act.getActionCommand().equals("Parts")) {
            categoryPanel();
        } else if (act.getActionCommand().equals("Appointments")) {
            appPanel();
        } else if (act.getActionCommand().equals("View Cart")) {
            cartPanel();
        } else if (act.getActionCommand().equals("Save")) {
            saveCart();
        } else if (act.getActionCommand().equals("Load")) {
            loadCart();
        } else if (act.getActionCommand().equals("Exit App!")) {
            logs.printLog(EventLog.getInstance());
            System.exit(0);
        } else if (act.getActionCommand().equals("GPU")) {
            gpuPanel();
        } else if (act.getActionCommand().equals("Return to menu!")) {
            returnToMenu();
        } else if (act.getActionCommand().equals("RAM")) {
            ramPanel();
        } else if (act.getActionCommand().equals("CPU")) {
            cpuPanel();
        } else if (act.getActionCommand().equals("Add RAM1")) {
            addToPanel(ram16GB, ram1, ram1Remove);
        } else if (act.getActionCommand().equals("Add CPU1")) {
            addToPanel(i76700k, cpu1, cpu1Remove);
        } else if (act.getActionCommand().equals("Add CPU2")) {
            addToPanel(i77700k, cpu2, cpu2Remove);
        } else if (act.getActionCommand().equals("Add GPU1")) {
            addToPanel(gtx1060, gpu1, gpu1Remove);
        } else if (act.getActionCommand().equals("Add GPU2")) {
            addToPanel(rtx3060, gpu2, gpu2Remove);
        } else if (act.getActionCommand().equals("get total")) {
            this.tot.setText("Total is: " + customer.getTotal());
        } else if (act.getActionCommand().equals("remove gpu1")) {
            removeFromPanel(gtx1060, gpu1, gpu1Remove);
        } else if (act.getActionCommand().equals("remove gpu2")) {
            removeFromPanel(rtx3060, gpu2, gpu2Remove);
        } else if (act.getActionCommand().equals("remove cpu1")) {
            removeFromPanel(i76700k, cpu1, cpu1Remove);
        } else if (act.getActionCommand().equals("remove cpu2")) {
            removeFromPanel(i77700k, cpu2, cpu2Remove);
        } else if (act.getActionCommand().equals("remove ram1")) {
            removeFromPanel(ram16GB, ram1, ram1Remove);
        } else if (act.getActionCommand().equals("checkout")) {
            checkOutClear();
        } else if (act.getActionCommand().equals("submit")) {
            submitAppointment();
        }
    }

    //MODIFIES: appointments, this
    //EFFECTS: submits appointment when user clicks "submit"
    public void submitAppointment() {
        appointments.setDate(date.getText());
        appointments.setIssue(issue.getText());
        //System.out.println("Appointment Saved: " + date.getText() + " Issue: " + issue.getText());
        date.setText("");
        issue.setText("");
        returnToMenu();

    }


    //MODIFIES: customer, this
    //EFFECTS: clears customer cart and removes panels from cart.
    public void checkOutClear() {
        customer.setTotal(0);
        customer.clearCart();
        cartPanel.remove(gpu1);
        cartPanel.remove(gpu2);
        cartPanel.remove(cpu1);
        cartPanel.remove(cpu2);
        cartPanel.remove(ram1);
        cartPanel.remove(gpu1Remove);
        cartPanel.remove(gpu2Remove);
        cartPanel.remove(cpu1Remove);
        cartPanel.remove(cpu2Remove);
        cartPanel.remove(ram1Remove);
        returnToMenu();
    }



    //MODIFIES: customer, this
    //EFFECTS: adds part, label, and removal button to the cart.
    public void addToPanel(Parts part, JLabel label, JButton removal) {
        customer.addToCart(part);
        total = total + customer.getTotal();
        cartPanel.add(label);
        cartPanel.add(removal);
        returnToMenu();
    }

    //MODIFIES: customer, this
    //EFFECTS: removes part, label, and removal button from the cart.
    public void removeFromPanel(Parts part, JLabel label, JButton removal) {
        customer.removeFromCartPart(part);
        cartPanel.remove(label);
        cartPanel.remove(removal);
        returnToMenu();
    }


    //MODIFIES: this
    //EFFECTS: initializes the cpuPanel.
    private void cpuPanel() {
        add(cpuPanel);
        cpuPanel.setVisible(true);
        menu.setVisible(false);
        categoryPanel.setVisible(false);
        gpuPanel.setVisible(false);

    }

    //MODIFIES: this
    //EFFECTS: initializes the ramPanel.
    private void ramPanel() {
        add(ramPanel);
        ramPanel.setVisible(true);
        menu.setVisible(false);
        categoryPanel.setVisible(false);
        gpuPanel.setVisible(false);
        cpuPanel.setVisible(false);
    }


    //MODIFIES: this, customer
    //EFFECTS: loads the customer cart with the given file
    private void loadCart() {
        this.jsonRead = new JsonRead(CUSTOMER_FILE);
        try {
            customer = jsonRead.read();
            System.out.println("Restored " + customer.getCart());
            addBackToCart(customer);
            returnToMenu();
        } catch (IOException e) {
            System.out.println("Unable to read file! " + CUSTOMER_FILE);
        }
    }


    //MODIFIES: this
    //EFFECTS: adds items back to the cartPanel based on the customer cart
    private void addBackToCart(Customer customer) {
        for (String s : customer.getCart()) {
            if (Objects.equals(s, gtx1060.getName())) {
                cartPanel.add(gpu1);
                cartPanel.add(gpu1Remove);
            }

            if (Objects.equals(s, rtx3060.getName())) {
                cartPanel.add(gpu2);
                cartPanel.add(gpu2Remove);
            }

            if (Objects.equals(s, i76700k.getName())) {
                cartPanel.add(cpu1);
                cartPanel.add(cpu1Remove);
            }

            if (Objects.equals(s, i77700k.getName())) {
                cartPanel.add(cpu2);
                cartPanel.add(cpu2Remove);
            }

            if (Objects.equals(s, ram16GB.getName())) {
                cartPanel.add(ram1);
                cartPanel.add(ram1Remove);
            }

        }


    }


    //MODIFIES: this
    //EFFECTS: saves the customer cart to file
    private void saveCart() {
        this.jsonWrite = new JsonWrite(CUSTOMER_FILE);
        try {
            jsonWrite.open();
            jsonWrite.write(customer);
            jsonWrite.close();
            System.out.println("Saved " + customer.getCart());
            returnToMenu();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save your cart!" + CUSTOMER_FILE);
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes the cartPanel.
    private void cartPanel() {
        add(cartPanel);
        cartPanel.setVisible(true);
        menu.setVisible(false);
        cpuPanel.setVisible(false);
        gpuPanel.setVisible(false);
        ramPanel.setVisible(false);
        categoryPanel.setVisible(false);
    }


    //MODIFIES: this
    //EFFECTS: initializes the appPanel.
    private void appPanel() {
        add(appPanel);
        appPanel.setVisible(true);
        menu.setVisible(false);
        cpuPanel.setVisible(false);
        gpuPanel.setVisible(false);
        ramPanel.setVisible(false);
        categoryPanel.setVisible(false);
        cartPanel.setVisible(false);
    }


    //MODIFIES: this
    //EFFECTS: initializes the categoryPanel.
    public void categoryPanel() {
        add(categoryPanel);
        categoryPanel.setVisible(true);
        menu.setVisible(false);
        cpuPanel.setVisible(false);
        gpuPanel.setVisible(false);
        ramPanel.setVisible(false);
    }



    //MODIFIES: this
    //EFFECTS: initializes the gpuPanel.
    private void gpuPanel() {
        add(gpuPanel);
        gpuPanel.setVisible(true);
        menu.setVisible(false);
        categoryPanel.setVisible(false);
        ramPanel.setVisible(false);
    }


    //EFFECTS: creates the Appointment labels/fields
    public void addAppointments() {
        appPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel date1 = new JLabel("Please type in the date: ");
        this.date = new JTextField(50);
        appPanel.add(date1);
        appPanel.add(date);

        JLabel issue1 = new JLabel("Please describe your issue: ");
        this.issue = new JTextField(50);
        appPanel.add(issue1);
        appPanel.add(issue);

        JButton submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setActionCommand("submit");
        addButton(submit, appPanel);

        JButton backToMenuButton = new JButton("Return to menu!");
        backToMenuButton.setActionCommand("Return to menu!");
        backToMenuButton.addActionListener(this);
        addButton(backToMenuButton, appPanel);

    }

    //EFFECTS: creates the Categories labels/fields
    public void addCategories() {
        JButton gpu = new JButton("GPU");
        JButton cpu = new JButton("CPU");
        JButton ram = new JButton("RAM");
        JButton backToMenuButton = new JButton("Return to menu!");

        categoryPanel = new JPanel(new GridLayout(4, 1));
        gpu.setActionCommand("GPU");
        gpu.addActionListener(this);
        addButton(gpu, categoryPanel);

        cpu.setActionCommand("CPU");
        cpu.addActionListener(this);
        addButton(cpu, categoryPanel);

        ram.setActionCommand("RAM");
        ram.addActionListener(this);
        addButton(ram, categoryPanel);

        backToMenuButton.setActionCommand("Return to menu!");
        backToMenuButton.addActionListener(this);
        addButton(backToMenuButton, categoryPanel);

    }

    //EFFECTS: creates the GPU labels/fields
    public void addGPU() {
        this.gpu1 = new JLabel("GTX 1060");
        this.gpu2 = new JLabel("RTX 3060");
        this.gtx1060 = new Gpu("nvidia","GTX 1060", 1111, 2222);
        this.rtx3060 = new Gpu("nvidia", "GTX 3060", 22222, 3000);

        gpuPanel = new JPanel(new GridLayout(3, 1));

        gpu1.setIcon(new ImageIcon("./data/gtx1060.jpg"));
        gpu2.setIcon(new ImageIcon("./data/gtx3060.jpg"));

        this.gpu1Button = new JButton("GTX 1060", gpu1.getIcon());
        gpu1Button.setActionCommand("Add GPU1");
        gpu1Button.addActionListener(this);
        JButton gpu2Button = new JButton("RTX 3060", gpu2.getIcon());
        gpu2Button.setActionCommand("Add GPU2");
        gpu2Button.addActionListener(this);

        addButton(gpu1Button, gpuPanel);
        addButton(gpu2Button, gpuPanel);

        JButton back = new JButton("Return to menu!");
        back.setActionCommand("Return to menu!");
        back.addActionListener(this);
        addButton(back, gpuPanel);

    }

    //EFFECTS: creates the CPU labels/fields
    public void addCPU() {
        this.cpu1 = new JLabel("i7-6700k");
        this.cpu2 = new JLabel("i7-7700k");
        this.i76700k = new Gpu("intel", "i7 6700k", 100, 1500);
        this.i77700k = new Cpu("intel","i7 7700k", 300, 1000);
        cpuPanel = new JPanel(new GridLayout(3, 1));

        cpu1.setIcon(new ImageIcon("./data/i76700k.jpg"));
        cpu2.setIcon(new ImageIcon("./data/i77700k.jpg"));

        JButton cpu1Button = new JButton("i7-6700k", cpu1.getIcon());
        cpu1Button.setActionCommand("Add CPU1");
        cpu1Button.addActionListener(this);
        JButton cpu2Button = new JButton("i7-7700k", cpu2.getIcon());
        cpu2Button.setActionCommand("Add CPU2");
        cpu2Button.addActionListener(this);
        addButton(cpu1Button, cpuPanel);
        addButton(cpu2Button, cpuPanel);

        JButton back = new JButton("Return to menu!");
        back.setActionCommand("Return to menu!");
        back.addActionListener(this);
        addButton(back, cpuPanel);

    }


    //EFFECTS: creates the RAM labels/fields
    public void addRAM() {
        this.ram1 = new JLabel("16GB DDR4");
        this.ram16GB = new Ram("Tradesman", "16GB DDR4", 222, 150);
        ramPanel = new JPanel(new GridLayout(2, 1));
        ram1.setIcon(new ImageIcon("./data/16gbDDR4.jpg"));

        JButton ram1Button = new JButton("16GB DDR4", ram1.getIcon());
        ram1Button.setActionCommand("Add RAM1");
        ram1Button.addActionListener(this);
        addButton(ram1Button, ramPanel);

        JButton back = new JButton("Return to menu!");
        back.setActionCommand("Return to menu!");
        back.addActionListener(this);
        addButton(back, ramPanel);

    }


    //EFFECTS: creates the Cart labels/fields with given customer cart.
    private void showCart() {
        cartPanel = new JPanel(new GridLayout(customer.getCart().size(), 1));

        JButton total = new JButton("Press to Calculate Total");
        total.setActionCommand("get total");
        total.addActionListener(this);
        addButton(total, cartPanel);


        JButton back = new JButton("Return to menu!");
        back.setActionCommand("Return to menu!");
        back.addActionListener(this);
        addButton(back, cartPanel);


        this.tot = new JLabel("Total is: " + customer.getTotal());
        cartPanel.add(tot);

        JButton checkout = new JButton("Checkout");
        checkout.setActionCommand("checkout");
        checkout.addActionListener(this);
        addButton(checkout, cartPanel);
    }


    //EFFECTS: returns user back to main menu.
    public void returnToMenu() {
        menu.setVisible(true);
        categoryPanel.setVisible(false);
        gpuPanel.setVisible(false);
        cpuPanel.setVisible(false);
        ramPanel.setVisible(false);
        cartPanel.setVisible(false);
        appPanel.setVisible(false);
    }

}
