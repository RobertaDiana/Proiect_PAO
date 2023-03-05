package Models;
public class Editura {
    private int idEditura;
    private String denumire;

    public Editura (int idEditura, String denumire)
    {
        this.idEditura= idEditura;
        this.denumire= denumire;

    }

    public Editura ()
    {
        this.idEditura=1;
        this.denumire="Aramis";
    }
    @Override
    public String toString() {
        return "Editura{" +
                "idEditura=" + idEditura +
                ", denumire='" + denumire + '\'' +
                '}';
    }

    public int getIdEditura() {
        return idEditura;
    }

    public void setIdEditura(int idEditura) {
        this.idEditura = idEditura;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
