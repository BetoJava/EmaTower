package scripts.game.spells;


import scripts.game.entities.Character;
import scripts.game.statut.Poison;

public class JetOsSpell extends Spell{
    public JetOsSpell (){
        super("Jet d'Os",300,50, "offensif");
    }

    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);
        attaquant.addStatut(new Poison(attaquant));
    }

}
