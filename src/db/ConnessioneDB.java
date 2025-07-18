package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/aereoportoNapoliDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Zr8v!pDq21@LmXeK";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione al DB riuscita!");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver PostgreSQL non trovato. Aggiungi il JAR al progetto!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Errore nella connessione al DB:");
            e.printStackTrace();
        }

        return conn;
    }
}