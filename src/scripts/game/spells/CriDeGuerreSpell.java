package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Effect;
import scripts.game.statut.Peur;

public class CriDeGuerreSpell extends Spell {
    
    public CriDeGuerreSpell(){
        super("Cri de guerre",10,30, "boost");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);

        // On ajoute un boost de défense et attaque pour 3 tours
        Effect boost = new Effect(attaquant,3) {
            @Override
            public void action(Character cible) {

            }
        };
        boost.setBoostDefense(5);
        boost.setBoostAttaque(35);
        attaquant.addStatut(boost);

        // On ajoute le statut peur à la cible
        cible.addStatut(new Peur(attaquant));
    }
}
