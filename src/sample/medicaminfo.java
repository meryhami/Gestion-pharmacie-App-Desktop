package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class medicaminfo {
    static Connection conn=null;
        public static Connection getconnection() {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost/pharmacie";
                conn = DriverManager.getConnection(url, "root", "rootroot");
                System.out.println("connected");


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return conn;
        }
        public static int ajouter(Medicament medicament)
        {
            int s=0;
            try{
                String sql="INSERT INTO `medicament`(  `id_categorie`,`quantite`, `prix`, `nom_medica`) VALUES (?,?,?,?)";
                Connection con=medicaminfo.getconnection();
                PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
                ps.setInt(1,medicament.getId_categorie());
                ps.setInt(2,medicament.getQuantité());
                ps.setDouble(3,medicament.getPrix());
                ps.setString(4,medicament.getNom_medicament());
                s=ps.executeUpdate();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return  s;

        }
     /** public static int update(Medicament medicament)
        {
            int s=0;
            try{
                String sql="UPDATE`medicament`SET`id_categorie`=?,`quantite`=?,`prix`=?,`nom_medica`=? WHERE id_medicament=?";
                Connection con=medicaminfo.getconnection();
                PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
                ps.setInt(1,medicament.getId_categorie());
                ps.setInt(2,medicament.getQuantité());
                ps.setDouble(3,medicament.getPrix());
                ps.setString(4,medicament.getNom_medicament());
                ps.setInt(5,medicament.getId_medicament());

                s=ps.executeUpdate();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return s;

        }*/
    public static int updateq(Medicament medicament)
    {
        int s=0;
        try{
            String sql="UPDATE`medicament`SET `quantite`=? WHERE id_medicament=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,medicament.getQuantité());
            ps.setInt(2,medicament.getId_medicament());
            s=ps.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;
    }
    public static int updatnom(Medicament medicament)
    {
        int s=0;
        try{
            String sql="UPDATE`medicament`SET `nom_medica`=? WHERE id_medicament=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1,medicament.getNom_medicament());
            ps.setInt(2,medicament.getId_medicament());

            s=ps.executeUpdate();
            con.setAutoCommit(false);

            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;

    }
    public static int updatcat(Medicament medicament)
    {
        int s=0;
        try{
            String sql="UPDATE`medicament`SET `id_categorie`=? WHERE id_medicament=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1,medicament.getId_categorie());
            ps.setInt(2,medicament.getId_medicament());
            s=ps.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;

    }
    public static int updateprice(Medicament medicament)
    {
        int s=0;
        try{
            String sql="UPDATE`medicament`SET `prix`=? WHERE id_medicament=?";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setDouble(1,medicament.getPrix());
            ps.setInt(2,medicament.getId_medicament());

            s=ps.executeUpdate();
            con.setAutoCommit(false);
            con.commit();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return s;

    }




    public static int delet(Medicament medicament){
            int s=0;
            try{
                String sql="DELETE FROM `medicament` WHERE id_medicament=?";
                Connection con=medicaminfo.getconnection();
                PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
                ps.setInt(1,medicament.getId_medicament());
                s=ps.executeUpdate();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return  s;
        }

        public static Medicament getmedicament(String label){
            Medicament cat=new Medicament();

            try{
                String sql="SELECT * FROM `medicament` WHERE nom_medica=?";
                Connection con=medicaminfo.getconnection();
                PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
                ps.setString(1,label);
                ResultSet resultSet=ps.executeQuery();
                if(resultSet.next())
                {
                    cat.setId_medicament(resultSet.getInt(1));
                    cat.setId_categorie(resultSet.getInt(2));
                    cat.setQuantité(resultSet.getInt(3));
                    cat.setPrix(resultSet.getFloat(4));
                    cat.setNom_medicament(resultSet.getString(5));

                }

            }catch (SQLException e){
                e.printStackTrace();
            }
            return  cat;
        }
    public static Medicament getmedicamentparid(int id){
        Medicament cat=new Medicament();

        try{
            String sql="SELECT * FROM `medicament` WHERE id_medicament=id";
            Connection con=medicaminfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
           //s ps.setInt(1,id);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                cat.setId_medicament(resultSet.getInt(1));
                cat.setId_categorie(resultSet.getInt(2));
                cat.setQuantité(resultSet.getInt(3));
                cat.setPrix(resultSet.getFloat(4));
                cat.setNom_medicament(resultSet.getString(5));

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  cat;
    }
        public static List<Medicament> getmedicaments() {
            List<Medicament> list = new ArrayList<Medicament>();
            try {
                String sql = "SELECT * FROM `medicament` WHERE 1";
                Connection con= medicaminfo.getconnection();
                PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Medicament adm = new Medicament();
                    adm.setId_medicament(resultSet.getInt(1));
                    adm.setId_categorie(resultSet.getInt(2));
                    adm.setQuantité(resultSet.getInt(3));
                    adm.setPrix(resultSet.getFloat(4));
                    adm.setNom_medicament(resultSet.getString(5));
                    list.add(adm);
      
                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }












}
