package scripts.game.entities.enemies;
import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;
import scripts.game.spells.*;


public class SqueletteDore extends Enemy {
    public SqueletteDore (){
        super("Squelette Doré",400,1000,0,-10,160,0.1f,2.2f,3);
        image = Resources.CHARACTERS.get("goldenSkeleton");
        this.sort1 = new JetOsSpell();
        this.sort2 = new TrancheSpell();
        this.sort3 = new OmbreSpell();
        this.sort4 = new CriSpell();
        initSpellList();
    }

}
