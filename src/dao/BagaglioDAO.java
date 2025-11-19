package dao;

import model.Bagaglio;
import model.StatoBagaglio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce le operazioni di accesso che riguardano i bagagli registrati nel database.
 * Contiene metodi per l'inserimento, per la ricerca, per l'aggiornamento (la segnalazione) dei bagagli
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Bagaglio}</li>
 * <li>{@link model.StatoBagaglio}</li>
 * </ul>
 */
public class BagaglioDAO {
    private Connection connessione;

    /**
     * Costruisce un nuovo bagaglio con la connessione specificata
     *
     * @param connessione connessione del database
     */
    public BagaglioDAO(Connection connessione){
        this.connessione = connessione;
    }

    /**
     * Inserire un nuovo bagaglio in riferimento alla prenotazione
     *
     * @param b              sarebbe il bagaglio {@link Bagaglio} da inserire
     * @param prenotazioneId l'id della prenotazione a cui verrà associato il bagaglio
     * @throws SQLException se si verifica un errore durante l'inserimento
     */
    public void add(Bagaglio b, int prenotazioneId) throws SQLException {
        String sql = "INSERT INTO bagaglio (codice, prenotazione_id, stato, descrizione) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, b.getCodice());
            stmt.setInt(2, b.getPrenotazioneId());
            stmt.setString(3, b.getStato().name());
            stmt.setString(4, b.getDescrizione());
            stmt.executeUpdate();
        }
    }

    /**
     * Trova i bagagli associati a una determinata prenotazione
     *
     * @param prenotazioneId l'id della prenotazione
     * @return la lista dei bagagli {@link Bagaglio} associati alla prenotazione
     * @throws SQLException se si verifica un errore durante la query
     */
    public List<Bagaglio> trovaBagagliPerPrenotazione(int prenotazioneId) throws SQLException {
        List<Bagaglio> bagagli = new ArrayList<>();
        String sql = "SELECT * FROM bagaglio WHERE prenotazione_id = ?";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, prenotazioneId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bagaglio b = new Bagaglio(
                        prenotazioneId,
                        rs.getInt("codice"),
                        rs.getString("descrizione")
                );
                b.setStato(StatoBagaglio.valueOf(rs.getString("stato")));
                bagagli.add(b);
            }
        }
        return bagagli;
    }

    /**
     * Trova i bagagli con stato "smarrito"
     *
     * @return la lista dei bagagli {@link Bagaglio} smarriti
     * @throws SQLException se si verifica un errore durante la query
     */
    public List<Bagaglio> trovaBagagliSmarriti() throws SQLException {
        List<Bagaglio> bagagli = new ArrayList<>();
        String sql = "SELECT * FROM bagaglio WHERE stato = 'smarrito'";
        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Bagaglio b = new Bagaglio(
                        rs.getInt("prenotazione_id"),
                        rs.getInt("codice"),
                        rs.getString("descrizione")
                );
                b.setStato(StatoBagaglio.valueOf(rs.getString("stato").toLowerCase()));
                bagagli.add(b);
            }
        }
        return bagagli;
    }

    /**
     * Aggiorna lo stato del bagaglio in base al suo codice.
     *
     * @param codiceBagaglio codice del bagaglio
     * @param nuovoStato     il nuovo stato aggiornato
     * @throws SQLException se si verifica un errore durante la query
     */
    public void aggiornaStatoBagaglio(String codiceBagaglio, StatoBagaglio nuovoStato) throws SQLException {
        String query = "UPDATE bagaglio SET stato = ? WHERE codice = ?";
        try (PreparedStatement stmt = connessione.prepareStatement(query)) {
            stmt.setString(1, nuovoStato.name());
            stmt.setString(2, codiceBagaglio); // usa setString perché codice è VARCHAR
            stmt.executeUpdate();
        }
    }
}

