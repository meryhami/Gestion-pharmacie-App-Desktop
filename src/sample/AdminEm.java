package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminEm {
    static Connection conn=null;
    public static java.sql.Connection getconnection()
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

    public static List<Emploiyer> getAdmis() {
        List<Emploiyer> list = new ArrayList<Emploiyer>();
        try {
            String sql = "SELECT * FROM `userp` WHERE 1";
            Connection conn = AdminEm.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Emploiyer adm = new Emploiyer();
                adm.setId(resultSet.getInt(1));
                adm.setUsername(resultSet.getString(2));
                adm.setPassword(resultSet.getString(3));
                list.add(adm);

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
