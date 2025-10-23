package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {

        ArrayList<Product> inventory = new ArrayList<>();
        ArrayList<Product> cart = new ArrayList<>();

        loadInventory("products.csv", inventory);

        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 3) {
            System.out.println("\nWelcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter 1, 2, or 3.");
                scanner.nextLine();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayProducts(inventory, cart, scanner);
                case 2 -> displayCart(cart, scanner);
                case 3 -> System.out.println("Thank you for shopping with us!");
                default -> System.out.println("Invalid choice!");
            }
        }
        scanner.close();
    }


    public static void loadInventory(String fileName, ArrayList<Product> inventory) {

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty() || line.startsWith("productId")) continue;

                String[] parts = line.split("\\|");
                if (parts.length != 3) continue;

                final String productId = parts[0].trim();
                final String productName = parts[1].trim();
                final double productPrice = Double.parseDouble(parts[2].trim());

                inventory.add(new Product(productId, productName, productPrice));
            }

        } catch (IOException e) {
            System.out.println("Error loading File: " + e.getMessage());

        }
    }


    public static void displayProducts(ArrayList<Product> inventory,
                                       ArrayList<Product> cart,
                                       Scanner scanner) {
        while (true) {
            System.out.println("\nAvailable Products:");
            System.out.println("--------------------------------------------------");

            for (Product p : inventory) {
                System.out.println(p);
            }

            System.out.println("\nEnter the Product ID to add to cart, or 'X' to return:");
            System.out.print("choice: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("X")) {
                break;
            }

            Product found = findProductById(input, inventory);

            if (found != null) {
                cart.add(found);
                System.out.println(found.getProductName() + " added to your cart.");
            } else
                System.out.println("No product found try again.");
        }
    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner) {
        // TODO:
        //   • list each product in the cart
        //   • compute the total cost
        //   • ask the user whether to check out (C) or return (X)
        //   • if C, call checkOut(cart, totalAmount, scanner)
    }

    /**
     * Handles the checkout process:
     * 1. Confirm that the user wants to buy.
     * 2. Accept payment and calculate change.
     * 3. Display a simple receipt.
     * 4. Clear the cart.
     */
    public static void checkOut(ArrayList<Product> cart,
                                double totalAmount,
                                Scanner scanner) {
        // TODO: implement steps listed above
    }

    /**
     * Searches a list for a product by its id.
     *
     * @return the matching Product, or null if not found
     */
    public static Product findProductById(String productId, ArrayList<Product> inventory) {
        for (Product p : inventory) {
            if (p.getProductId().equalsIgnoreCase(productId)) {
                return p;
            }
        }
        return null;
    }
}

 