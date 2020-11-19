package sample;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Alert;

public class utilisateur {
    public JFXTextField log;
    public JFXPasswordField pass;

    public void ajoututilisateur()
    {
        Admin admin=new Admin();
        admin.setUsername(log.getText());
        admin.setPassword(pass.getText());
        int s=AdminDb.ajouter(admin);
        if(s!=0)
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout utilisateur");
            alert.setContentText("utilisateur ajouter avec succer");
            alert.setHeaderText("information sur utilisateur");
            alert.showAndWait();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ajout utilisateur");
            alert.setContentText("Error 412");
            alert.setHeaderText("information sur utilisateur");
            alert.showAndWait();

        }
        
    }
}
