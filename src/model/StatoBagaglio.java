package model;

/**
 * Enum, rappresenta lo stato di un bagaglio durante il processo di viaggio.
 * Ogni valore indica una fase specifica del ciclo di gestione del bagaglio.
 */
public enum StatoBagaglio {
    /**
     * Il bagaglio è stato registrato ma non ancora imbarcato.
     */
    registrato,

    /**
     * Il bagaglio è stato imbarcato sull'aereo.
     */
    imbarcato,

    /**
     * Il bagaglio è disponibile per il ritiro da parte del passeggero.
     */
    ritirabile,

    /**
     * Il bagaglio è stato smarrito e non è attualmente rintracciabile.
     */
    smarrito;
}
