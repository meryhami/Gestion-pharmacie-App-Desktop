package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modifier {

    public JFXTextField h;
    public JFXTextField nom;
    public JFXComboBox categorie;
    public JFXTextField prix;
    public JFXTextField quantite;
    int status;

    public void go(ActionEvent event) {
        String label=h.getText();
       String a=medicaminfo.getmedicament(label).getNom_medicament();
        if(a != null)
        {
            nom.setText(a);
            categories cat =Dbinfo.getcategorie(medicaminfo.getmedicament(label).getId_categorie());
            prix.setText(medicaminfo.getmedicament(label).afficher());
            quantite.setText(medicaminfo.getmedicament(label).afficherqantite());
            categorie.setPromptText(cat.getLabel());

        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data inserted");
            alert.setContentText("ce medicament n'existe pas");
            alert.setHeaderText("information dialog");
            alert.showAndWait();
        }


    }
    public ObservableList<categories> data= FXCollections.observableArrayList();


    public void inisialiser()
    {
        try {
            String sql = "SELECT * FROM `categorie` WHERE 1";
            Connection con= Dbinfo.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new categories(resultSet.getString(2)));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        categorie.setItems(data);
    }


   public void modifier()
    {


        int id =medicaminfo.getmedicament(h.getText()).getId_medicament();
        int quantitencien=medicaminfo.getmedicament(h.getText()).getQuantité();
        Float prixencien= medicaminfo.getmedicament(h.getText()).getPrix();
        int categoriancienn=medicaminfo.getmedicament(h.getText()).getId_categorie();
        String nomancienn=medicaminfo.getmedicament(h.getText()).getNom_medicament();
        String nommedi=nom.getText();
        String q=quantite.getText();
        int c=Dbinfo.getcategorie(String.valueOf(categorie.getValue())).getId();
        String p=prix.getText();
        Medicament medicament=new Medicament();
        medicament.setId_medicament(id);
        medicament.setId_categorie(c);
        medicament.setPrix(Float.parseFloat(p));
        medicament.setQuantité(Integer.parseInt(q));
        medicament.setNom_medicament(nommedi);

       // int status=medicaminfo.updateq(medicament);// int status=medicaminfo.update(medicament);
      // int status2=medicaminfo.updatcat(medicament);
      // int status3=medicaminfo.updateprice(medicament);
      if(!nommedi.equals(nomancienn)) {
          int status4 = medicaminfo.updatnom(medicament);
      }
      if(Integer.parseInt(q)!=quantitencien)
      {
          int status=medicaminfo.updateq(medicament);
      }
        if(prixencien!=Float.parseFloat(p))
        {
            int status=medicaminfo.updateprice(medicament);
        }
       if(categoriancienn!=c)
        {
            int status=medicaminfo.updatcat(medicament);
        }




        if(status>0)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data modifie");
            alert.setContentText("modificatin succed");
            alert.setHeaderText("information dialog");
            alert.showAndWait();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data  modifie");
            alert.setContentText("modification succed");
            alert.setHeaderText("information dialog");
            alert.showAndWait();



        }



    }
    }


