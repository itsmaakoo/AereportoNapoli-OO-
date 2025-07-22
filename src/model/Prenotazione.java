//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

public class Prenotazione {
    private int id; //ID PRENOTAZIONE
    private int num_biglietto;
    private int passeggeroId;
    private String documento;
    private int voloId;
    private String stato_prenotazione;

    public Prenotazione(int id, int num_biglietto, int passeggeroId, String documento, int voloId, String stato_prenotazione) {
        this.id = id;
        this.num_biglietto = num_biglietto;
        this.passeggeroId = passeggeroId;
        this.documento = documento;
        this.voloId = voloId;

        this.stato_prenotazione = stato_prenotazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_biglietto() {
        return this.num_biglietto;
    }

    public void setNum_biglietto(int num_biglietto) {
        this.num_biglietto = num_biglietto;
    }

    public int getPasseggeroId() {
        return this.passeggeroId;
    }

    public void setPasseggeroId(int passeggeroId) {
        this.passeggeroId = passeggeroId;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getVoloId() {
        return this.voloId;
    }

    public void setVoloId(int voloId) {
        this.voloId = voloId;
    }


    public String getStato_prenotazione() {
        return this.stato_prenotazione;
    }

    public void setStato_prenotazione(String stato_prenotazione) {
        this.stato_prenotazione = stato_prenotazione;
    }

    public String toString() {
        String stringa = "\nbiglietto: " + this.num_biglietto + " \nposto_assegnato:  \nstato_prenotazione: " + this.stato_prenotazione;
        return stringa;
    }

    public void checkin() {
    }
}

