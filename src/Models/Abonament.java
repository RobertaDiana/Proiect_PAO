package models;
import java.time.LocalDate;

public class Abonament extends Cititor {
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
    public String toString()
    {
        super.toString();
        return "Abonament{" +
                "idAbonament=" + idAbonament +
                ", tipAbonament='" + tipAbonament + '\'' +
                ", dataCreare=" + dataCreare +
                ", status='" + status + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
