package Models;
import java.time.LocalDate;

public class Persoana {
    protected int id;
    protected String nume;
    protected String prenume;
    protected LocalDate dataNastere;
    protected String gen;

    //Constructori
    public Persoana(int id, String nume,String prenume, LocalDate dataNastere,String gen){
        this.id=id;
        this.nume=nume;
        this.prenume=prenume;
        this.dataNastere=dataNastere;
        this.gen=gen;
    }

    public Persoana(){
        this.id=1;
        this.nume="Nimic";
        this.prenume="Nimic";
        this.dataNastere=LocalDate.now();
        this.gen="Necunoscut";
    }


    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public LocalDate getDataNastere() {
        return dataNastere;
    }

    public void setDataNastere(LocalDate dataNastere) {
        this.dataNastere = dataNastere;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", dataNastere=" + dataNastere +
                ", gen='" + gen + '\'' +
                '}';
    }
}

