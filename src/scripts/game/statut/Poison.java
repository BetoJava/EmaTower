package scripts.game.statut;


import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;

public class Poison extends Effect {

    public Poison(Character lanceur){
        super(lanceur, 4);
    }
    
    @Override
    public void action(Character cible){
        int dg = cible.getPvMax()/16;

        cible.takeDamage(dg,lanceur);

        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, cible.getPseudo() + " souffre du poison.");
    }

}
