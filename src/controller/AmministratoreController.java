package controller;

import gui.*;

public class AmministratoreController {

    public void apriInserisciVolo(){
        new InserisciVolo();
    }

    public void apriModificaVolo(){
        new ModificaVolo();
    }

    public void apriAssegnaGate(){
        new AssegnaGate();
    }

    public void apriAggiornaBagaglio(){
        new AggiornaBagaglio();
    }

    public void apriVisualizzaSmarrimenti(){
        new VisualizzaSmarrimenti();
    }

    public void apriVisualizzaPasseggeri(){
        new VisualizzaPasseggeri();
    }

    public void logout(){
        new LoginFrame();
    }
}
