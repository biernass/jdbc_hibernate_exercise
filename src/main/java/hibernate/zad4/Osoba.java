package hibernate.zad4;

import hibernate.zad4.Mieszkanie;

import javax.persistence.*;

@Entity
public class Osoba {

    @Id
    @GeneratedValue
    private long id;
    private String imie;
    private String nazwisko;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mieszkanie_id")
    private Mieszkanie mieszkanie;

    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, Mieszkanie mieszkanie) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mieszkanie = mieszkanie;
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

    public Mieszkanie getMieszkanie() {
        return mieszkanie;
    }

    public void setMieszkanie(Mieszkanie mieszkanie) {
        this.mieszkanie = mieszkanie;
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", mieszkanie=" + mieszkanie +
                '}';
    }
}
