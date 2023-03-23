package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cititor extends Persoana {

    private String userName;
    private  String parola;

    private int nrCartiCitite;
    private Adresa adresa;
    private List<Carte> carti;
    private Abonament abonament;
    
    public Cititor(int id,String username,String parola, String nume, String prenume, LocalDate dataNastere, String gen,
                   int nrCartiCitite, Adresa adresa, List<Carte> carti, Abonament abonament) {
        super(id, nume, prenume, dataNastere, gen);
        this.nrCartiCitite = nrCartiCitite;
        this.adresa = adresa;
        this.carti = carti;
        this.abonament = abonament;
        this.userName=username;
        this.parola=parola;

    }
    public Cititor() {
        super();
        this.nrCartiCitite = 0;
        this.adresa = null;
        this.carti = null;
        this.abonament = null;
        this.userName="user";
        this.parola="parola";
    }
    public int getNrCartiCitite() {
        return nrCartiCitite;
    }
    public Adresa getAdresa() {
        return adresa;
    }
    public List<Carte> getCarti() {
        return carti;
    }
    public Abonament getAbonament() {
        return abonament;
    }
    public void setNrCartiCitite(int nrCartiCitite) {
        this.nrCartiCitite = nrCartiCitite;
    }
    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }
    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }
    public void setAbonament(Abonament abonament) {
        this.abonament = abonament;
    }
    @Override
    public String toString() {
        return "Cititor" + super.toString() + " [nrCartiCitite=" + nrCartiCitite + ", adresa=" + adresa + ", carti=" + carti + ", abonament="
                + abonament + "]";
    }


    public void ImprumutaCarte(Carte c){
        if (carti==null)
            carti=new ArrayList<Carte>();
        if(c.getImprumut()==false )//daca carte nu e imprumutata
            carti.add(c);

    }
    public void ReturneazaCarte(Carte c){
        carti.remove(c);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean VerificareParola(String posibilaParola){
        return this.parola.contentEquals(posibilaParola);
    }
}
