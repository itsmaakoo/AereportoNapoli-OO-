package dao;

import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {
    private Connection conn;

    public PrenotazioneDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvaPrenotazione(Prenotazione p) {
        String sql = "INSERT INTO prenotazione(num_biglietto, passeggero_id, codicefiscale, volo_id, stato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getNum_biglietto());
            stmt.setInt(2, p.getPasseggeroId());
            stmt.setString(3, p.getDocumento()); // codice fiscale
            stmt.setInt(4, p.getVoloId());
            stmt.setString(5, p.getStato_prenotazione());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Prenotazione> trovaPrenotazioni() {
        List<Prenotazione> lista = new ArrayList<>();
        String sql = "SELECT * FROM prenotazione";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),                      // id prenotazione
                        rs.getInt("num_biglietto"),           // numero biglietto
                        rs.getString("codicefiscale"),
                        rs.getInt("passeggero_id"),           // ID passeggero// documento
                        rs.getInt("volo_id"),                 // ID volo
                        rs.getString("stato")                 // stato
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Prenotazione> trovaPrenotazioniPerVolo(int voloId) {
        List<Prenotazione> lista = new ArrayList<>();
        String sql = "SELECT * FROM prenotazione WHERE volo_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, voloId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("id"),
                        rs.getInt("num_biglietto"),
                        rs.getString("codicefiscale"),
                        rs.getInt("passeggero_id"),
                        rs.getInt("volo_id"),
                        rs.getString("stato")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

