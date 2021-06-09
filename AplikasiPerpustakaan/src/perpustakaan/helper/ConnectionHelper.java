/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.helper;
import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author CodeVector
 */
public class ConnectionHelper {
    
    //Deklarasi Variable untuk connect ke Database
    public static final String DB_NAME = "db_perpustakaan";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    
    //Membuat method untuk mengembalikan nilai Connection
    //juga melalukan pelemparan atau throws ke class SQLExction bila terjadi error
    public static Connection getConnection() throws SQLException{
        
        //Instansiasi DriverManager 
        //Mendaftarkan Driver baru untuk melakukan Koneksi ke database
        DriverManager.registerDriver(new Driver());
        
        //Instansiasi var connection 
        //nilainya adalah getConnection yang bertujuan untuk melakaukan koneksi
        //ke Database dengan memasukkan Parameter
        //Seperti URL driver dan nama database, alamat ip atau nama host,
        //nama user mysql dan passwordnya
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        
        //mengembalikan nilai connetion
        return connection;
    }
}
