package sample;

import java.util.Date;
import java.util.List;

public class Facture {
    int id_facture;
    Double sommepaye;
    String payera;
    Double totale;

    public Facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public Facture(int id_facture, Double sommepaye, String payera) {
        this.id_facture = id_facture;
        this.sommepaye = sommepaye;
        this.payera = payera;
    }

    public Facture(int id_facture, Double sommepaye, String payera, Double totale) {
        this.id_facture = id_facture;
        this.sommepaye = sommepaye;
        this.payera = payera;
        this.totale = totale;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    public Double getSommepaye() {
        return sommepaye;
    }

    public void setSommepaye(Double sommepaye) {
        this.sommepaye = sommepaye;
    }

    public String getPayera() {
        return payera;
    }

    public void setPayera(String payera) {
        this.payera = payera;
    }

    public Facture() {

    }

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id_facture=" + id_facture +
                '}';
    }
}

