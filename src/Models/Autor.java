package models;

import java.time.LocalDate;

public class Autor extends Persoana{
    private int nrCartiScrise;

    @Override
    public String toString() {
        return  "Autor" + '\n' +
                super.toString()+
                "nrCartiScrise=" + nrCartiScrise + '\n';
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

    @Override
    public boolean equals(Object o){
        if (this==o)
            return true;

        if (!(o instanceof Autor)) {
            return false;
        }
        Autor a = (Autor) o;

        if(this.nume.equals(a.nume) && this.prenume.equals(a.prenume))
            return  true;

        return false;
    }
}
