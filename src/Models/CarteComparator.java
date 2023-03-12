package models;

public class CarteComparator implements java.util.Comparator<Carte> {
    @Override
    public int compare(Carte a, Carte b) {
        return a.getTitle().compareTo(b.getTitle());
    }
}