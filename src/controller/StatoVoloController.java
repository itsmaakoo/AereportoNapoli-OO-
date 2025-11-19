package controller;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;
import java.sql.Connection;
import java.util.Collections;
import java.util.List;

/**
 * Gestisce le informazioni che riguardano allo stato dei voli
 * consente di ottenere l'elenco completo dei voli o filtrare per il tipo
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
public class StatoVoloController {
    private VoloDAO voloDAO;

    /**
     * Crea un nuovo controller per la gestione dello stato dei voli.
     * Stabilisce la connessione al database e la classe {@link VoloDAO}
     */
    public StatoVoloController() {
        try {
            Connection conn = ConnessioneDB.getConnection();
            if(conn != null){
                voloDAO = new VoloDAO(conn);
            } else {
                System.err.println("Connessione al database fallita");
            }
        } catch (Exception e) {
            System.err.println("Errore durante l'inizializzazione del controller");
            e.printStackTrace();
        }
    }

    /**
     * Recupera l'elenco completo dei voli presenti nel database.
     *
     * @return lista di voli {@link Volo} oppure una lista vuota in caso di errore
     */
    public List<Volo> recuperaTuttiIVoli() {
        if(voloDAO != null){
            return voloDAO.getTuttiVoli();

        }
        return Collections.emptyList();
    }

    /**
     * Recupera l'elenco dei voli filtrati per tipo.
     * @param tipo tipo di volo che può essere partenza o arrivo
     * @return lista di voli {@link Volo} corrispondenti al tipo specificato.
     */

    public List<Volo> recuperaVoliPerTipo(String tipo) {
        if(voloDAO != null){
            return voloDAO.getTuttiVoli();
        }
        return Collections.emptyList();

    }
}
