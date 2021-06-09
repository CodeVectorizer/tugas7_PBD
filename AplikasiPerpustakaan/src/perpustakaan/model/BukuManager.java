/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan.model;

//Import depedency class
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import perpustakaan.helper.CheckConnection;
import perpustakaan.helper.ConnectionHelper;

/**
 *
 * @author CodeVector
 */
public class BukuManager {
    public static List<Buku> showAllBook() {
        //Instansiasi List dengan model buku
        //dan dimasukkan ke dalam bukulist
        //dengan nilai new ArrayList
        List<Buku> bukulist = new ArrayList<>();        
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
            
            //Mengecek dan melakukan perulangan bila ada data
            while (rs.next()) {
                
                //Memanggil Buku ke variabel buku
                Buku buku = new Buku();
                
                //mengeset properti didalam class buku
                //yang datanya diambil dari database
                buku.setId_buku(rs.getString("id"));
                buku.setJudul_buku(rs.getString("judul_buku"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setTahun_terbit(Integer.parseInt(rs.getString("tahun_terbit")));

                //Menambahkan properti kedalam bukulist
                bukulist.add(buku);
            }

            //Menangkap error apablila terjadi error
        } catch (Exception e) {
            //melakukan logging apabila ada error didalam class CheckConnection 
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, e);;

        }
        //Mengembalikan nilai bukulist
        return bukulist;
    }
    
}
