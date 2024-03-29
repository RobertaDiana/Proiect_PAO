package models;
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
        this.denumire="Necunoscut";
    }
    @Override
    public String toString() {
        return  "Editura" + '\n' +
                "idEditura=" + idEditura + '\n' +
                "denumire=" + denumire + '\n';
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
