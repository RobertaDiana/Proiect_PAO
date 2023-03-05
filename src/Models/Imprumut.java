package Models;

public class Imprumut{
    private int idCititor;


    public Imprumut(int idCititor)
    {
        this.idCititor=idCititor;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "idCititor=" + idCititor +
                '}';
    }

    public Imprumut()
    {
        this.idCititor=1;
    }
    public int getIdCititor() {
        return idCititor;
    }

    public void setIdCititor(int idCititor) {
        this.idCititor = idCititor;
    }
}
