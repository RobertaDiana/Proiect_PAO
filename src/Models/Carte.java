
package models;
import java.time.LocalDate;
import java.util.List;

public class Carte {
    private int idCarte;
    private String title;
    private List<Autor> autor;
    private List<Categorie> categorie;
    private int dataPublicarii;
    private int cantitate;
    private Editura editura;
    private Boolean imprumut;

    

    public Carte(int idCarte, String title, List<Autor> autor, List<Categorie> categorie, int dataPublicarii,
            int cantitate, Editura editura, Boolean imprumut) {
        this.idCarte = idCarte;
        this.title = title;
        this.autor = autor;
        this.categorie = categorie;
        this.dataPublicarii = dataPublicarii;
        this.cantitate = cantitate;
        this.editura = editura;
        this.imprumut = imprumut;
    }

    
    public Carte()
    {
        this.idCarte = -1;
        this.title = "Necunoscut";
        this.autor = null;
        this.dataPublicarii = LocalDate.now().getYear();
        this.cantitate = 0;
        this.editura = null;
        this.imprumut = false;
        this.categorie = null;
    }

    
    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(int dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public List<Autor> getAutor() {
        return autor;
    }



    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }



    public List<Categorie> getCategorie() {
        return categorie;
    }



    public void setCategorie(List<Categorie> categorie) {
        this.categorie = categorie;
    }



    public Editura getEditura() {
        return editura;
    }



    public void setEditura(Editura editura) {
        this.editura = editura;
    }



    public Boolean getImprumut() {
        return imprumut;
    }



    public void setImprumut(Boolean imprumut) {
        this.imprumut = imprumut;
    }



    @Override
    public String toString() {
        return "Carte [idCarte=" + idCarte + ", title=" + title + ", autor=" + autor + ", categorie=" + categorie
                + ", dataPublicarii=" + dataPublicarii + ", cantitate=" + cantitate + ", editura=" + editura
                + ", imprumut=" + imprumut + "]";
    }
}
