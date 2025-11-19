package model;

/**
 * Rappresenta un gate, un imbarco in un aeroporto.
 * Ogni gate è identificato da una stringa.
 */
public class Gate {
    private String gate;

    /**
     * Costruisce un nuovo oggetto Gate con il nome specificato.
     *
     * @param gate il nome o codice del gate (es. "A12")
     */
    Gate(String gate) {
        this.gate = gate;
    }

    /**
     * Restituisce il nome del gate.
     *
     * @return il codice del gate
     */
    public String getGate() {
        return gate;
    }

    /**
     * Imposta un nuovo nome per il gate.
     *
     * @param gate il nuovo codice del gate
     */
    public void setGate(String gate) {
        this.gate = gate;
    }

    /**
     * Restituisce una rappresentazione testuale del gate.
     *
     * @return una stringa descrittiva del gate
     */
    @Override
    public String toString() {
        return "\nil gate è: " + gate;
    }
}

