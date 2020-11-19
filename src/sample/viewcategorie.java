package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class viewcategorie  implements Initializable {


    public TableView<categories>table;
    public TableColumn<categories, Integer>id;
    public TableColumn<categories,String> label;
    public ObservableList<categories>data= FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String sql = "SELECT * FROM `categorie` WHERE 1";
            Connection con= Dbinfo.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new categories(resultSet.getInt(1),resultSet.getString(2)));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<categories,Integer>("id"));
        label.setCellValueFactory(new PropertyValueFactory<categories,String>("label"));
        table.setItems(data);

    }
}
