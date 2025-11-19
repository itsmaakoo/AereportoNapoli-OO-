package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import java.util.Date;

/**
 * Gestisce l'inserimento dei voli in arrivo.
 * Utilizza {@link VoloDAO} per salvare le informazioni relative al volo
 *
 * <p><b>Dipendenze</b></p>
 * <ul>
 * <li>{@link dao.VoloDAO}</li>
 * <li>{@link db.ConnessioneDB}</li>
 * </ul>
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * </ul>
 */

public class VoloArrivoController {
    private final VoloDAO dao;

    /**
     * Crea un controller per la gestione dei voli in arrivo
     * Consente la connessione al database e la classe {@link VoloDAO}
     */

    public VoloArrivoController() {
        this.dao = new VoloDAO(ConnessioneDB.getConnection());
    }

    /**
     * Salva un nuovo volo "in arrivo" nel database co i seguenti parametri
     * @param destinazione da dove arriva il volo
     * @param dataArrivo la data e ora d'arrivo del volo
     * @param stato stato del volo
     * @param gate gate assegnato al volo
     * @return vero se il volo è stato salvato, falso altrimenti
     */

    public boolean salvaVoloArrivo(String destinazione, Date dataArrivo, String stato, String gate) {
        Date dataPartenzaFittizia = new Date();
        Volo volo = new Volo(0, destinazione, dataPartenzaFittizia, dataArrivo, stato, gate, "arrivo");
        return dao.salvaVolo(volo);
    }
}
