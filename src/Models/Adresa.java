package models;

public class Adresa {
    private int idAdresa;
    private String adresa;


    
    public Adresa() {
        this.idAdresa = -1;
        this.adresa = "necunoscut";

    }

    public Adresa(int idAdresa, String adresa) {
        this.idAdresa = idAdresa;
        this.adresa = adresa;

    }
    public int getIdAdresa() {
        return idAdresa;
    }
    public void setIdAdresa(int idAdresa) {
        this.idAdresa = idAdresa;
    }
    public String getAdresa() {
        return adresa;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }


    @Override
    public String toString() {
        return  "Adresa" + '\n' +
                "idAdresa: " + idAdresa + '\n' +
                "adresa: " + adresa + '\n' ;
    }
    
}
