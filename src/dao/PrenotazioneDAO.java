package dao;

import db.ConnessioneDB;
import model.Prenotazione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneDAO {

    public PrenotazioneDAO(Connection conn) {
    }

    public void salvaPrenotazione(Prenotazione p){
        String sql = "INSERT INTO prenotazioni(num_biglietto, passeggeroId, documento, voloId, prenotazioneId, stato_prenotazione) VALUES(?,?,?,?,?,?)";
        try(Connection connessione = ConnessioneDB.getConnection();
            PreparedStatement stmt = connessione.prepareStatement(sql)){

            // i vari get (getCognome....) devono essere aggiunti nel Passeggero model
            stmt.setInt(1, p.getNum_biglietto());
            stmt.setInt(2, p.getPasseggeroId());
            stmt.setString(3, p.getDocumento());
            stmt.setInt(4, p.getVoloId());
            stmt.setInt(5, p.getPrenotazioneId());
            stmt.setString(6, p.getStato_prenotazione());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Prenotazione> trovaPrenotazioni() throws SQLException {
        List<Prenotazione> lista = new ArrayList<Prenotazione>();
        String sql = "SELECT * FROM prenotazioni";

        try(Connection connessione = ConnessioneDB.getConnection();
            Statement stmt = connessione.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Prenotazione p = new Prenotazione(
                        rs.getInt ("num_biglietto"),
                        rs.getInt ("passeggeroId"),
                        rs.getString("documento"),
                        rs.getInt ("voloId"),
                        rs.getInt ("prenotazioneId"),
                        rs.getString("stato_prenotazione")
                );
                lista.add(p);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<Prenotazione> trovaPrenotazioniPerVolo(int Id) throws SQLException {
        List<Prenotazione> lista = new ArrayList<Prenotazione>();
        String sql = "SELECT * FROM prenotazioni WHERE voloId=?";

        try (Connection connessione = ConnessioneDB.getConnection();
             PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setInt(1, Id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Prenotazione p = new Prenotazione(
                        rs.getInt("num_biglietto"),
                        rs.getInt("passeggeroId"),
                        rs.getString("documento"),
                        rs.getInt("voloId"),
                        rs.getInt("prenotazioneId"),
                        rs.getString("stato_prenotazione")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
