package dao;

import model.Volo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoloDAO {
    private Connection connessione;

    public VoloDAO(Connection conn) {
        this.connessione = conn;
    }

    public boolean salvaVolo(Volo v) {
        String sql = "INSERT INTO volo (destinazione, data_partenza, data_arrivo, stato, gate) VALUES (?,?,?,?,?)";

        try (PreparedStatement stmt = connessione.prepareStatement(sql)) {
            stmt.setString(1, v.getDestinazione());
            stmt.setTimestamp(2, new java.sql.Timestamp(v.getDataPartenza().getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(v.getDataArrivo().getTime()));
            stmt.setString(4, v.getStato());
            stmt.setString(5, v.getGate());
            return stmt.executeUpdate() >0;


        } catch (SQLException e) {
            System.err.println("errore durante l'inserimento");
            e.printStackTrace();
            return false;
        }
    }



    public List<Volo> getTuttiVoli() {
        List<Volo> lista = new ArrayList<>();
        String sql = "SELECT * FROM volo";
        try (Statement stmt = connessione.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                String destinazione = rs.getString("destinazione");
                Date data_partenza = rs.getDate("data_partenza");
                Date data_arrivo = rs.getDate("data_arrivo");
                String stato = rs.getString("stato");
                String gate = rs.getString("gate");

                Volo v= new Volo(destinazione, data_partenza, data_arrivo, stato, gate);
                lista.add(v);
            }
        }catch (SQLException e){
            System.err.println("errore durante il recupero voli");
            e.printStackTrace();
        }
        return lista;
    }

    public void aggiornaStato(String destinazione, String nuovoStato){
        String sql = "UPDATE volo SET stato = ? WHERE destinazione = ?";
        try(PreparedStatement statement = connessione.prepareStatement(sql);){
            statement.setString(1, nuovoStato);
            statement.setString(2, destinazione);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}