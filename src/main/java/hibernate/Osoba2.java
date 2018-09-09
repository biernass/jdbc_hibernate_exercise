package hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
public class Osoba2 {

    @Id
    @GeneratedValue
    private long id;
    private String imie;
    private String nazwisko;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Mieszkanie2> mieszkanieList;


    public Osoba2() {
    }

    public Osoba2(String imie, String nazwisko, List<Mieszkanie2> mieszkanieList) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mieszkanieList = mieszkanieList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public List<Mieszkanie2> getMieszkanieList() {
        return mieszkanieList;
    }

    public void setMieszkanieList(List<Mieszkanie2> mieszkanieList) {
        this.mieszkanieList = mieszkanieList;
    }
}
