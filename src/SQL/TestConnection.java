package SQL;

//STEP 1. Import required packages

import java.sql.*;

public class TestConnection {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/projets1?serverTimezone=UTC";

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
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            System.out.println("Connexion à la base de données...");
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
        System.out.println("La connexion a bien été faite avec la base de données.");
        System.out.println("- - - - - - - - - - - - - - - - - - -\n");
    }


    public void addRow() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            stmt = conn.createStatement();
            String sql = "INSERT INTO profs(Last_Name , First_Name) VALUES ('', '')";
            stmt.executeUpdate(sql);

            System.out.println("Ajout d'une ligne vide dans la base de données.");
            System.out.println("- - - - - - - - - - - - - - - - - - -\n");

            //STEP 6: Clean-up environment

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

    public void cleanDB() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            //STEP 4: Execute a query

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
        System.out.println("La base de données a bien été vidé.");
        System.out.println("- - - - - - - - - - - - - - - - - - -\n");
    }

    public void addRow(String prenom, String nom) {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            //STEP 4: Execute a query
            System.out.println("Ajout de : " + nom + " " + prenom + " à la base de données.");
            stmt = conn.createStatement();

            String sql = "INSERT INTO profs(Last_Name , First_Name) VALUES ('" + nom + "',' " + prenom + "')";

            stmt.executeUpdate(sql);

            //STEP 6: Clean-up environment

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
        System.out.println("- - - - - - - - - - - - - - - - - - -\n");
    }

    public void readTab() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            System.out.println("Affichage de l'intégralité de la base de données.\n");

            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql1 = "SELECT Last_Name, First_Name FROM profs";

            ResultSet rs = stmt.executeQuery(sql1);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                String first_name = rs.getString("First_Name");
                String last_name = rs.getString("Last_Name");

                //Display values
                System.out.println("Prenom: " + first_name+ "  Nom: " + last_name);
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
        System.out.println("- - - - - - - - - - - - - - - - - - -\n");
    }

    public void searchName(String reserach) {
        String last_name = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("- - - - - - - - - - - - - - - - - - -");
            System.out.println("Lancement de la recherche par nom :");
            //STEP 4: Execute a query
            stmt = conn.createStatement();

            String sql1 = "SELECT last_name FROM user WHERE (last_name) = '" + reserach + "'";

            ResultSet rs = stmt.executeQuery(sql1);
            //STEP 5: Extract data from result set
            try {
                rs.next();
                last_name = rs.getString("Last_Name");
            } catch (SQLException throwables) {
                System.out.println("    Le nom recherché ne correspond à personne dans la base de donnés");
            }


            if (last_name != null) {
                System.out.println("    Le nom : " + last_name + " est bien présent dans la base de données");
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
        System.out.println("- - - - - - - - - - - - - - - - - - -\n");
    }
}
