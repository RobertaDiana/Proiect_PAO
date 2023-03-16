package models;

public class Categorie {
    private int idCategorie;
    private String nume;

    public Categorie(int idCategorie, String nume)
    {
        this.idCategorie=idCategorie;
        this.nume=nume;

    }

    public Categorie()
    {
        this.idCategorie=-1;
        this.nume="Necunoscut";

    }

    @Override
    public String toString() {
        return "Categorie{" +
                "idCategorie=" + idCategorie +
                ", nume=" + nume +
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

}
