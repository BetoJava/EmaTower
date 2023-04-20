package scripts.game.spells;


import scripts.game.entities.Character;
import scripts.game.statut.Effect;
import scripts.main.Engine;

public class ArmureDeFlammeSpell extends Spell {
    
    public ArmureDeFlammeSpell (){
        super("Armure de Flamme",0, 20, "boost");
    }


    public void action(Character attaquant, Character cible) {
        super.action(attaquant, cible);

        // On ajoute un boost de défense et attaque pour 3 tours
        Effect boost = new Effect(attaquant,3) {
            @Override
            public void action(Character cible) {

            }
        };
        boost.setBoostDefense(35 + attaquant.getDefense());
        boost.setBoostAttaque(10 + (int)(attaquant.getDefense()*0.1f));
        attaquant.addStatut(boost);

    }
}
