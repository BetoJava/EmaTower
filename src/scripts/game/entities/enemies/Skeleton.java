package scripts.game.entities.enemies;
import scripts.display.resources.Resources;
import scripts.game.entities.Enemy;
import scripts.game.spells.*;


public class Skeleton extends Enemy {
    public Skeleton (){
        super("Squelette",250,1000,0,0,40,0.1f,2f,1);
        image = Resources.CHARACTERS.get("skeleton");
        this.sort1 = new JetOsSpell();
        this.sort2 = new TrancheSpell();
        this.sort3 = new OmbreSpell();
        this.sort4 = new CriSpell();
        initSpellList();
    }
    
    
    
    
}
