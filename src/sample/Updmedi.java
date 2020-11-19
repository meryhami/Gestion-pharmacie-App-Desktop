package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static sample.Dbinfo.*;

public class Updmedi {
    public ChoiceBox choice;
    public TextField nom;
    public TextField quantite;
    public TextField prix;
    public ComboBox<categories> combox;
    public ObservableList<categories>data= FXCollections.observableArrayList();
   public void inisialiser()
    {
        try {
            String sql = "SELECT * FROM `categorie` WHERE 1";
            Connection con= getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new categories(resultSet.getString(2)));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        combox.setItems(data);
    }

    public void insertData(ActionEvent event)throws IOException, SQLException
    {

        String nommedi=nom.getText();
        String q=quantite.getText();
        String p=prix.getText();
        Medicament m= new Medicament();
        m.setNom_medicament(nommedi);
        m.setQuantité(Integer.parseInt(q));
        m.setPrix(Float.parseFloat(p));
        m.setId_categorie(getcategorie(String.valueOf(combox.getValue())).getId());

        System.out.println(getcategorie(String.valueOf(combox.getValue())));

       // System.out.println(combox.getValue());
        int status=medicaminfo.ajouter(m);

        if(status>0)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data inserted");
            alert.setContentText("yees!!");
            alert.setHeaderText("information dialog");
            alert.showAndWait();

        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data inserted");
            alert.setContentText("Erorr insert");
            alert.setHeaderText("information dialog");
            alert.showAndWait();



        }



    }}
