//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Volo {
    private int id;
    private String destinazione;
    private Date data_partenza;
    private Date data_arrivo;
    private String stato;
    private String gate;
    private List<Bagaglio> bagagli = new ArrayList();
    private List<Passeggero> passggeri = new ArrayList();
    public static List<Volo> archivio = new ArrayList();

    public Volo(String destinazione, Date dataPartenza, Date dataArrivo, String stato, String gate) {
        this.destinazione = destinazione;
        this.data_partenza = dataPartenza;
        this.data_arrivo = dataArrivo;
        this.stato = stato;
        this.gate = gate;
    }

    public void aggiungiBagaglio(Bagaglio b) {
        this.bagagli.add(b);
    }

    public List<Bagaglio> getBagagli() {
        return this.bagagli;
    }

    public void aggiungiPasseggero(Passeggero p) {
        this.passggeri.add(p);
    }

    public List<Passeggero> getPassggeri() {
        return this.passggeri;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestinazione() {
        return this.destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public Date getDataPartenza() {
        return this.data_partenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.data_partenza = dataPartenza;
    }

    public Date getDataArrivo() {
        return this.data_arrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        this.data_arrivo = dataArrivo;
    }

    public String getStato() {
        return this.stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getGate() {
        return this.gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String toString() {
        String var10000 = this.destinazione;
        return var10000 + " " + (new SimpleDateFormat("dd/MM/yyyy")).format(this.data_partenza) + "Stato: " + this.stato;
    }
}

