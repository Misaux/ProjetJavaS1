package SQL;

//STEP 1. Import required packages

import java.sql.*;

public class TestConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/projetjava?serverTimezone=UTC";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    public TestConnection() {

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);


        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try

    }


    public void addRow() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO profs(Last_Name , First_Name) VALUES ('', '')";
            stmt.executeUpdate(sql);

            String sql1 = "SELECT Last_Name, First_Name FROM profs";

            ResultSet rs = stmt.executeQuery(sql1);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String first_name = rs.getString("First_Name");
                String last_name = rs.getString("Last_Name");
                //Display values
                System.out.print("Prenom: " + first_name);
                System.out.print("Nom: " + last_name);
                System.out.println("\n");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void cleanDB(){
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "DELETE FROM profs";

            stmt.executeUpdate(sql);

            //String sql1;

            //sql1 = "DELETE FROM profs";

            //ResultSet rs = stmt.executeQuery(sql1);

            //STEP 6: Clean-up environment
            //rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void addRow(String prenom, String nom) {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql = "INSERT INTO profs(Last_Name , First_Name) VALUES ('"+ nom +"', '"+prenom +"')";

            stmt.executeUpdate(sql);



            String sql1 = "SELECT Last_Name, First_Name FROM profs";

            ResultSet rs = stmt.executeQuery(sql1);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String first_name = rs.getString("First_Name");
                String last_name = rs.getString("Last_Name");

                //Display values
                System.out.print("Prenom: " + first_name);
                System.out.print("Nom: " + last_name);
                System.out.println("\n");

            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }

    public void readTab() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            stmt = conn.createStatement();

            String sql1 = "SELECT Last_Name, First_Name FROM profs";

            ResultSet rs = stmt.executeQuery(sql1);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String first_name = rs.getString("First_Name");
                String last_name = rs.getString("Last_Name");

                //Display values
                System.out.print("Prenom: " + first_name);
                System.out.print("Nom: " + last_name);
                System.out.println("\n");
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
