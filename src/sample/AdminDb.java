package sample;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDb {
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

    public static List<Admin> getAdmis() {
        List<Admin> list = new ArrayList<Admin>();
        try {
            String sql = "SELECT * FROM `users` WHERE 1";
            Connection conn = AdminDb.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Admin adm = new Admin();
                adm.setId(resultSet.getInt(1));
                adm.setUsername(resultSet.getString(2));
                adm.setPassword(resultSet.getString(3));
                System.out.println(adm.getPassword());
                System.out.println(adm.getUsername());
                list.add(adm);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static int ajouter(Admin em)
    {
        int s=0;
        try{
            String sql="INSERT INTO `users`(`username`, `password`) VALUES (?,?)";
            Connection con=Dbinfo.getconnection();
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1,em.getUsername());
            ps.setString(2,em.getPassword());
            s=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return  s;

    }



}
