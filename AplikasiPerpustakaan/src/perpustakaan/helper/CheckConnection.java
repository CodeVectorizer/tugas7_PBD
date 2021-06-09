/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.helper;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author CodeVector
 */
public class CheckConnection {
    public static void main(String[] args) {
        //Memanggil Method isConnetced
        isConnected();
        
        //Memanggil Method showBook
        showBook();
    }
    
    //Membuat method non-void isConnected 
    //dan mereturn nilai boolean
    public static boolean isConnected() {
        
        //Membuat try-catch untuk menangani error
        try {
            //Memanggil Class ConnetionHelper
            //lalu memanggil method getConnection
            ConnectionHelper.getConnection();
            
            //Cetak "Database Connected" apabila berhasil konek ke db
            System.out.println("Database Connected");
            
            //mengembalikan nilai true apabila berhasil konek ke db
            return true;
            
            //menggunakan SQLException untuk menangkap error
        } catch (SQLException e) {
            //Cetak "Failed to connect database" apabila gagal konek ke db
            System.out.println("Failed to connect database");
            
            //Melakukan Logging atau pencatatan bila ada error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e);;
            
            //mengembalikan nilai false apabila gagal konek ke db
            return false;
        }
    }
    
    public static void showBook(){
        try {
            //Memanggil Class ConnetionHelper
            //lalu memanggil method getConnection
            //lalu disimpan ke variable conn
            Connection conn = ConnectionHelper.getConnection();
            
            //Memanggil statement untuk melakukan pemanggilan statement sql
            //agar bisa melalukan query
            Statement stmn = conn.createStatement();
            
            //Melalukan ExecuteQuery agar bisa mengambil data dari database
            //lalu disimpan ke variabel rs atau ResultSet
            ResultSet rs = stmn.executeQuery("SELECT * FROM buku");
            
            //Melakukan iterable atau loopin bila ada datanya di dalam database
            while(rs.next()){
                //Melakuakan pencetakan atau tampil ke output
                //seperti id buku, judul buku, pengarang, penerbit dan tahun terbit
                System.out.println("ID Buku: " + rs.getString("ISBN")
                        + "Judul Buku: "+ rs.getString("judul_buku")
                        + "Pengarang: "+ rs.getString("pengarang")
                        + "Penerbit: " + rs.getString("penerbit")
                        + "Tahun Terbit: " + rs.getString("tahun_terbit"));                        
            }
             //menggunakan SQLException untuk menangkap error
        } catch (SQLException e) {
            
            //Melakukan Logging atau pencatatan bila ada error
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e);
            
            //Cetak "Failed to connect database" apabila gagal konek ke db
            System.out.println("Failed to connect database");
        }
    }
}