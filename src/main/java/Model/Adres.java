package Model;

import javax.persistence.*;

@Entity(name = "Adres")
@Table(name= "adres")

public class Adres {

    @Id
    @Column(name= "adres_id")
    private int id;
    @Column(name= "postcode")
    private String postcode;
    @Column(name= "huisnummer")
    private String huisnummer;
    @Column(name= "straat")
    private String straat;
    @Column(name= "woonplaats")
    private String woonplaats;
    @OneToOne
    @JoinColumn(name= "reiziger_id")
    private Reiziger reiziger_id;

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats, Reiziger reiziger_id) {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reiziger_id = reiziger_id;
    }

    public Adres() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Reiziger getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(Reiziger reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    @Override
    public String toString() {
        return "Adres{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                '}';
    }
}






