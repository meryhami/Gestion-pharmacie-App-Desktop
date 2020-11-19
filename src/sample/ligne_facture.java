package sample;

import java.util.Date;

public class ligne_facture {
    int id;
    int id_medica;
    int id_facture;
    String date ;
    int quantite;

    public ligne_facture(int id, int id_medica, int id_facture, double prix, String date,int quantite) {
        this.id = id;
        this.id_medica = id_medica;
        this.id_facture = id_facture;
        this.date = date;
        this.quantite=quantite;
    }


    public ligne_facture(int id_medica, int id_facture, String date, int quantite) {
        this.id_medica = id_medica;
        this.id_facture = id_facture;
        this.date = date;
        this.quantite = quantite;
    }

    public ligne_facture(int id_medica, String date, int quantite) {
        this.id_medica = id_medica;
        this.date = date;
        this.quantite = quantite;
    }

    public ligne_facture() {

    }

    public ligne_facture(int id, int id_medica, int id_facture, String date, int quantite) {
        this.id = id;
        this.id_medica = id_medica;
        this.id_facture = id_facture;
        this.date = date;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_medica() {
        return id_medica;
    }

    public void setId_medica(int id_medica) {
        this.id_medica = id_medica;
    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "ligne_facture{" +
                "id=" + id +
                ", id_medica=" + id_medica +
                ", id_facture=" + id_facture +
                ", date=" + date +
                '}';
    }

}
