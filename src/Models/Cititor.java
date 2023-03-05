package Models;
public class Cititor extends Persoana {
    protected int idCititor;
    protected int nrCartiCitite;

    public Cititor(int idCititor, int nrCartiCitite)
    {
        super();
        this.idCititor=idCititor;
        this.nrCartiCitite=nrCartiCitite;
    }

    @Override
    public String toString() {
        super.toString();
        return "Cititor{" +
                "idCititor=" + idCititor +
                ", nrCartiCitite=" + nrCartiCitite +
                '}';
    }

    public Cititor()
    {
        this.idCititor=1;
        this.nrCartiCitite=1;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public void setIdCititor(int idCititor) {
        this.idCititor = idCititor;
    }

    public int getNrCartiCitite() {
        return nrCartiCitite;
    }

    public void setNrCartiCitite(int nrCartiCitite) {
        this.nrCartiCitite = nrCartiCitite;
    }
}
