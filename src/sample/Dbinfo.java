package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dbinfo {
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

    public static int ajouter(categories categorie)
    {
        int s=0;
        try{
            String sql="INSERT INTO `categorie`(`label`) VALUES (?)";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1,categorie.getLabel());
            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  s;

    }
    public static int delet(categories categorie){
        int s=0;
        try{
            String sql="DELETE FROM `categorie` WHERE id=?";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,categorie.getId());
            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  s;
    }

    public static categories getcategorie(String label){
        categories cat=new categories();

        try{
            String sql="SELECT * FROM `categorie` WHERE label=?";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1,label);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                cat.setId(resultSet.getInt(1));
                cat.setLabel(resultSet.getString(2));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  cat;
    }
    public static categories getcategorie(int id){
        categories cat=new categories();

        try{
            String sql="SELECT * FROM `categorie` WHERE id=?";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                cat.setId(resultSet.getInt(1));
                cat.setLabel(resultSet.getString(2));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  cat;
    }
    public static List<categories> getcategories() {
        List<categories> list = new ArrayList<categories>();
        try {
            String sql = "SELECT * FROM `categorie` WHERE 1";
            Connection con= Dbinfo.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories adm = new categories();
                adm.setId(resultSet.getInt(1));
                adm.setLabel(resultSet.getString(2));
                list.add(adm);

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int getid(String label){
        int s = 0;

        try{
            String sql= "SELECT `id` FROM `categorie` WHERE label=?";
            Connection con= Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1,label);
            ResultSet resultSet=ps.executeQuery();
            s=resultSet.getInt(1);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }





}
