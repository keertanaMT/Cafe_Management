package basic;
import java.sql.*;
import java.util.Scanner;

public class CustomerActions {
    public static void handleCustomerActions(Connection conn, Scanner scanner) throws SQLException {
        CustomerActions.displayMenu(conn);
        System.out.println("Would you like to place an order? (yes/no)");
        String orderChoice = scanner.next().toLowerCase();
        if (orderChoice.equals("yes")) {
            CustomerActions.placeOrder(conn, scanner);
        }
    }

    private static void displayMenu(Connection conn) throws SQLException {
        
    	Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM menu");
        System.out.println("    ");
        System.out.println("Menu:");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getDouble("price"));
        }
        rs.close();
        stmt.close();
    }

    private static void placeOrder(Connection conn, Scanner scanner) throws SQLException {
    	System.out.println();
    	System.out.println("Enter item ID to order: ");
        int itemId = scanner.nextInt();
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        String sql = "SELECT * FROM menu WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, itemId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            double price = rs.getDouble("price");
            double totalCost = price * quantity;
            System.out.println("Total cost for the order: $" + totalCost);
        } else {
            System.out.println("No item found with ID " + itemId);
        }
        rs.close();
        preparedStatement.close();
    
    }
}

