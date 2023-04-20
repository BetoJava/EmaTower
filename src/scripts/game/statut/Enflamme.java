package scripts.game.statut;
import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;


public class Enflamme extends Effect{

    public Enflamme(Character lanceur){
        super(lanceur,2);
    }
    
    @Override
    public void action(Character cible){
        int dg = cible.getPvMax()/10;

        cible.takeDamage(dg,lanceur);

        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, cible.getPseudo() + " brûle.");
    }
    
}
