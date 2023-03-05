package Models;

public class Autor extends Persoana{
    private int idAutor;
    private int nrCartiScrise;

    @Override
    public String toString() {
        super.toString();
        return "Autor{" +
                "idAutor=" + idAutor +
                ", nrCartiScrise=" + nrCartiScrise +
                '}';
    }

    public Autor(int nrCartiScrise) {
        super();
        this.idAutor=idAutor;
        this.nrCartiScrise = nrCartiScrise;
    }

    public Autor()
    {
        this.idAutor=1;
        this.nrCartiScrise=1;
    }

    public int getNrCartiScrise() {
        return nrCartiScrise;
    }

    public void setNrCartiScrise(int nrCartiScrise) {
        this.nrCartiScrise = nrCartiScrise;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}
