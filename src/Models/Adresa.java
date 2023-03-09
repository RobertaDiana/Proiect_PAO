package models;

public class Adresa {
    private int idAdresa;
    private String adresa;
    private String numar;
    private String alteDetalii;

    
    public Adresa() {
        this.idAdresa = -1;
        this.adresa = "necunoscut";
        this.numar = "necunoscut";
        this.alteDetalii = "Nimic";
    }

    public Adresa(int idAdresa, String adresa, String numar, String alteDetalii) {
        this.idAdresa = idAdresa;
        this.adresa = adresa;
        this.numar = numar;
        this.alteDetalii = alteDetalii;
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
    public String getNumar() {
        return numar;
    }
    public void setNumar(String numar) {
        this.numar = numar;
    }
    public String getAlteDetalii() {
        return alteDetalii;
    }
    public void setAlteDetalii(String alteDetalii) {
        this.alteDetalii = alteDetalii;
    }

    @Override
    public String toString() {
        return  "Adresa"+ 
                "\n   idAdresa: " + idAdresa + 
                "\n   adresa: " + adresa + 
                "\n   numar: " + numar + 
                "\n   alteDetalii: "+ alteDetalii ;
    }
    
}
