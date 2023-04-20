import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;
import scripts.game.statut.Effect;


public class Etourdie extends Effect {
    
    public Etourdie(Character lanceur){
        super(lanceur, 1);
        passTurn = true;
    }
    
    @Override
    public void action(Character cible){
        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, cible.getPseudo() + " est étourdi.");
    }
}
    
