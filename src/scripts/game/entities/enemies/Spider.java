package scripts.game.entities.enemies;
import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;
import scripts.game.spells.*;


public class Spider extends Enemy {
    public Spider() {
        super("Araignée géante",210,1000,0,0,150,0.1f,2f,2);
        image = Resources.CHARACTERS.get("spider");
        this.sort1 = new ChargeSpell();
        this.sort2 = new MorsureVenimeuse();
        this.sort3 = new SoinSpell();
        this.sort4 = new CriSpell();
        initSpellList();
    }

    
}
