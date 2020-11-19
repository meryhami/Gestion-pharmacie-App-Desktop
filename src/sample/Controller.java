package sample;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.pdfjet.Letter;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class Controller {

    @FXML
    public Button button;
    @FXML
    public TextField text;
    @FXML
    public PasswordField passw;
    @FXML
    public Label label;
    @FXML
    public BorderPane dialg;
    public AnchorPane anchore;
    public JFXTextField ffacture;
    public Label facnum;
    public JFXTextField nommedi;
    public JFXTextField quantit;
    public DatePicker datepiker;
    public ObservableList<ligne_facture> data= FXCollections.observableArrayList();
    public ObservableList<ligne_facture> data2= FXCollections.observableArrayList();

    public TableView <ligne_facture>table;
    public TableColumn <ligne_facture, Integer>id_facture;
    public TableColumn <ligne_facture, Integer>id_medica;
    public TableColumn <ligne_facture, String>Date;
    public TableColumn <ligne_facture, Integer>quantite;
    public TableColumn <ligne_facture, Integer> id;
    static Double total=0.0;
    static Double rest=0.0;
    public Label k;
    public VBox info;
    public BorderPane fac;
    public JFXButton enre;
    public JFXTextField sommp;
    public DatePicker datrest;
    public Label rrest;
    public TableView <ligne_facture>table2;
    public TableColumn <ligne_facture, Integer>id2;
    public TableColumn <ligne_facture, Integer>id_medica2;
    public TableColumn <ligne_facture, String>Date2;
    public TableColumn <ligne_facture, Integer>quantite2;
    public JFXTextField cherch;
    public Pane cher;
    public JFXButton chb;
    public Label so;
    public Label t;
    public Label d;
    public JFXTextField employer;
    public JFXPasswordField p;
    public BorderPane a;
    public Label entre2;
    public Label label2;
    public JFXButton imprimer;
    public Label kl;


    int v=0;


    public void entrer(ActionEvent event) throws IOException, SQLException {
        List<Admin> list = AdminDb.getAdmis();
        Map<String, String> map = new HashMap<String, String>();
        for (Admin a : list) {
            map.put(a.getUsername(), a.getPassword());
        }
        System.out.println("voile le username "+text.getText()+"  "+map.get(text.getText()));
        if (map.containsKey(text.getText())) {
            String value = map.get(text.getText());

            if (value.equals(passw.getText())) {
                AdminDb.getconnection();
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(getClass().getResource("Main1.fxml"));
                Parent view=loader.load();
                Scene scene=new Scene(view);
                Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } else {
                label.setVisible(true);
                ;
            }
        } else {
            label2.setVisible(true);
        }


    }

public void entrer2(ActionEvent event) throws IOException, SQLException {
        List<Emploiyer> list = AdminEm.getAdmis();
        Map<String, String> map = new HashMap<String, String>();
        for (Emploiyer a : list) {
            map.put(a.getUsername(), a.getPassword());
        }
        if (map.containsKey(employer.getText())) {
            String value = map.get(employer.getText());
            if (value.equals(p.getText())) {
                AdminEm.getconnection();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("stock.fxml"));
                } catch (Exception e) {
                }
                a.setCenter(root);

            } else {
                entre2.setVisible(true);
                ;
            }
        } else {
            entre2.setVisible(true);
        }


    }

    public void logout(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Main.fxml"));
        Parent view=loader.load();
        Scene scene=new Scene(view);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void Home(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Main1.fxml"));
        Parent view=loader.load();
        Scene scene=new Scene(view);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void Shownewdialog() throws IOException, SQLException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("categorie.fxml"));
        } catch (Exception e) {
        }
        dialg.setCenter(root);
    }
    public void Showmedicament() throws IOException, SQLException {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("medicament.fxml"));
        } catch (Exception e) {
        }
        dialg.setCenter(root);
    }
        public void Shownstock() throws IOException, SQLException {
            Parent root = null;
            try {
                root= FXMLLoader.load(getClass().getResource("accerstok.fxml"));
            } catch (Exception e) {
            }
            dialg.setCenter(root);
        }
    public void Showfacture() throws IOException, SQLException {
        Parent root = null;
        try {
            root= FXMLLoader.load(getClass().getResource("fectureaccui.fxml"));
        } catch (Exception e) {
        }
        dialg.setCenter(root);
    }

    public void Showfacturer() throws IOException, SQLException {
        Parent root = null;
        try {
            root= FXMLLoader.load(getClass().getResource("facture.fxml"));
        } catch (Exception e) {
        }
        fac.setCenter(root);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bienvenu !!");
        alert.setContentText("veuillez svp attribuer a votre facture un code pour reussir la vente ");
        alert.setHeaderText("realisation de facture ");
        alert.showAndWait();
    }
    public void cherchbut()throws IOException, SQLException
    {
        Parent root = null;
        try {
            root= FXMLLoader.load(getClass().getResource("chercherfac.fxml"));
        } catch (Exception e) {
        }
        fac.setCenter(root);
    }
    public void ajouterf(ActionEvent event)
    {

        int s=0;
        int a;
        a = Integer.parseInt(ffacture.getText());
        Facture f=new Facture();
        f.setId_facture(a);
        f.setSommepaye(0.0);
        f.setPayera("");
        f.setTotale(0.0);
       s= factureinfo.ajouterfactur(f);

        if(s>0)
        {
            info.setVisible(true);
            ffacture.setVisible(false);
            enre.setVisible(false);

            v=1;
            facnum.setText(" "+a);
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data inserted");
            alert.setContentText(" votre facture est prete a renmplire");
            alert.setHeaderText("information dialog");
            alert.showAndWait();
            v=1;

        }
        else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data inserted");
            alert.setContentText("cette facture existe deja ");
            alert.setHeaderText("information dialog");
            alert.showAndWait();



        }



    }
    public void effectuervent(ActionEvent Event) {

        int s=0;
        int j;
        ligne_facture f= new ligne_facture();

        f.setId_facture(Integer.parseInt(ffacture.getText()));
        f.setId_medica(medicaminfo.getmedicament(nommedi.getText()).getId_medicament());
        f.setDate(datepiker.getEditor().getText());
        f.setQuantite(Integer.parseInt(quantit.getText()));
        s=factureinfo.ajouter(f);
        if(s>0) {
            if (Integer.parseInt(quantit.getText()) <= medicaminfo.getmedicament(nommedi.getText()).getQuantité())
            {

                Medicament m = medicaminfo.getmedicament(nommedi.getText());
                rest = rest + Double.valueOf(sommp.getText());
                total = total + m.getPrix() * Integer.parseInt(quantit.getText());
                Facture facture = new Facture();
                facture.setId_facture(Integer.parseInt(ffacture.getText()));
                facture.setPayera(datrest.getEditor().getText());
                facture.setSommepaye(rest);
                facture.setTotale(total);
                m.setQuantité(m.getQuantité() - Integer.parseInt(quantit.getText()));
                k.setText("  " + total);
                rrest.setText("  " + (total - rest));
                kl.setText("  "+datepiker.getEditor().getText());
                j = medicaminfo.updateq(m);
                factureinfo.updatdatp(facture);


            try {
                String sql = "SELECT * FROM `ligne_facture` WHERE id_facture=" + ffacture.getText() + "";
                Connection con = factureinfo.getconnection();
                PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    data.add(new ligne_facture(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5)));

                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            id.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("id"));
            id_medica.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("id_medica"));
            Date.setCellValueFactory(new PropertyValueFactory<ligne_facture, String>("Date"));
            quantite.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("quantite"));
            table.setItems(data);
        }
            else /**(Integer.parseInt(quantit.getText())>medicaminfo.getmedicament(nommedi.getText()).getQuantité())*/
            {

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Merci pour votre vente!!");
                alert.setContentText("quantité est insufisante ");
                alert.setHeaderText("realiser vente");
                alert.showAndWait();

            }
        }




    }




    public  void chercherf(ActionEvent Event)
    {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Merci pour votre recherche!!");
        alert.setContentText("si vous voulez faire une autre recherche cliquer sur chercher qu'est en haut ");
        alert.setHeaderText("chercher  facture ");
        alert.showAndWait();
        cherch.setVisible(false);
        chb.setVisible(false);
        int a= factureinfo.cherchersom(Integer.parseInt(cherch.getText())).id_facture;
        System.out.println(a);
        if(a==0)
        {
            Alert b=new Alert(Alert.AlertType.INFORMATION);
            b.setTitle("Merci pour votre recherche!!");
            b.setContentText("ouups cette facture n'existe pas  ");
            b.setHeaderText("chercher  facture ");
            b.showAndWait();
        }
        else {
            try {
                String sql = "SELECT * FROM `ligne_facture` WHERE id_facture=" + cherch.getText() + "";
                Connection con = factureinfo.getconnection();
                PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    data2.add(new ligne_facture(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5)));

                }
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            id2.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("id"));
            id_medica2.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("id_medica"));
            Date2.setCellValueFactory(new PropertyValueFactory<ligne_facture, String>("Date"));
            quantite2.setCellValueFactory(new PropertyValueFactory<ligne_facture, Integer>("quantite"));
            table2.setItems(data2);
            System.out.println(data2);
            Facture g;
            g = factureinfo.cherchersom(Integer.parseInt(cherch.getText()));
            so.setText("" + g.getSommepaye());
            t.setText("" + g.getTotale());
            d.setText("" + g.getPayera());
        }
    }


    public void viewmedicament (ActionEvent event)throws IOException, SQLException
    {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("medicament.fxml"));
        Parent view=loader.load();
        Scene scene=new Scene(view);
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void view (ActionEvent event)throws IOException, SQLException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("viewcat.fxml"));
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void updatemedicament (ActionEvent event)throws IOException, SQLException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("updmedi.fxml"));
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void stock (ActionEvent event)throws IOException, SQLException
    {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("stock.fxml"));
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public void cherchermedi(ActionEvent event)throws IOException, SQLException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("chercher.fxml"));
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void facture(ActionEvent event)throws IOException, SQLException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("facture.fxml"));
        Scene scene = new Scene(root, 1060, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void actionbt(ActionEvent  event) throws FileNotFoundException {
        String path;
        FileChooser j = new FileChooser();
        j.setTitle("Open Resource File");
        j.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF file", "*pdf"));
        j.setInitialFileName("united.pdf");

        File file = j.showSaveDialog(null);
        if (file != null) {
            path = file.getAbsolutePath();
            FileOutputStream fos = new FileOutputStream(path);
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, fos);
                document.open();
                Image logo=Image.getInstance("C://Users/pc/Desktop/logo.jpg");
                logo.scaleAbsoluteWidth(600);
                logo.scaleAbsoluteHeight(92);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
                //PdfPTable t=new PdfPTable(3);
               //t.addCell("item1");
                //t.addCell("item2");
                //t.addCell("item3");
                //document.add(t);
               // Facture g;
                //g = factureinfo.cherchersom(Integer.parseInt(cherch.getText()));
                //so.setText("" + g.getSommepaye());
               // t.setText("" + g.getTotale());
                //d.setText("" + g.getPayera());
                Paragraph p0=new Paragraph("Le "+datepiker.getEditor().getText());
                Paragraph p1=new Paragraph("Bonjour Monsieur ");

                Paragraph p2=new Paragraph("Numero de facture :"+facnum.getText());
                List<String>l=new ArrayList<String>();

                //Paragraph p3=new Paragraph(data.toString());
                try {
                    String sql = "SELECT * FROM `ligne_facture` WHERE id_facture=" + ffacture.getText() +";";
                    Connection con = factureinfo.getconnection();
                    PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        //data.add(new ligne_facture(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getInt(5)));
                        //Paragraph p=new Paragraph(medicaminfo.getmedicamentparid(resultSet.getInt(2)).getNom_medicament());
                        l.add(medicaminfo.getmedicamentparid(resultSet.getInt(2)).getNom_medicament());
                        //document.add(p);

                    }
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Paragraph p=new Paragraph(String.valueOf(total));
                Paragraph p6=new Paragraph("Les medicament vendu :");
                document.add(p0);
                document.add(p1);
                document.add(p2)   ;
                document.add(p6);
                for(String a:l)
                {
                    Paragraph p5=new Paragraph(a+"  son  prix :"+medicaminfo.getmedicament(a).getPrix());
                    document.add(p5);

                }
                Paragraph p8=new Paragraph("Le Totale de la facture  :" +total+"  DH ");


                Paragraph p7=new Paragraph("Le reste a payer   :"+rest+"  DH ");
                document.add(p8);
                document.add(p7);


                document.close();


            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }}