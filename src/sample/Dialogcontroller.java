package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class Dialogcontroller {
  public JFXTextField labael;


  public void supremer(ActionEvent event) {
      String label=labael.getText();
      String a=Dbinfo.getcategorie(label).getLabel();
      viewcateg v= new viewcateg();
      if(a!=null)
      {
        categories cat=Dbinfo.getcategorie(label);
        int status=Dbinfo.delet(cat);
       /** v.sup(cat,status);*/
        if(status>0)
        {

          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("suppression");
          alert.setContentText("categorie supprimer!");
          alert.setHeaderText("information ");
          alert.showAndWait();


        }
        else {
          Alert alert=new Alert(Alert.AlertType.ERROR);
          alert.setTitle("suppression");
          alert.setContentText(" vous ne pouvez pas supprimer cette categorie , il est affecté deja a un medicament");
          alert.setHeaderText("information");
          alert.showAndWait();




        }

      }
      else {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("suppression");
        alert.setContentText("Erorr ,peut etre que vous essaiez de supprimer une categorie qui n'existe pas");
        alert.setHeaderText("information ");
        alert.showAndWait();




      }



}}
