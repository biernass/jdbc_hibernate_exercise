package hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
public class Osoba3 {

    @Id
    @GeneratedValue
    private long id;
    private String imie;
    private String nazwisko;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "osoba3")
    //@JoinColumn(name = "osobaOne_id")
    private List<Mieszkanie3> mieszkanieList;


    public Osoba3() {
    }

    public Osoba3(String imie, String nazwisko, List<Mieszkanie3> mieszkanieList) {
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

    public List<Mieszkanie3> getMieszkanieList() {
        return mieszkanieList;
    }

    public void setMieszkanieList(List<Mieszkanie3> mieszkanieList) {
        this.mieszkanieList = mieszkanieList;
    }
}
