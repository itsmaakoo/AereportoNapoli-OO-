package controller;
import gui.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UtenteGenericoController {
    public ActionListener getPrenotaVoloAction(){
        return e-> new PrenotaVolo();
    }
    public ActionListener getStatoVoloAction(){
        return e-> new StatoVolo();
    }
    public ActionListener getAggiungiBagaglioAction(){
        return e-> new AggiungiBagaglio();
    }
    public ActionListener getSegnalaBagaglioSmarritoAction(){
        return e-> new BagaglioSmarrito();
    }
    public ActionListener getLogoutAction(UtenteGenericoPanel frame){
        return e-> {
            new LoginFrame();

            frame.dispose();
        };
    }

}
