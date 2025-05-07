package view;

import java.util.Scanner;

public class OrderView {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n--- Order Management System ---");
        System.out.println("1. Add Order");
        System.out.println("2. Search Order");
        System.out.println("3. Delete Order");
        System.out.println("4. Update Order");
        System.out.println("5. Display Orders");
        System.out.println("6. Exit");
    }

    public char getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.next().charAt(0);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
