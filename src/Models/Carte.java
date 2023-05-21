
package models;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Carte implements  Comparable{
    private int idCarte;
    private String title;
    private Set<Autor> autor;
    private List<Categorie> categorie;
    private int dataPublicarii;
    private Editura editura;
    private Boolean imprumut;

    

    public Carte(int idCarte, String title, Set<Autor> autor, List<Categorie> categorie, int dataPublicarii,
                 Editura editura, Boolean imprumut) {
        this.idCarte = idCarte;
        this.title = title;
        this.autor = autor;
        this.categorie = categorie;
        this.dataPublicarii = dataPublicarii;
        this.editura = editura;
        this.imprumut = imprumut;
    }

    
    public Carte()
    {
        this.idCarte = -1;
        this.title = "Necunoscut";
        this.autor = null;
        this.dataPublicarii = LocalDate.now().getYear();
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



    public List<Categorie> getCategorie() {
        return categorie;
    }


    public void setCategorie(List<Categorie> categorie) {
        this.categorie = categorie;
    }

    public Set<Autor> setAutor(){
        return autor;
    }
    public void setAutor( Set<Autor> autor){
        this.autor=autor;
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
    public boolean equals(Object o){
        if (this==o)
            return true;

        if (!(o instanceof Carte)) {
            return false;
        }
        Carte c = (Carte) o;

        if(this.idCarte==((Carte) o).idCarte)
            return  true;

        return false;
    }

    @Override
    public String toString() {
        return  "Carte" + '\n' +
                "idCarte=" + idCarte + '\n' +
                "title=" + title + '\n' +
                "autor=" + autor + '\n' +
                "categorie=" + categorie + '\n' +
                "dataPublicarii=" + dataPublicarii + '\n' +
                "editura=" + editura + '\n' +
                "imprumut=" + imprumut + '\n';
    }


    @Override
    public int compareTo(Object o) {
        if (this==o)
            return 0;

        if (!(o instanceof Carte)) {
            return -1;
        }
        Carte c = (Carte) o;
        return this.getTitle().compareTo(c.getTitle());
    }
}



