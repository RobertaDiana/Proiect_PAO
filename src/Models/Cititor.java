package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cititor extends Persoana {


    private int nrCartiCitite;
    private Adresa adresa;
    private List<Carte> carti;
    private Abonament abonament;
    
    public Cititor(int id, String nume, String prenume, LocalDate dataNastere, String gen,
                   int nrCartiCitite, Adresa adresa, List<Carte> carti, Abonament abonament) {
        super(id, nume, prenume, dataNastere, gen);
        this.nrCartiCitite = nrCartiCitite;
        this.adresa = adresa;
        this.carti = carti;
        this.abonament = abonament;


    }
    public Cititor() {
        super();
        this.nrCartiCitite = 0;
        this.adresa = null;
        this.carti = null;
        this.abonament = null;

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
        return   "Cititor" + '\n' + super.toString() +
                "nrCartiCitite=" + nrCartiCitite + '\n' +
                "adresa=" + adresa + '\n' +
                "carti=" + carti + '\n' +
                "abonament=" + abonament + '\n';
    }


    public void ImprumutaCarte(Carte c){
        if (carti==null)
            carti=new ArrayList<Carte>();
        if(c.getImprumut()==false )//daca carte nu e imprumutata
            carti.add(c);

    }
    public void ReturneazaCarte(Carte c){
        if (carti==null)
            carti=new ArrayList<Carte>();
        if(carti.isEmpty())
            return;
        carti.remove(c);
    }

}
