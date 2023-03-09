package models;

public class Categorie {
    private int idCategorie;
    private String nume;
    private int nrCarti;

    public Categorie(int idCategorie, String nume, int nrCarti)
    {
        this.idCategorie=idCategorie;
        this.nume=nume;
        this.nrCarti=nrCarti;
    }

    public Categorie()
    {
        this.idCategorie=-1;
        this.nume="Necunoscut";
        this.nrCarti=0;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nume='" + nume + '\'' +
                ", nrCarti=" + nrCarti +
                '}';
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrCarti() {
        return nrCarti;
    }

    public void setNrCarti(int nrCarti) {
        this.nrCarti = nrCarti;
    }
}
