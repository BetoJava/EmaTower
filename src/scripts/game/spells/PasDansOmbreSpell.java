package scripts.game.spells;

import scripts.game.entities.Character;
import scripts.game.statut.Effect;

public class PasDansOmbreSpell extends Spell{
    
    public PasDansOmbreSpell (){
        super ("Pas Dans l'Ombre", 0, 20, "boost");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);

        // On ajoute un boost de défense et attaque pour 2 tours
        Effect boost = new Effect(attaquant,2) {
            @Override
            public void action(Character cible) {

            }
        };
        boost.setBoostDefense(100 + (int)(attaquant.getDefense()*1.5f));
        attaquant.addStatut(boost);
    }
}
