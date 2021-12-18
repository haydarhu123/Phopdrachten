package Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "OVChipkaart")
@Table(name = "ov_chipkaart")

public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private int kaartnummer;
    @Column(name = "geldig_tot")
    private Date geldig_tot;
    @Column(name = "klasse")
    private int klasse;
    @Column(name = "saldo")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger_id;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = @JoinColumn(name = "kaart_nummer"), inverseJoinColumns = @JoinColumn(name = "product_nummer"))
            private List<Product> product;

            public OVChipkaart(int kaartnummer, Date geldig_tot, int klasse, Double saldo, Reiziger reiziger_id){
            this.kaartnummer=kaartnummer;
            this.geldig_tot=geldig_tot;
            this.klasse=klasse;
            this.saldo=saldo;
            this.reiziger_id=reiziger_id;
            }

            public OVChipkaart(){

            }

            public int getKaartnummer(){
            return kaartnummer;
            }

            public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setReiziger_id(Reiziger reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getReiziger_id() {
        return reiziger_id;
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaartnummer=" + kaartnummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger_id=" + reiziger_id +
                '}';
    }
}
