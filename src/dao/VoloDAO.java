package dao;

import model.Volo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Gestisce le operazioni di accesso ai dati che riguardano i voli
 * Contiene metodi per l'inserimento, l'aggiornamento, e il recupero dei voli
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Volo}</li>
 * </ul>
 */
public class VoloDAO {
    private Connection connessione;

    /**
     * Crea un voli con la connessione specificata
     *
     * @param conn la connessione al database
     */
    public VoloDAO(Connection conn) {
        this.connessione = conn;
    }

    /**
     * Salva un nuovo volo nel database.
     *
     * @param v il volo {@link Volo} da inserire
     * @return vero se il volo è stato salvato correttamente altrimenti falso
     */
    public boolean salvaVolo(Volo v) {
        String sql = "INSERT INTO volo (destinazione, data_partenza, data_arrivo, stato, gate, tipo) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement stmt = connessione.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, v.getDestinazione());
            stmt.setTimestamp(2, new java.sql.Timestamp(v.getDataPartenza().getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(v.getDataArrivo().getTime()));
            stmt.setString(4, v.getStato());
            stmt.setString(5, v.getGate());
            stmt.setString(6, v.getTipo());

            System.out.println("Tentativo di inserimento volo:");
            System.out.println("Destinazione: " + v.getDestinazione());
            System.out.println("Partenza: " + v.getDataPartenza());
            System.out.println("Arrivo: " + v.getDataArrivo());
            System.out.println("Stato: " + v.getStato());
            System.out.println("Gate: " + v.getGate());
            System.out.println("Tipo: " + v.getTipo());

            int affectedRows = stmt.executeUpdate(); // se fallisce, qui viene lanciata l'eccezione

            if (affectedRows == 0) {
                System.err.println("Nessuna riga inserita.");
                return false;
            }

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getInt(1));
                    return true;
                } else {
                    System.err.println("Nessuna chiave generata.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore SQL reale: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recupera tutti i voli presenti nel database
     *
     * @return lista dei voli {@link Volo}
     */
    public List<Volo> getTuttiVoli() {
        List<Volo> lista = new ArrayList<>();
        String sql = "SELECT * FROM volo";
        try (Statement stmt = connessione.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                int id = rs.getInt("id");
                String destinazione = rs.getString("destinazione");
                Date data_partenza = rs.getTimestamp("data_partenza");
                Date data_arrivo = rs.getTimestamp("data_arrivo");
                String stato = rs.getString("stato");
                String gate = rs.getString("gate");
                String tipo = rs.getString("tipo");

                Volo v= new Volo(id, destinazione, data_partenza, data_arrivo, stato, gate, tipo);
                lista.add(v);
            }
        }catch (SQLException e){
            System.err.println("errore durante il recupero voli");
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Recupera tutti i voli prenotabili, ovvero non cancellati o con ritardo
     *
     * @return lista di voli {@link Volo} prenotabili
     */
    public List<Volo> getVoliPrenotabili() {
        List<Volo> lista = new ArrayList<>();
        String sql = "SELECT * FROM volo WHERE stato != 'Cancellato' AND data_partenza > CURRENT_TIMESTAMP";

        try (Statement stmt = connessione.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String destinazione = rs.getString("destinazione");
                Date data_partenza = rs.getTimestamp("data_partenza");
                Date data_arrivo = rs.getTimestamp("data_arrivo");
                String stato = rs.getString("stato");
                String gate = rs.getString("gate");
                String tipo = rs.getString("tipo");

                Volo v = new Volo(id, destinazione, data_partenza, data_arrivo, stato, gate, tipo);
                lista.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante il recupero dei voli prenotabili");
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Aggiorna lo stato di un volo tramite il suo id.
     *
     * @param id         l'id del volo
     * @param nuovoStato il nuovo stato aggiornato
     */
    public void aggiornaStato(int id, String nuovoStato){
        String sql = "UPDATE volo SET stato = ? WHERE id = ?";
        try(PreparedStatement statement = connessione.prepareStatement(sql);){
            statement.setString(1, nuovoStato);
            statement.setInt(2, id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * Aggiorna il gate tramite l'id del volo.
     *
     * @param id   l'id del volo
     * @param gate il gate da assegnare
     */
    public void aggiornaGate(int id, String gate){
        String sql = "UPDATE volo SET gate = ? WHERE id = ?";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setString(1, gate);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("errore durante l'aggiornamento del gate");
            e.printStackTrace();
        }
    }
}
