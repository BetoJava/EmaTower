package scripts.game.statut;

import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;

public class Endormie extends Effect{
    
    public Endormie(Character lanceur){
        super(lanceur,2);
        passTurn = true;
    }
    
    @Override
    public void action(Character cible){
        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, cible.getPseudo() + " dort.");
    }
}
