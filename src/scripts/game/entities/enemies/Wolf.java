package scripts.game.entities.enemies;
import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;
import scripts.game.spells.*;


public class Wolf extends Enemy {

    public Wolf() {
        super("Loup",200,1000,0,10,40,0.1f,2f,1);
        image = Resources.CHARACTERS.get("wolf");
        this.sort1 = new ChargeSpell();
        this.sort2 = new TrancheSpell();
        this.sort3 = new OmbreSpell();
        this.sort4 = new CriSpell();
        initSpellList();
    }
    
}
