package Model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Product")
@Table(name= "product")

public class Product {

    @Id
    @Column(name="product_nummer")
    private int productnummer;
    @Column(name="naam")
    private String naam;
    @Column(name="beschrijving")
    private String beschrijving;
    @Column(name="prijs")
    private Double prijs;
    @ManyToMany
    private List<OVChipkaart> ovchipkaart = new ArrayList<>();

    public Product(int productnummer, String naam, String beschrijving, Double prijs) {
        this.productnummer = productnummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public Product() {

    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public int getProductnummer() {
        return productnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvchipkaart() {
        return ovchipkaart;
    }

    public void setOvchipkaart(List<OVChipkaart> ovchipkaart) {
        this.ovchipkaart = ovchipkaart;
    }
}

