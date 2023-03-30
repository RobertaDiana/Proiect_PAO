package models;
import java.time.LocalDate;

public class Abonament  {
    private int idAbonament;
    private String tipAbonament;
    private LocalDate dataCreare;
    private String status;

    public Abonament(int idAbonament, String tipAbonament, LocalDate dataCreare, String status)
    {
        this.idAbonament=idAbonament;
        this.tipAbonament=tipAbonament;
        this.dataCreare=dataCreare;
        this.status=status;
    }
    public Abonament()
    {
        this.idAbonament=1;
        this.tipAbonament="lunar";
        this.dataCreare=LocalDate.now();
        this.status="Valabil";
    }


    public int getIdAbonament() {
        return idAbonament;
    }

    public void setIdAbonament(int idAbonament) {
        this.idAbonament = idAbonament;
    }

    public String getTipAbonament() {
        return tipAbonament;
    }

    public void setTipAbonament(String tipAbonament) {
        this.tipAbonament = tipAbonament;
    }

    public LocalDate getDataCreare() {
        return dataCreare;
    }

    public void setDataCreare(LocalDate dataCreare) {
        this.dataCreare = dataCreare;
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;

        if (!(o instanceof Abonament)) {
            return false;
        }
        return this.idAbonament==((Abonament) o).idAbonament;
    }

    @Override
    public String toString()
    {

        return "Abonament " + '\n' +
                "idAbonament=" + idAbonament + '\n' +
                "tipAbonament=" + tipAbonament + '\n' +
                "dataCreare=" + dataCreare + '\n' +
                "status=" + status + '\n';

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
