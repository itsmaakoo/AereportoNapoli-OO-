package gui;

import dao.VoloDAO;
import db.ConnessioneDB;
import model.Volo;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class StatoVolo extends JFrame {
    public StatoVolo() {
        setTitle("Stato del Volo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<Volo> model = new DefaultListModel<>();
        try{
            Connection conn = ConnessioneDB.getConnection();
            if(conn != null){
                VoloDAO voloDao = new VoloDAO(conn);
                List<Volo> voli= voloDao.getTuttiVoli();
                for(Volo v : voli){
                    model.addElement(v);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Connessione non trovato");
            }
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore nel caricamento dei voli");
        }

        JList<Volo> lista= new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(lista);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
}
}