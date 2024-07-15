package com.cts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class QualityControlManagementSystem {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quality_control", "root", "Sheik@2002");
            stmt = conn.createStatement();
            scanner = new Scanner(System.in);

            // Display menu
            while (true) {
                System.out.println("Quality Control Management System");
                System.out.println("1. Manage Products");
                System.out.println("2. Manage Defects");
                System.out.println("3. Manage Quality Checks");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        manageProducts();
                        break;
                    case 2:
                        manageDefects();
                        break;
                    case 3:
                        manageQualityChecks();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs!= null) rs.close();
                if (stmt!= null) stmt.close();
                if (conn!= null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Product Management
    private static void manageProducts() {
        while (true) {
            System.out.println("Product Management");
            System.out.println("1. Add a new product");
            System.out.println("2. View product details");
            System.out.println("3. Update product information");
            System.out.println("4. Delete a product");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProductDetails();
                    break;
                case 3:
                    updateProductInformation();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter production date (yyyy-mm-dd): ");
        String productionDate = scanner.next();
        System.out.print("Enter quantity produced: ");
        int quantityProduced = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (name, description, production_date, quantity_produced) VALUES (?,?,?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, productionDate);
            pstmt.setInt(4, quantityProduced);
            pstmt.executeUpdate();
            System.out.println("Product added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return;
    }

    private static void viewProductDetails() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM products WHERE product_id =?");
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("Production Date: " + rs.getString("production_date"));
                System.out.println("Quantity Produced: " + rs.getInt("quantity_produced"));
            } else {
                System.out.println("Product not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateProductInformation() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new product name: ");
        String name = scanner.next();
        System.out.print("Enter new product description: ");
        String description = scanner.next();
        System.out.print("Enter new production date (yyyy-mm-dd): ");
        String productionDate = scanner.next();
        System.out.print("Enter new quantity produced: ");
        int quantityProduced = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET name = ?, description = ?, production_date = ?, quantity_produced = ? WHERE product_id = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, productionDate);
            pstmt.setInt(4, quantityProduced);
            pstmt.setInt(5, productId);
            pstmt.executeUpdate();
            System.out.println("Product information updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteProduct() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE product_id = ?");
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
            System.out.println("Product deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Defect Management
    private static void manageDefects() {
        while (true) {
            System.out.println("Defect Management");
            System.out.println("1. Add a new defect type");
            System.out.println("2. View defect details");
            System.out.println("3. Update defect information");
            System.out.println("4. Delete a defect type");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addDefectType();
                    break;
                case 2:
                    viewDefectDetails();
                    break;
                case 3:
                    updateDefectInformation();
                    break;
                case 4:
                    deleteDefectType();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDefectType() {
        System.out.print("Enter defect name: ");
        String name = scanner.next();
        System.out.print("Enter defect description: ");
        String description = scanner.next();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO defects (name, description) VALUES (?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();
            System.out.println("Defect type added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewDefectDetails() {
        System.out.print("Enter defect ID: ");
        int defectId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM defects WHERE defect_id = ?");
            pstmt.setInt(1, defectId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Defect ID: " + rs.getInt("defect_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
            } else {
                System.out.println("Defect not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateDefectInformation() {
        System.out.print("Enter defect ID: ");
        int defectId = scanner.nextInt();
        System.out.print("Enter new defect name: ");
        String name = scanner.next();
        System.out.print("Enter new defect description: ");
        String description = scanner.next();

        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE defects SET name = ?, description = ? WHERE defect_id = ?");
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setInt(3, defectId);
            pstmt.executeUpdate();
            System.out.println("Defect information updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteDefectType() {
        System.out.print("Enter defect ID: ");
        int defectId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM defects WHERE defect_id = ?");
            pstmt.setInt(1, defectId);
            pstmt.executeUpdate();
            System.out.println("Defect type deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

 // Quality Check Management
    private static void manageQualityChecks() {
        while (true) {
            System.out.println("Quality Check Management");
            System.out.println("1. Add a new quality check");
            System.out.println("2. View quality check details");
            System.out.println("3. Update quality check information");
            System.out.println("4. Delete a quality check");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addQualityCheck();
                    break;
                case 2:
                    viewQualityCheckDetails();
                    break;
                case 3:
                    updateQualityCheckInformation();
                    break;
                case 4:
                    deleteQualityCheck();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addQualityCheck() {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter defect ID: ");
        int defectId = scanner.nextInt();
        System.out.print("Enter check date (yyyy-mm-dd): ");
        String checkDate = scanner.next();
        System.out.print("Enter result (pass/fail): ");
        String result = scanner.next();

        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO quality_checks (product_id, defect_id, check_date, result) VALUES (?,?,?,?)");
            pstmt.setInt(1, productId);
            pstmt.setInt(2, defectId);
            pstmt.setString(3, checkDate);
            pstmt.setString(4, result);
            pstmt.executeUpdate();
            System.out.println("Quality check added successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void viewQualityCheckDetails() {
        System.out.print("Enter quality check ID: ");
        int qualityCheckId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM quality_checks WHERE check_id = ?");
            pstmt.setInt(1, qualityCheckId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Quality Check ID: " + rs.getInt("check_id"));
                System.out.println("Product ID: " + rs.getInt("product_id"));
                System.out.println("Defect ID: " + rs.getInt("defect_id"));
                System.out.println("Check Date: " + rs.getString("check_date"));
                System.out.println("Result: " + rs.getString("result"));
            } else {
                System.out.println("Quality check not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateQualityCheckInformation() {
        System.out.print("Enter quality check ID: ");
        int qualityCheckId = scanner.nextInt();
        System.out.print("Enter new product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter new defect ID: ");
        int defectId = scanner.nextInt();
        System.out.print("Enter new check date (yyyy-mm-dd): ");
        String checkDate = scanner.next();
        System.out.print("Enter new result (pass/fail): ");
        String result = scanner.next();

        try {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE quality_checks SET product_id = ?, defect_id = ?, check_date = ?, result = ? WHERE check_id = ?");
            pstmt.setInt(1, productId);
            pstmt.setInt(2, defectId);
            pstmt.setString(3, checkDate);
            pstmt.setString(4, result);
            pstmt.setInt(5, qualityCheckId);
            pstmt.executeUpdate();
            System.out.println("Quality check information updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteQualityCheck() {
        System.out.print("Enter quality check ID: ");
        int qualityCheckId = scanner.nextInt();

        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM quality_checks WHERE check_id = ?");
            pstmt.setInt(1, qualityCheckId);
            pstmt.executeUpdate();
            System.out.println("Quality check deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
 