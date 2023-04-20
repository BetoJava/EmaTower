package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Endormie;

public class DodoSpell extends Spell{

    public DodoSpell(){
        super("Dodo", 0, 25, "boost");
    }

    public void action(Character attaquant, Character cible){
        super.action(attaquant, cible);
        cible.addStatut(new Endormie(attaquant));
    }
}
