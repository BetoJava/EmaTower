package scripts.game.spells;


import scripts.game.entities.Character;
import scripts.game.statut.Poison;

public class PresageMortelSpell extends Spell {
    
    public PresageMortelSpell(){
        super("Présage Mortel", 75, 20, "offensif");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);
        cible.addStatut(new Poison(attaquant));
    }
}
