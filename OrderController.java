package controller;
import java.util.Scanner;

import model.Meal;
import model.Order;
import view.OrderView;
import utils.CircuitBreaker;

public class OrderController {
    private final Order[] orders = new Order[100];
    private int size = 0;
    private int orderCount = 1;
    private final OrderView view = new OrderView();
    private final CircuitBreaker circuitBreaker = new CircuitBreaker();

  public void start() {
    if (!authenticate()) return;

    char choice;
    do {
        view.displayMenu();
        choice = view.getUserChoice();
        try {
            if (!circuitBreaker.isAvailable()) {
                System.out.println("System temporarily unavailable. Try again later.");
                continue;
            }

            switch (choice) {
                case '1':
                    addOrder();
                    break;
                case '2':
                    searchOrder();
                    break;
                case '3':
                    deleteOrder();
                    break;
                case '4':
                    updateOrder();
                    break;
                case '5':
                    displayOrders();
                    break;
                case '6':
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }

            circuitBreaker.recordSuccess();
        } catch (Exception e) {
            circuitBreaker.recordFailure();
            System.out.println("Error occurred: " + e.getMessage());
        }
    } while (choice != '6');
}

    private boolean authenticate() {
        final String correctPassword = "dsa";
        final int maxAttempts = 3;
        for (int i = 0; i < maxAttempts; i++) {
            System.out.print("Enter Password: ");
            String password = view.getScanner().next();
            if (password.equals(correctPassword)) {
                System.out.println("Login successful!");
                return true;
            }
            System.out.println("Wrong password.");
        }
        System.out.println("Max attempts reached. Exiting...");
        return false;
    }

   private void addOrder() {
    Scanner scanner = view.getScanner();
    System.out.print("Enter your name: ");
    String name = scanner.next();
    System.out.print("Enter your contact: ");
    String contact = scanner.next();

    System.out.println("Available meals:\n1. Pizza - $10.99\n2. Burger - $5.99\n3. Pasta - $7.99");
    System.out.print("Enter meal choice: ");
    int choice = scanner.nextInt();

    Meal meal;
    switch (choice) {
        case 1:
            meal = new Meal("Pizza", 10.99);
            break;
        case 2:
            meal = new Meal("Burger", 5.99);
            break;
        case 3:
            meal = new Meal("Pasta", 7.99);
            break;
        default:
            System.out.println("Invalid. Defaulting to Pizza.");
            meal = new Meal("Pizza", 10.99);
            break;
    }

    System.out.println("Dining types:\n1. Dine-in\n2. Take-away\n3. Both");
    int dine = scanner.nextInt();
    String type;
    switch (dine) {
        case 1:
            type = "Dine-in";
            break;
        case 2:
            type = "Take-away";
            break;
        case 3:
            type = "Dine-in and Take-away";
            break;
        default:
            type = "Dine-in";
            break;
    }

    orders[size++] = new Order(orderCount++, name, contact, meal, type);
    System.out.println("Order added!");
}

private void searchOrder() {
    System.out.print("Enter order number to search: ");
    int orderNo = view.getScanner().nextInt();
    for (int i = 0; i < size; i++) {
        if (orders[i].orderNo == orderNo) {
            printOrder(orders[i]);
            return;
        }
    }
    System.out.println("Order not found.");
}

private void deleteOrder() {
    System.out.print("Enter order number to delete: ");
    int orderNo = view.getScanner().nextInt();
    for (int i = 0; i < size; i++) {
        if (orders[i].orderNo == orderNo) {
            for (int j = i; j < size - 1; j++) {
                orders[j] = orders[j + 1];
            }
            size--;
            System.out.println("Order deleted!");
            return;
        }
    }
    System.out.println("Order not found.");
}

private void updateOrder() {
    Scanner scanner = view.getScanner();
    System.out.print("Enter order number to update: ");
    int orderNo = scanner.nextInt();

    for (int i = 0; i < size; i++) {
        if (orders[i].orderNo == orderNo) {
            System.out.println("Available meals:\n1. Pizza - $10.99\n2. Burger - $5.99\n3. Pasta - $7.99");
            System.out.print("Enter new meal choice: ");
            int choice = scanner.nextInt();

            Meal meal;
            switch (choice) {
                case 1:
                    meal = new Meal("Pizza", 10.99);
                    break;
                case 2:
                    meal = new Meal("Burger", 5.99);
                    break;
                case 3:
                    meal = new Meal("Pasta", 7.99);
                    break;
                default:
                    System.out.println("Invalid choice. Defaulting to Pizza.");
                    meal = new Meal("Pizza", 10.99);
                    break;
            }

            System.out.println("New dining type:\n1. Dine-in\n2. Take-away\n3. Both");
            int type = scanner.nextInt();
            String diningType;
            switch (type) {
                case 1:
                    diningType = "Dine-in";
                    break;
                case 2:
                    diningType = "Take-away";
                    break;
                case 3:
                    diningType = "Dine-in and Take-away";
                    break;
                default:
                    diningType = "Dine-in";
                    break;
            }

            orders[i].setMeal(meal);
            orders[i].setDiningType(diningType);
            System.out.println("Order updated!");
            return;
        }
    }

    System.out.println("Order not found.");
}



    private void displayOrders() {
        if (size == 0) {
            System.out.println("No orders.");
            return;
        }
        for (int i = 0; i < size; i++) {
            printOrder(orders[i]);
        }
    }

    private void printOrder(Order o) {
        System.out.printf("Order No: %d\nName: %s\nContact: %s\nMeal: %s ($%.2f)\nDining: %s\n\n",
                o.orderNo, o.name, o.contact, o.meal.name, o.meal.price, o.diningType);
    }
}
