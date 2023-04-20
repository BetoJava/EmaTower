package scripts.game.entities.enemies;

import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;
import scripts.game.spells.BouleDeFeuSpell;
import scripts.game.spells.DodoSpell;
import scripts.game.spells.SoinSpell;
import scripts.game.spells.TourniquetSpell;

public class DarkLord extends Enemy {

    public DarkLord(){
        super("Seigneur sombre",500,1000,10,10,205,0.1f,2f,5);
        image = Resources.CHARACTERS.get("m5");
        this.sort1 = new TourniquetSpell();
        this.sort2 = new BouleDeFeuSpell();
        this.sort3 = new SoinSpell();
        this.sort4 = new DodoSpell();
        initSpellList();
    }
}
