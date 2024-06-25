package basic;
import java.sql.*;
import java.util.*;
public class UserInterface {
    public static void run(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

       while (!exit) {
            System.out.println("               ");
            System.out.println("***********************************************************************************************************************************************************************");
            System.out.println("************************************************************************* WELCOME TO CAFE *****************************************************************************");
            System.out.println("***********************************************************************************************************************************************************************");
            System.out.println("               ");
            System.out.println("Are you an Admin or a Customer?");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("              ");
            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    if (authenticateAdmin(conn, scanner)) {
                        AdminActions.handleAdminActions(conn, scanner);
                    } else {
                    	System.out.println("              ");
                        System.out.println("Admin authentication failed!");
                    }
                    break;
                case 2:
                    CustomerActions.handleCustomerActions(conn, scanner);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println("      ");
            System.out.println("Would you like to continue?");
            System.out.println("      ");
            System.out.print("Enter 'yes' to continue, 'no' to exit: ");
            String continueChoice = scanner.next().toLowerCase();
            if (!continueChoice.equals("yes")) {
                exit = true;
            }
        }
    }

   private static boolean authenticateAdmin(Connection conn, Scanner scanner) throws SQLException {
    	System.out.println("             ");
    	System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.println("              ");
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        String sql = "SELECT * FROM admin_credentials WHERE admin_username = ? AND admin_password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); 
        }
    }
}

