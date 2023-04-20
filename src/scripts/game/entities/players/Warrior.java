package scripts.game.entities.players;

import scripts.display.resources.Resources;
import scripts.game.entities.Player;
import scripts.game.spells.*;


public class Warrior extends Player {
 
    public Warrior (){
        super("Warrior",1500,100,0,20,20,0.1f,2f);
        image = Resources.CHARACTERS.get("warrior");
        this.sort1 = new SwingSpell();
        this.sort2 = new Repos();
        this.sort3 = new CriDeGuerreSpell();
        this.sort4 = new ExecutionSpell();
        initSpellList();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        pvMax += 4*Math.pow(niveau+10,2);
        pv = pvMax;
        atk += Math.pow(niveau,2);
        defense += Math.pow(niveau,2);
        agilite += Math.pow(niveau+1,2)*2;
        dgCrit += 0.05f;
    }
   
    

}
