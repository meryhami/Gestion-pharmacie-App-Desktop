package sample;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class DialogMEDICASUP {
    public JFXTextField labael;

        public void supremer(ActionEvent event) {
            String label=labael.getText();
            int a=medicaminfo.getmedicament(label).getId_medicament();
            viewcateg v= new viewcateg();
            if(a!=0)
            {
                Medicament cat=medicaminfo.getmedicament(label);
                int status=medicaminfo.delet(cat);
                /** v.sup(cat,status);*/
                if(status>0)
                {

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("suppression");
                    alert.setContentText("medicament supprimer!");
                    alert.setHeaderText("information ");
                    alert.showAndWait();


                }
                else {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("suppression");
                    alert.setContentText(" vous ne pouvez pas supprimer ce medicament  , il est affecté deja a une facture");
                    alert.setHeaderText("information");
                    alert.showAndWait();




                }

            }
            else {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("suppression");
                alert.setContentText("Erorr ,peut etre que vous essaiez de supprimer un medicament qui n'existe pas");
                alert.setHeaderText("information ");
                alert.showAndWait();




            }




        }





}


