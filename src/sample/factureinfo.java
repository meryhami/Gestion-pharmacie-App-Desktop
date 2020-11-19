package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class factureinfo {
    static Connection conn=null;
    public static Connection getconnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost/pharmacie";
            conn= DriverManager.getConnection(url,"root","rootroot");
            System.out.println("connected");



        }catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static int ajouter(ligne_facture facture)
    {
        int s = 0;

        try {
            String sql = "INSERT INTO `ligne_facture`( `id_medica`, `id_facture`, `Date`, `quantite`) VALUES (?,?,?,?)";
            Connection con = factureinfo.getconnection();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1,facture.id_medica);
            ps.setInt(2, facture.id_facture);
            ps.setString(3, facture.date);
            ps.setInt(4, facture.quantite);
            s = ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;

    }
    public static int ajouterfactur(Facture facture)
    {
        int s=0;
        try{
            String sql="INSERT INTO `facture`(`id_facture`,`sommepaye`,`payera`,`totale`) VALUES (?,?,?,?)";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,facture.getId_facture());
            ps.setDouble(2,facture.getSommepaye());
            ps.setString(3,facture.getPayera());
            ps.setDouble(4,facture.getTotale());

            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  s;

    }


    public static int affichier(ligne_facture f)
    {
        int s=0;
        try{
            String sql="SELECT * FROM `ligne_facture` WHERE id_facture=?";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,f.getId_medica());
            ps.setString(2,f.getDate());
            ps.setInt(3,f.getQuantite());
            ps.setInt(4,f.getId_facture());
            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  s;

    }

    public static int updatdatp(Facture facture)
    {
        int s=0;
        try{
            String sql="UPDATE`facture`SET `sommepaye`=?,`payera`=?, `totale`=? WHERE id_facture=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setDouble(1,facture.getSommepaye());
            ps.setString(2,facture.getPayera());
            ps.setDouble(3,facture.getTotale());
            ps.setInt(4,facture.getId_facture());


            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;

    }
    public static Facture cherchersom(int id)
    {
        Facture cat=new Facture();

        try{
            String sql="SELECT * FROM `facture` WHERE id_facture=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                cat.setId_facture(resultSet.getInt(1));
                cat.setSommepaye(resultSet.getDouble(2));
                cat.setPayera(resultSet.getString(3));
                cat.setTotale(resultSet.getDouble(4));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  cat;
    }

}
