package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Biblioteca {
    private List<Carte> carti;
    private List<Cititor> cititori;
    private List<Autor> autori;
    private List<Categorie> categori;
    

    public Biblioteca(List<Carte> carti, List<Cititor> cititori, List<Autor> autori, List<Categorie> categori) {
        this.carti = carti;
        this.cititori = cititori;
        this.autori = autori;
        this.categori = categori;
    }


    public Biblioteca() {
        this.carti = new ArrayList<Carte>();
        this.cititori = new ArrayList<Cititor>();
        this.autori = new ArrayList<Autor>();
        this.categori = new ArrayList<Categorie>();
    }


    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    public void setCititori(List<Cititor> cititori) {
        this.cititori = cititori;
    }

    public void setAutori(List<Autor> autori) {
        this.autori = autori;
    }

    public void setCategori(List<Categorie> categori) {
        this.categori = categori;
    }

    public List<Carte> getCarti() {
        return carti;
    }

    public List<Cititor> getCititori() {
        return cititori;
    }

    public List<Autor> getAutori() {
        return autori;
    }
    
    public List<Categorie> getCategori() {
        return categori;
    }
    
    public void adaugaCategorie(Categorie categ){
        this.categori.add(categ);
    }

    public void adaugaCititor(Cititor cititor){
        this.cititori.add(cititor);
    }

    public void adaugaAutor(Autor autor){
        this.autori.add(autor);
    }
    
    
    public void AdaugaCarte(Carte carte){
        this.carti.add(carte);
    }

    public void SortCarti(){
        Collections.sort(this.carti, new CarteComparator());
    }

}
