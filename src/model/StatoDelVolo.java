package model;

/**
 * Enum, rappresenta lo stato operativo di un volo.
 * Ogni valore indica una condizione specifica del volo nel suo ciclo di pianificazione.
 */
public enum StatoDelVolo {
    /**
     * Il volo è in orario e procede secondo la pianificazione prevista.
     */
    IN_ORARIO,

    /**
     * Il volo è in ritardo rispetto all'orario previsto.
     */
    IN_RITARDO,

    /**
     * Il volo è stato cancellato e non verrà effettuato.
     */
    CANCELLATO,

    /**
     * Il volo è stato programmato ma non è ancora partito.
     */
    PROGRAMMATO;
}
