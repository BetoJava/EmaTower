package scripts.game.entities.enemies;
import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;

import scripts.game.spells.TrancheSpell;
import scripts.game.spells.ChargeSpell;
import scripts.game.spells.SoinSpell;
import scripts.game.spells.CriSpell;



public class Sanglier extends Enemy{
    
    public Sanglier (){
        super("Sanglier",350,1000,0,18,30,0.1f,2f,1);
        image = Resources.CHARACTERS.get("sanglier");
        this.sort1 = new ChargeSpell();
        this.sort2 = new TrancheSpell();
        this.sort3 = new SoinSpell();
        this.sort4 = new CriSpell();
        initSpellList();
    }


}
