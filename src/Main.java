/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author Rim
 */
public class Main {

    static Scanner s = new Scanner(System.in);


    public static Connection getConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        String DB_URL = "jdbc:mysql://localhost:3306/db_pbo";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "";

        dataSource.setURL(DB_URL);
        dataSource.setUser(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);

        try {
            Connection conn = dataSource.getConnection();
            
            return conn;

        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {

        int n;
        do {
            System.out.println("Selamat Datang di aplikasi Lost and Found pilih yang anda inginkan.");
            System.out.println("1, tambah user");
            System.out.println("2. tampilkan user");
            System.out.println("3. hapus user");

            System.out.println("4, tambah lost item");
            System.out.println("5. tampilkan lost item");
            System.out.println("6. hapus lost item");

            System.out.println("7, tambah found item");
            System.out.println("8. tampilkan found item");
            System.out.println("9. hapus found item");
            System.out.println("10. keluar");

            n = s.nextInt();

            switch (n) {
                case 1:
                    insertDataUser();
                    break;
                case 2:
                    tampilDataUser();
                    break;
                case 3:
                   hapusDataUser();
                    break;
                case 4:
                    insertDataLost();
                    break;
                case 5:
                    tampilDataLost();
                    break;
                case 6:
                   hapusDataLost();
                    break;
                case 7:
                    insertDataFound();
                    break;

                case 8:
                    tampilDataFound();
                    break;
                case 9:
                    hapusDataLost();
                    break;
            }

        } while (n < 10);

    }

    private static void insertDataUser() {
        Users u = new Users();
        System.out.println("masukkan id user");
        u.setId(s.nextInt());
        System.out.println("masukkan nama");
        u.setName(s.next());
        System.out.println("masukkan password");
        u.setPassword(s.next());

        try {
            Connection conn = getConnect();
            String queryInsert = "INSERT INTO users(id,name,password)VALUES( ?,  ?,  ?)";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, u.getId());
            ps.setString(2, u.getName());
            ps.setString(3, u.getPassword());

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Insert data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }

    private static void insertDataLost() {
        Lost l = new Lost();
        System.out.println("masukkan id ");
        l.setId(s.nextInt());
        System.out.println("masukkan nama");
        l.setName(s.next());
        System.out.println("masukkan type");
        l.setType(s.next());

        try {
            Connection conn = getConnect();
            String queryInsert = "INSERT INTO item_losts(id,item_name,item_type)VALUES( ?,  ?,  ?)";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, l.getId());
            ps.setString(2, l.getName());
            ps.setString(3, l.getType());

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Insert data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }

       private static void insertDataFound() {
        Found l = new Found();
        System.out.println("masukkan id ");
        l.setId(s.nextInt());
        System.out.println("masukkan nama");
        l.setName(s.next());
        System.out.println("masukkan type");
        l.setType(s.next());

        try {
            Connection conn = getConnect();
            String queryInsert = "INSERT INTO item_founds(id,item_name,item_type)VALUES( ?,  ?,  ?)";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, l.getId());
            ps.setString(2, l.getName());
            ps.setString(3, l.getType());

            int rowAffected = ps.executeUpdate();

            if (rowAffected > 0) {
                System.out.println("Insert data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }

    private static void tampilDataUser() {
        System.out.println("masukan id yang ingin di tampilkan");
        int id = s.nextInt();
        try{
            Connection conn = getConnect();
            String kueri = "SELECT * FROM users WHERE id= ?";
            PreparedStatement ps = conn.prepareStatement(kueri);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                System.out.println(" \n id :"+rs.getInt("id") +"\n nama : "+rs.getString("name")+"\n password (untuk tugas ini ) : " + rs.getString("password"));
            }
            rs.close(); ps.close();conn.close();
            
        }catch(SQLException ex){
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }
    
    
    private static void tampilDataFound() {
        System.out.println("masukan id yang ingin di tampilkan");
        int id = s.nextInt();
        try{
            Connection conn = getConnect();
            String kueri = "SELECT * FROM item_founds WHERE id= ?";
            PreparedStatement ps = conn.prepareStatement(kueri);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                System.out.println(" - "+rs.getInt("id") +" -  "+rs.getString("item_name")+" -    " + rs.getString("item_type"));
            }
            rs.close(); ps.close();conn.close();
            
        }catch(SQLException ex){
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }
    
    
    private static void tampilDataLost() {
        System.out.println("masukan id yang ingin di tampilkan");
        int id = s.nextInt();
        try{
            Connection conn = getConnect();
            String kueri = "SELECT * FROM item_losts WHERE id= ?";
            PreparedStatement ps = conn.prepareStatement(kueri);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            
            while(rs.next()){
                 System.out.println(" - "+rs.getInt("id") +" -  "+rs.getString("item_name")+" -    " + rs.getString("item_type"));
            }
            rs.close(); ps.close();conn.close();
            
        }catch(SQLException ex){
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }

    private static void hapusDataUser() {
        System.out.println("masukkan id di hapus");
        int id = s.nextInt();

        try {
            Connection conn = getConnect();
            String queryInsert = "DELETE FROM users WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, id);
            
            int rowAffected = ps.executeUpdate();

            if (rowAffected < 1) {
                System.out.println("Hapus data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }
    
    private static void hapusDataLost() {
        System.out.println("masukkan id di hapus");
        int id = s.nextInt();

        try {
            Connection conn = getConnect();
            String queryInsert = "DELETE FROM item_lost WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, id);
            
            int rowAffected = ps.executeUpdate();

            if (rowAffected < 1) {
                System.out.println("Hapus data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }
    
    private static void hapusDataFound() {
        System.out.println("masukkan id di hapus");
        int id = s.nextInt();

        try {
            Connection conn = getConnect();
            String queryInsert = "DELETE FROM item_founds WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(queryInsert);
            ps.setInt(1, id);
            
            int rowAffected = ps.executeUpdate();

            if (rowAffected < 1) {
                System.out.println("Hapus data berhasil");
            }
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Eksepsi akses data: " + ex.getMessage());
        }
    }
}
