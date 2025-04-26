import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class InventoryManagementSystem extends JFrame {
    private Map<String, Integer> inventory;

    private JTextField itemNameTextField;
    private JTextField quantityTextField;
    private JButton addItemButton;
    private JButton updateQuantityButton;
    private JTextArea inventoryListTextArea;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setLayout(new BorderLayout());
        inventory = new HashMap<>();

        JPanel inputPanel = new JPanel(new FlowLayout());

        itemNameTextField = new JTextField(20);
        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameTextField);

        quantityTextField = new JTextField(5);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityTextField);

        addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(e -> addItem());
        inputPanel.add(addItemButton);

        updateQuantityButton = new JButton("Update Quantity");
        updateQuantityButton.addActionListener(e -> updateQuantity());
        inputPanel.add(updateQuantityButton);

        add(inputPanel, BorderLayout.NORTH);

        inventoryListTextArea = new JTextArea(20, 40);
        inventoryListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(inventoryListTextArea);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null); // Center window
        pack();
        setVisible(true);
    }

    private void addItem() {
        String itemName = itemNameTextField.getText().trim();
        String quantityStr = quantityTextField.getText().trim();

        if (itemName.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both item name and quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            inventory.put(itemName, quantity);
            updateInventoryList();
            itemNameTextField.setText("");
            quantityTextField.setText("");
            JOptionPane.showMessageDialog(this, "Item added successfully!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a valid number.");
        }
    }

    private void updateQuantity() {
        String itemName = itemNameTextField.getText().trim();
        String quantityStr = quantityTextField.getText().trim();

        if (itemName.isEmpty() || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both item name and quantity.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            if (inventory.containsKey(itemName)) {
                inventory.put(itemName, quantity);
                updateInventoryList();
                itemNameTextField.setText("");
                quantityTextField.setText("");
                JOptionPane.showMessageDialog(this, "Quantity updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Item not found.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a valid number.");
        }
    }

    private void updateInventoryList() {
        inventoryListTextArea.setText("");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            inventoryListTextArea.append(entry.getKey() + " - Quantity: " + entry.getValue() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManagementSystem::new);
    }
}
