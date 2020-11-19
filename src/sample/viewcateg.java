package sample;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

public class viewcateg implements Initializable {

    public JFXListView <categories>listviw;
    public ObservableList<categories> data= FXCollections.observableArrayList();
    public ObservableList<String> dat = FXCollections.observableArrayList();
    public JFXTextField h;
    public JFXTextField chercher;
    public BorderPane o;
    public DialogPane dialog;
    public JFXTextField labael;


   public FilteredList<String> filteredData;
    public ListView<String> l;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            String sql = "SELECT * FROM `categorie` WHERE 1";
            Connection con= Dbinfo.getconnection();
            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                data.add(new categories(resultSet.getInt(1),resultSet.getString(2)));
               dat.add(resultSet.getString(2));

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        listviw.setItems(data);


         filteredData = new FilteredList<>(dat, s -> true);
        chercher.textProperty().addListener(obs->{
            String filter = chercher.getText();
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
}

    public void Sheepdog()  {
       Dialog<ButtonType>padang=new Dialog<>();
       padang.initOwner(o.getScene().getWindow());
       try
       {
           Parent root=FXMLLoader.load(getClass().getResource("chercher.fxml"));
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
    public void showmodifi()  {
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


/**public  void sup(categories cat,int i) {
    if (i > 0) {
        filteredData = new FilteredList<>(dat, s -> true);
        l = new ListView<>(filteredData);


        try {
            o.setLeft(l);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
*/
    public void insertData(ActionEvent event)throws IOException, SQLException
    {
        String label=h.getText();
        String a=Dbinfo.getcategorie(label).getLabel();

        categories cat=new categories();
        cat.setLabel(label);
        if(label.isEmpty()||a!=null)
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data inserted");
            alert.setContentText("Erorr ,vous essaiez d'ajouter du vide ou bien une categorie deja existe ");
            alert.setHeaderText("information dialog");
            alert.showAndWait();

        }
        else {
            int status = Dbinfo.ajouter(cat);
            if (status > 0) {
                filteredData = new FilteredList<>(dat, s -> true);
                l= new ListView<>(filteredData);


                try{
                    o.setLeft(l);

                }catch(Exception e){
                    e.printStackTrace();
                }

                dat.add(cat.getLabel());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Data inserted");
                alert.setContentText("categorie ajouter!!");
                alert.setHeaderText("information dialog");
                alert.showAndWait();




            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data inserted");
                alert.setContentText("Erorr ");
                alert.setHeaderText("information dialog");
                alert.showAndWait();


            }
        }
    }


   }




