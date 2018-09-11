package hibernate.zad4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mieszkanie {

    @Id
    @GeneratedValue
    private long id;
    private String miasto;
    private String ulica;
    private int numerDomu;
    private int numerMieszkania;

    public Mieszkanie() {
    }

    public Mieszkanie(String miasto, String ulica, int numerDomu, int numerMieszkania) {
        this.miasto = miasto;
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.numerMieszkania = numerMieszkania;
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

    @Override
    public String toString() {
        return "Mieszkanie{" +
                "id=" + id +
                ", miasto='" + miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", numerDomu=" + numerDomu +
                ", numerMieszkania=" + numerMieszkania +
                '}';
    }
}
