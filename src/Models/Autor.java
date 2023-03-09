package models;

import java.time.LocalDate;

public class Autor extends Persoana{
    private int nrCartiScrise;

    @Override
    public String toString() {
        return "Autor{" +super.toString()+
                ", nrCartiScrise=" + nrCartiScrise +
                '}';
    }

    public Autor(int id, String nume,String prenume, LocalDate dataNastere,String gen,int nrCartiScrise) {
        super(id,nume,prenume,dataNastere,gen);
        this.nrCartiScrise = nrCartiScrise;
    }

    public Autor()
    {
        super();
        this.nrCartiScrise=0;
    }

    public int getNrCartiScrise() {
        return nrCartiScrise;
    }

    public void setNrCartiScrise(int nrCartiScrise) {
        this.nrCartiScrise = nrCartiScrise;
    }

}
