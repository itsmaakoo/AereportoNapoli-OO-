package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Passeggero;

/**
 * Gestisce le operazioni di accesso che riguardano i passeggeri.
 * Contiene metodi per l'inserimento, la ricerca del passeggero
 *
 * <p><b>Modelli</b></p>
 * <ul>
 * <li>{@link model.Passeggero}</li>
 * </ul>
 */
public class PasseggeroDAO {
    private Connection connessione;

    /**
     * Crea un nuovo passeggero con la connessione specificata
     *
     * @param conn la connessione al database
     */
    public PasseggeroDAO(Connection conn) {

        this.connessione = conn;
    }

    /**
     * Salva un nuovo passeggero nel database.
     *
     * @param passeggero passeggero da inserire
     * @return l'id generato del passeggero
     * @throws SQLException se si verifica un errore durante l'inserimento
     */
    public int salvaPasseggero(Passeggero passeggero) throws SQLException {
        String sql = "INSERT INTO passeggero (nome, cognome, codiceFiscale, id_volo) VALUES (?,?,?,?) RETURNING id";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, passeggero.getNome());
            stmt.setString(2, passeggero.getCognome());
            stmt.setString(3, passeggero.getCodiceFiscale());
            stmt.setInt(4, passeggero.getIdVolo());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        return 0;
    }


    /**
     * Recupera l'elenco completo dei passeggeri presenti nel database
     *
     * @return la lista di passeggeri {@link Passeggero}
     * @throws SQLException se si verifica un errore durante la query
     */
    public List<Passeggero> trovaListaPasseggero() throws SQLException {
        List<Passeggero> listaPasseggero = new ArrayList();
        String sql = "SELECT * FROM passeggero";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Passeggero p = new Passeggero();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCognome(rs.getString("cognome"));
                p.setCodiceFiscale(rs.getString("codiceFiscale"));
                p.setIdVolo(rs.getInt("id_volo")); // <-- aggiunto
                listaPasseggero.add(p);            }
        }

        return listaPasseggero;
    }
}



