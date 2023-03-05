
package Models;

public class Carte {
    private int idCarte;
    private String title;

    private Autor autor;
    private int dataPublicarii;
    private int cantitate;

    private Editura editura;

    private Imprumut imprumut;

    public Carte(int idCarte, String title, int dataPublicarii, int cantitate)
    {
        this.idCarte=idCarte;
        this.title= title;
        this.dataPublicarii=dataPublicarii;
        this.cantitate=cantitate;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "idCarte=" + idCarte +
                ", title='" + title + '\'' +
                ", autor=" + autor +
                ", dataPublicarii=" + dataPublicarii +
                ", cantitate=" + cantitate +
                ", editura=" + editura +
                '}';
    }

    public Carte()
    {
        this.idCarte=1;
        this.title="Carte";
        this.dataPublicarii=2023-03-02;
        this.cantitate=1;
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
}
