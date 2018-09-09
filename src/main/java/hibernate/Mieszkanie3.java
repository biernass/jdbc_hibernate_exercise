package hibernate;

import javax.persistence.*;

@Entity
public class Mieszkanie3 {

    @Id
    @GeneratedValue
    private long id;
    private String miasto;
    private String ulica;
    private int numerDomu;
    private int numerMieszkania;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "abc")
    private Osoba3 osoba3;


    public Mieszkanie3() {
    }

    public Mieszkanie3(String miasto, String ulica, int numerDomu, int numerMieszkania, Osoba3 osoba3) {
        this.miasto = miasto;
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.numerMieszkania = numerMieszkania;
        this.osoba3 = osoba3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getNumerDomu() {
        return numerDomu;
    }

    public void setNumerDomu(int numerDomu) {
        this.numerDomu = numerDomu;
    }

    public int getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(int numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }


}
