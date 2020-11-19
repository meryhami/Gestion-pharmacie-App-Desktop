package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.control.CheckBox;

public class Medicament extends RecursiveTreeObject<Medicament> {
   private int id_medicament;
   private int id_categorie;
   private int quantite ;
   private  float prix;
   private String nom_medicament;


    public Medicament(int id_medicament, int id_categorie, int quantite, float prix, String nom_medicament) {
        this.id_medicament = id_medicament;
        this.id_categorie = id_categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.nom_medicament = nom_medicament;
    }




    public int getId_medicament() {
        return id_medicament;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getQuantité() {
        return quantite;
    }

    public void setQuantité(int quantité) {
        this.quantite = quantité;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom_medicament() {
        return nom_medicament;
    }

    public void setNom_medicament(String nom_medicament) {
        this.nom_medicament = nom_medicament;
    }

    public Medicament() {
    }

    @Override
    public String toString() {
        return nom_medicament;
    }


    public String afficher (){
        return String.valueOf(prix);
    }

    public String afficherqantite (){
        return String.valueOf(quantite);
    }
}
