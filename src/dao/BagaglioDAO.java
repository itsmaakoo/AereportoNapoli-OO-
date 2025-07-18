package dao;

import model.Bagaglio;
import model.StatoBagaglio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getString;

public class BagaglioDAO {
    private Connection connessione;

    public BagaglioDAO(Connection connessione){
        this.connessione = connessione;
    }
    public void add(Bagaglio b, int prenotazioneId) throws SQLException{
        String sql = "INSERT INTO bagagli (codice, prenotazioneId, stato, descrizione) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setInt(1, b.getCodice());
            stmt.setInt(2, b.getPrenotazioneId());
            stmt.setString(3, b.getStato().name());
            stmt.setString(4, b.getDescrizione());
            stmt.executeUpdate();
        }
    }
    public List<Bagaglio> trovaBagagliPerPrenotazione(int prenotazioneId) throws SQLException{
        List<Bagaglio> bagagli = new ArrayList<>();
        String sql = "SELECT * FROM bagagli WHERE prenotazioneId = ?";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setInt(1, prenotazioneId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
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
    public void aggiornaStatoBagaglio(String codiceTracciamento, StatoBagaglio nuovoStato) throws SQLException{
        String sql = "UPDATE stato SET stato = ? WHERE codiceTracciamento = ?";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setString(1, nuovoStato.name());
            stmt.setString(2, codiceTracciamento);
            stmt.executeUpdate();
        }
    }
    public void eliminaBagaglio(int codiceTracciamento) throws SQLException{
        String sql = "DELETE FROM bagagli WHERE codiceTracciamento = ?";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setInt(1, codiceTracciamento);
            stmt.executeUpdate();
        }
    }
    public Bagaglio trovaCodice(int codiceTracciamento) throws SQLException{
        String sql = "SELECT * FROM bagagli WHERE codiceTracciamento = ?";
        try(PreparedStatement stmt = connessione.prepareStatement(sql)){
            stmt.setInt(1, codiceTracciamento);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Bagaglio b = new Bagaglio(
                        rs.getInt("prenotazioneId"),
                        rs.getInt("codiceTraccaimento"),
                        rs.getString("descrizione")
                );
                b.setStato(StatoBagaglio.valueOf(rs.getString("stato")));
                return b;
            }
        }
        return null;
    }
}
