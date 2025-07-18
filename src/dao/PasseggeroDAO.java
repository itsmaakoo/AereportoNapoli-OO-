//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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
        this.connessione = this.connessione;
    }

    public int salvaPasseggero(Passeggero passeggero) throws SQLException {
        String sql = "INSERT INTO passeggeri (nome, cognome, codiceFiscale) VALUES (?,?,?) RETURNING id";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, passeggero.getNome());
            stmt.setString(2, passeggero.getCognome());
            stmt.setString(3, passeggero.getCodiceFiscale());
            stmt.executeUpdate();
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        return 0;
    }

    public Passeggero trovaPerCodiceFiscale(String codiceFiscale) throws SQLException {
        String sql = "SELECT * FROM passeggeri WHERE codiceFiscale = ?";

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
        String sql = "SELECT * FROM passeggeri";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                listaPasseggero.add(new Passeggero(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"), rs.getString("codiceFiscale")));
            }
        }

        return listaPasseggero;
    }

    public void aggiornaPasseggero(Passeggero passeggero) throws SQLException {
        String sql = "UPDATE passeggeri SET nome=?, cognome=?, WHERE codiceFiscale=?";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, passeggero.getNome());
            stmt.setString(2, passeggero.getCognome());
            stmt.setString(3, passeggero.getCodiceFiscale());
            stmt.executeUpdate();
        }

    }

    public void eliminaPasseggero(String codiceFiscale) throws SQLException {
        String sql = "DELETE FROM passeggeri WHERE codiceFiscale = ?";

        try (PreparedStatement stmt = this.connessione.prepareStatement(sql)) {
            stmt.setString(1, codiceFiscale);
            stmt.executeUpdate();
        }

    }
}

