package controller;

import gui.*;

/**
 * Codice che viene utilizzato per la gestione delle operazioni dell'amministratore
 */
public class AmministratoreController {

    /**
     * Apre l'interfaccia per l'inserimento di un nuovo voli in partenza.
     * Crea un istanza di {@link InserisciVolo}
     */
    public void apriInserisciVolo(){
        new InserisciVolo();
    }

    /**
     * Apre l'interfaccia per l'inserimento di un nuovo voli in arrivo.
     * Crea un istanza di {@link InserisciVoloArrivo}
     */
    public void apriInserisciVoloArrivo() {
        new InserisciVoloArrivo();
    }

    /**
     * Apre l'interfaccia per la modifica dei dati di un volo esistente.
     * Crea un istanza di {@link ModificaVolo}
     */
    public void apriModificaVolo(){
        new ModificaVolo();
    }

    /**
     * Apre l'interfaccia per l'assegnamento di un gate a un volo.
     * Crea un istanza di {@link AssegnaGate}
     */
    public void apriAssegnaGate(){
        new AssegnaGate();
    }

    /**
     * Apre l'interfaccia per l'aggiornamento dei bagagli soociati al volo.
     * Crea un istanza di {@link AggiornaBagaglio}
     */
    public void apriAggiornaBagaglio(){
        new AggiornaBagaglio();
    }

    /**
     * Apre l'interfaccia per la visualizzazione dei bagali smarriti.
     * Crea un istanza di {@link VisualizzaSmarrimenti}
     */
    public void apriVisualizzaSmarrimenti(){
        new VisualizzaSmarrimenti();
    }

    /**
     * Apre l'interfaccia per la visualizzazione dei passeggeri associati al volo.
     * Crea un istanza di {@link VisualizzaPasseggeri}
     */
    public void apriVisualizzaPasseggeri(){
        new VisualizzaPasseggeri();
    }

    /**
     * Esegue il logiut dell'amministratore e ritorna alla schermata login
     * Crea un istanaza di {@link LoginFrame}
     */
    public void logout(){
        new LoginFrame();
    }
}