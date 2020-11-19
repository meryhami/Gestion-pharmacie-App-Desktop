package sample;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class stock implements Initializable {

    public Label categorie;

    public VBox boxaff;
    public JFXTextField search;
    public JFXListView <Medicament>listview;
    public ObservableList<Medicament> data= FXCollections.observableArrayList();
    public ObservableList<String> dat = FXCollections.observableArrayList();

    public FilteredList<String> filteredData;
    public ListView<String> l;
    public BorderPane o;
    public Label nom;
    public Label quantite;
    public Label prix;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                data.add(adm);
                dat.add(resultSet.getString(5));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listview.setItems(data);

        filteredData = new FilteredList<>(dat, s -> true);


        search.textProperty().addListener(obs->{
            String filter = search.getText();
            if(filter == null || filter.length() == 0) {

                filteredData.setPredicate(s -> true);
            }
            else {
                filteredData.setPredicate(s -> s.contains(filter));
            }
        });

        l= new ListView<>(filteredData);


        try{
            o.setLeft(l);

        }catch(Exception e){
            e.printStackTrace();
        }

        l.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String med=l.getSelectionModel().getSelectedItem();
                Medicament item=medicaminfo.getmedicament(med);
                categories cat=Dbinfo.getcategorie(item.getId_categorie());

                nom.setText(item.getNom_medicament());
                categorie.setText(cat.getLabel());
                prix.setText(item.afficher()+ "DH");
                quantite.setText(item.afficherqantite());
                boxaff.setVisible(true);
            }
        });

    }
    /**public void ajouter()
    {
        l= new ListView<>(filteredData);


        try{
            o.setLeft(l);

        }catch(Exception e){
            e.printStackTrace();
        }
    }**/

    public void Sheepdog()  {
        Dialog<ButtonType> padang=new Dialog<>();
        padang.initOwner(o.getScene().getWindow());
        try
        {
            Parent root= FXMLLoader.load(getClass().getResource("dialogajoumedi.fxml"));
            padang.getDialogPane().setContent(root);

        }catch (IOException e)
        {
            e.printStackTrace();

        }
        padang.getDialogPane().getButtonTypes().add(ButtonType.OK);
        padang.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result=padang.showAndWait();
        if(result.isPresent()&&result.get()==ButtonType.OK)
        {
            System.out.println("ok");
        }
        else {
            System.out.println("cancel");
        }


    }
    public void Shownewdialog() throws IOException, SQLException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("medicament.fxml"));
        } catch (Exception e) {
        }
        o.setCenter(root);
    }
    public void showajou()  {
        Dialog<ButtonType>padang=new Dialog<>();
        padang.initOwner(o.getScene().getWindow());
        try
        {
            Parent root=FXMLLoader.load(getClass().getResource("modifier.fxml"));
            padang.getDialogPane().setContent(root);

        }catch (IOException e)
        {
            e.printStackTrace();

        }
        padang.getDialogPane().getButtonTypes().add(ButtonType.OK);
        padang.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType>result=padang.showAndWait();
        if(result.isPresent()&&result.get()==ButtonType.OK)
        {
            System.out.println("ok");
        }
        else {
            System.out.println("cancel");
        }


    }
    public void showajouuti()  {
        Dialog<ButtonType>padang=new Dialog<>();
        padang.initOwner(o.getScene().getWindow());
        try
        {
            Parent root=FXMLLoader.load(getClass().getResource("ajouterutilisateur.fxml"));
            padang.getDialogPane().setContent(root);

        }catch (IOException e)
        {
            e.printStackTrace();

        }

        /**padang.getDialogPane().getButtonTypes().add(ButtonType.OK);*/
        padang.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType>result=padang.showAndWait();


    }


    public void showdog(ActionEvent event) {

            Dialog<ButtonType>padang=new Dialog<>();
            padang.initOwner(o.getScene().getWindow());
            try
            {
                Parent root=FXMLLoader.load(getClass().getResource("supprimermedicament.fxml"));
                padang.getDialogPane().setContent(root);

            }catch (IOException e)
            {
                e.printStackTrace();

            }
            padang.getDialogPane().getButtonTypes().add(ButtonType.OK);
            padang.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType>result=padang.showAndWait();
            if(result.isPresent()&&result.get()==ButtonType.OK)
            {
                System.out.println("ok");
            }
            else {
                System.out.println("cancel");
            }


        }
    }

