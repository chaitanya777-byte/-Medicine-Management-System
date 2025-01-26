import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.List;

// Medicine class
class Medicine {
    private int id;
    private String name;
    private String manufacturer;
    private int quantity;
    private double price;
    private LocalDate expiryDate;

    public Medicine(int id, String name, String manufacturer, int quantity, double price, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return "Medicine ID: " + id +
                ", Name: " + name +
                ", Manufacturer: " + manufacturer +
                ", Quantity: " + quantity +
                ", Price: $" + price +
                ", Expiry Date: " + expiryDate;
    }
}

// Medicine Inventory class
class MedicineInventory {
    private List<Medicine> inventory = new ArrayList<>();

    // Add new medicine
    public void addMedicine(Medicine medicine) {
        inventory.add(medicine);
        System.out.println("Medicine added successfully!");
    }

    // Display all medicines
    public void displayAllMedicines() {
        if (inventory.isEmpty()) {
            System.out.println("No medicines in inventory.");
            return;
        }
        System.out.println("Medicine Inventory:");
        for (Medicine medicine : inventory) {
            System.out.println(medicine);
        }
    }

    // Sell medicine
    public void sellMedicine(int id, int quantity) {
        for (Medicine medicine : inventory) {
            if (medicine.getId() == id) {
                if (medicine.getQuantity() >= quantity) {
                    medicine.setQuantity(medicine.getQuantity() - quantity);
                    System.out.println("Medicine sold! Total cost: $" + (quantity * medicine.getPrice()));
                    return;
                } else {
                    System.out.println("Insufficient stock!");
                    return;
                }
            }
        }
        System.out.println("Medicine not found!");
    }

    // Update stock
    public void updateStock(int id, int newQuantity) {
        for (Medicine medicine : inventory) {
            if (medicine.getId() == id) {
                medicine.setQuantity(newQuantity);
                System.out.println("Stock updated successfully!");
                return;
            }
        }
        System.out.println("Medicine not found!");
    }

    // Check for expired medicines
    public void checkExpiredMedicines() {
        LocalDate today = LocalDate.now();
        boolean found = false;
        for (Medicine medicine : inventory) {
            if (medicine.getExpiryDate().isBefore(today)) {
                System.out.println("Expired Medicine: " + medicine);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expired medicines.");
        }
    }

    // Check low stock medicines
    public void checkLowStock(int threshold) {
        boolean found = false;
        for (Medicine medicine : inventory) {
            if (medicine.getQuantity() < threshold) {
                System.out.println("Low Stock Alert: " + medicine);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No low stock medicines.");
        }
    }
}

// Main class
public class MedicineManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MedicineInventory inventory = new MedicineInventory();
        boolean running = true;

        System.out.println("Welcome to the Medicine Management System!");

        while (running) {
            System.out.println("\n1. Add Medicine");
            System.out.println("2. View All Medicines");
            System.out.println("3. Sell Medicine");
            System.out.println("4. Update Stock");
            System.out.println("5. Check Expired Medicines");
            System.out.println("6. Check Low Stock");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Medicine ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Medicine Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Manufacturer: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Expiry Date (YYYY-MM-DD): ");
                    String expiryDateString = scanner.next();
                    LocalDate expiryDate = LocalDate.parse(expiryDateString);

                    Medicine medicine = new Medicine(id, name, manufacturer, quantity, price, expiryDate);
                    inventory.addMedicine(medicine);
                    break;

                case 2:
                    inventory.displayAllMedicines();
                    break;

                case 3:
                    System.out.print("Enter Medicine ID to sell: ");
                    int sellId = scanner.nextInt();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    inventory.sellMedicine(sellId, sellQuantity);
                    break;

                case 4:
                    System.out.print("Enter Medicine ID to update stock: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new stock quantity: ");
                    int newStock = scanner.nextInt();
                    inventory.updateStock(updateId, newStock);
                    break;

                case 5:
                    inventory.checkExpiredMedicines();
                    break;

                case 6:
                    System.out.print("Enter low stock threshold: ");
                    int threshold = scanner.nextInt();
                    inventory.checkLowStock(threshold);
                    break;

                case 7:
                    running = false;
                    System.out.println("Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}
