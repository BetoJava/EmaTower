package scripts.game.entities.players;

import scripts.display.resources.Resources;
import scripts.game.entities.Player;
import scripts.game.spells.ArmureDeFlammeSpell;
import scripts.game.spells.BouleDeFeuSpell;
import scripts.game.spells.NueeDeMeteoreSpell;
import scripts.game.spells.RegenerationBrulanteSpell;

public class Wizard extends Player {
    
    public Wizard() {
        super("Wizard",1000,100,0,0,50,0.1f,2f);
        image = Resources.CHARACTERS.get("wizard");
        this.sort1 = new BouleDeFeuSpell();
        this.sort2 = new RegenerationBrulanteSpell();
        this.sort3 = new ArmureDeFlammeSpell();
        this.sort4 = new NueeDeMeteoreSpell();
        initSpellList();
    }

    @Override
    public void levelUp() {
        super.levelUp();
        pvMax += 4*Math.pow(niveau+10,2);
        pv = pvMax;
        atk += (int)(Math.pow(niveau,2)*1.5);
        defense += Math.pow(niveau,2)/4;
        agilite += Math.pow(niveau+1,2);
        dgCrit += 0.05f;
    }
    
}
