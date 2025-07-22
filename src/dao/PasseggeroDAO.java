package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Passeggero;

public class PasseggeroDAO {
    private Connection connessione;

    public PasseggeroDAO(Connection conn) {

        this.connessione = conn;
    }

    public int salvaPasseggero(Passeggero passeggero) throws SQLException {
        String sql = "INSERT INTO passeggero (nome, cognome, codiceFiscale) VALUES (?,?,?) RETURNING id";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, passeggero.getNome());
            stmt.setString(2, passeggero.getCognome());
            stmt.setString(3, passeggero.getCodiceFiscale());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        return 0;
    }

    public Passeggero trovaPerCodiceFiscale(String codiceFiscale) throws SQLException {
        String sql = "SELECT * FROM passeggero WHERE codiceFiscale = ?";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Passeggero(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale"));
            }
        }

        return null;
    }

    public List<Passeggero> trovaListaPasseggero() throws SQLException {
        List<Passeggero> listaPasseggero = new ArrayList();
        String sql = "SELECT * FROM passeggero";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                listaPasseggero.add(new Passeggero(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale")));
            }
        }

        return listaPasseggero;
    }

    public void aggiornaPasseggero(Passeggero passeggero) throws SQLException {
        String sql = "UPDATE passeggero SET nome=?, cognome=?, WHERE codiceFiscale=?";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, passeggero.getNome());
            stmt.setString(2, passeggero.getCognome());
            stmt.setString(3, passeggero.getCodiceFiscale());
            stmt.executeUpdate();
        }

    }

    public void eliminaPasseggero(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM passeggero WHERE codiceFiscale = ?";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            stmt.executeUpdate();
        }
    }
    }


