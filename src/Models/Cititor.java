package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cititor extends Persoana {
    
    private int nrCartiCitite;
    private Adresa adresa;
    private List<Carte> carti;
    private Abonament abonament;
    
    public Cititor(int id, String nume, String prenume, LocalDate dataNastere, String gen, int nrCartiCitite,
            Adresa adresa, List<Carte> carti, Abonament abonament) {
        super(id, nume, prenume, dataNastere, gen);
        this.nrCartiCitite = nrCartiCitite;
        this.adresa = adresa;
        this.carti = carti;
        this.abonament = abonament;
    }
    public Cititor() {
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
        return "Cititor" + super.toString() + " [nrCartiCitite=" + nrCartiCitite + ", adresa=" + adresa + ", carti=" + carti + ", abonament="
                + abonament + "]";
    }

    public String ImprumutaCarte(Carte c){

        if(c.getImprumut()==true)
            return "cartea este imprumutata de cineva";
        
        if (carti==null)
            carti=new ArrayList<Carte>();
        carti.add(c); 
        return "Ok";
    }

    public String ReturneazaCarte(Carte c){

        nrCartiCitite += 1;
        if (carti==null || carti.isEmpty())
            return "nu are carti imprumutate";
        
        int index=carti.indexOf(c);
        if( index==-1)
            return "Nu a imprumutat aceasta carete";
        
        
        carti.remove(index); 
        return "Ok";
    }
    

    
}
