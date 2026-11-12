package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/clinic_db?useSSL=false&serverTimezone=Turkey";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("veritabanı bağlantısı başarıyla sağlandı");
        } catch (SQLException e){
            System.out.println("veritabanı bağlantı hatası: "+ e.getMessage());
        }
        return conn;
    }
}
