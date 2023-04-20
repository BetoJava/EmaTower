package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Effect;
import scripts.game.statut.Peur;


public class CriSpell extends Spell {
    
    public CriSpell(){
        super("Cri", 0, 50, "boost");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);

        // On ajoute un boost de défense et attaque pour 2 tours
        Effect boost = new Effect(attaquant,2) {
            @Override
            public void action(Character cible) {

            }
        };
        boost.setBoostDefense(25);
        boost.setBoostAttaque(100);
        attaquant.addStatut(boost);

        // On ajoute le statut peur à la cible
        cible.addStatut(new Peur(attaquant));
    }
}
