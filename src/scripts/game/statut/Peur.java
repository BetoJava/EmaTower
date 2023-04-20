package scripts.game.statut;

import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;


public class Peur extends Effect {
    
    public Peur(Character lanceur){
        super(lanceur, 1);
        passTurn = true;
    }
    
    @Override
    public void action (Character cible){
        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, cible.getPseudo() + " est téntanisé de peur.");
    }
    
}
