package scripts.game.entities.players;

import scripts.display.resources.Resources;
import scripts.game.entities.Player;
import scripts.game.spells.*;


public class Assassin extends Player {
    
    public Assassin() {
        super("Assassin",1250,100,0,10,100,0.2f, 2f);
        image = Resources.CHARACTERS.get("assassin");
        this.sort1 = new DrainSpell();
        this.sort2 = new PasDansOmbreSpell();
        this.sort3 = new PresageMortelSpell();
        this.sort4 = new DecapitationSpell();
        initSpellList();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        pvMax += 4*Math.pow(niveau+10,2);
        pv = pvMax;
        atk += (int)(Math.pow(niveau,2)*1.2);
        defense += Math.pow(niveau,2)/2;
        agilite += Math.pow(niveau+1,2)*6;
        dgCrit += 0.1f;
    }

   
}
