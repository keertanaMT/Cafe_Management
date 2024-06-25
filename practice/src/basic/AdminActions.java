package basic;
import java.sql.*;
import java.util.Scanner;

public class AdminActions {
    public static void handleAdminActions(Connection conn, Scanner scanner) throws SQLException {
        System.out.println("What would you like to do?");
        System.out.println("1. Create new item");
        System.out.println("2. Update existing item");
        System.out.println("3. Delete item");
        System.out.print("Enter your choice: ");
        int adminChoice = scanner.nextInt();

        switch (adminChoice) {
            case 1:
                AdminActions.createNewItem(conn, scanner);
                break;
            case 2:
                AdminActions.updateExistingItem(conn, scanner);
                break;
            case 3:
                AdminActions.deleteItem(conn, scanner);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private static void createNewItem(Connection conn, Scanner scanner) throws SQLException {
      
    	System.out.print("Enter item id: ");
        int id=scanner.nextInt();
    	System.out.println("Enter item name: ");
        String name = scanner.next();
        System.out.println("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        String sql = "INSERT INTO menu (id,name, price) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setDouble(3, price);
        preparedStatement.executeUpdate();
        
        System.out.println("New item added successfully.");
    }

    private static void updateExistingItem(Connection conn, Scanner scanner) throws SQLException {
        
    	 System.out.println("Enter item ID to update: ");
         int id = scanner.nextInt();
         System.out.println("Enter updated item name: ");
         String name = scanner.next();
         System.out.println("Enter updated item price: ");
         double price = scanner.nextDouble();
         scanner.nextLine();
         String sql = "UPDATE menu SET name = ?, price = ? WHERE id = ?";
         PreparedStatement preparedStatement = conn.prepareStatement(sql);
         preparedStatement.setString(1, name);
         preparedStatement.setDouble(2, price);
         preparedStatement.setInt(3, id);
         int rowsAffected = preparedStatement.executeUpdate();
         
         if (rowsAffected > 0) {
             System.out.println("Item updated successfully.");
         } else {
             System.out.println("No item found with ID " + id);
    }
    }
    private static void deleteItem(Connection conn, Scanner scanner) throws SQLException {
     
    	  System.out.println("Enter item ID to delete: ");
          int id = scanner.nextInt();
          
          String sql = "DELETE FROM menu WHERE id = ?";
          PreparedStatement preparedStatement = conn.prepareStatement(sql);
          preparedStatement.setInt(1, id);
          int rowsAffected = preparedStatement.executeUpdate();
          
          if (rowsAffected > 0) {
              System.out.println("Item deleted successfully.");
          } else {
              System.out.println("No item found with ID " + id);
    
    }
}
    }
