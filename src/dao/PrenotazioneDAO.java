package dao;

import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce le operazioni di accesso ai dati relatici alle prenotazioni
 * Contiene metodi per l'inserimento, ricerca per volo e recupero delle prenotazioni
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Prenotazione}</li>
 * </ul>
 */
public class PrenotazioneDAO {
    private Connection connessione;

    /**
     * Crea una prenotazione con la connessione specificata
     *
     * @param connessione la connessione al database
     */
    public PrenotazioneDAO(Connection connessione) {
        this.connessione = connessione;
    }

    /**
     * Restituisce tutte le prenotazioni associate a un determinato volo.
     *
     * @param voloId l'id del volo
     * @return lista di prenotazioni {@link Prenotazione} aoociate al volo
     * @throws SQLException se si verifica un errore durante la query
     */
    public List<Prenotazione> getPrenotazioniPerVolo(int voloId) throws SQLException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        String sql = "SELECT * FROM prenotazione WHERE volo_id = ?";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, voloId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("num_biglietto"),
                        rs.getInt("passeggero_id"),
                        rs.getString("codicefiscale"),
                        rs.getInt("volo_id"),
                        rs.getInt("id"), // prenotazioneId
                        rs.getString("stato"),
                        rs.getString("posto_assegnato")
                );
                prenotazioni.add(p);
            }
        }
        return prenotazioni;
    }

    /**
     * Salva una nuova prenotazione nel database.
     *
     * @param p la prenotazione {@link Prenotazione} da salvare
     * @throws SQLException se si verifica un errore durante l'inserimento
     */
    public void salvaPrenotazione(Prenotazione p) throws SQLException {
        String sql = "INSERT INTO prenotazione (id, num_biglietto, passeggero_id, codicefiscale, volo_id, stato, posto_assegnato) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, p.getPrenotazioneId());
            stmt.setInt(2, p.getNum_biglietto());
            stmt.setInt(3, p.getPasseggeroId());
            stmt.setString(4, p.getCodiceFiscale());
            stmt.setInt(5, p.getVoloId());
            stmt.setString(6, p.getStato_prenotazione());
            stmt.setString(7, p.getPosto_assegnato());
            stmt.executeUpdate();
        }
    }

    /**
     * Restituisce tutte le prenotazioni presenti nel database.
     *
     * @return lista di tutte le prenotazioni {@link Prenotazione}
     * @throws SQLException se si verifica un errore durante la query
     */
    public List<Prenotazione> getTuttePrenotazioni() throws SQLException {
        List<Prenotazione> prenotazioni = new ArrayList<>();
        String sql = "SELECT * FROM prenotazione";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("num_biglietto"),
                        rs.getInt("passeggero_id"),
                        rs.getString("codicefiscale"),
                        rs.getInt("volo_id"),
                        rs.getInt("id"),
                        rs.getString("stato"),
                        rs.getString("posto_assegnato")
                );
                prenotazioni.add(p);
            }
        }
        return prenotazioni;
    }
}
