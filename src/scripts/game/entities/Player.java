package scripts.game.entities;

import scripts.game.GameManager;
import scripts.main.Engine;

import java.awt.image.BufferedImage;

public abstract class Player extends Character {
    protected int xpCap;

    public Player (String className, int pvMax, int enduranceMax, int atk, int defense, int agilite, float critique, float dgCrit){
        this.className = className;
        this.pvMax = pvMax;
        this.pv = pvMax;
        this.enduranceMax = enduranceMax;
        this.endurance = enduranceMax;
        this.atk = atk;
        this.defense = defense;
        this.agilite = agilite;
        this.critique = critique;
        this.dgCrit = dgCrit;
        this.exp = 0;
        this.niveau = 1;
        this.xpCap = 100;
    }
    public void passageNiveau() {
        if (exp>=xpCap){
            exp-=xpCap;
            niveau++;
            xpCap=(int)(20*getExp()*(0.1*niveau));
        }

    }

    @Override
    public void die(Character attaquant, Character loser) {
        // gameover
        this.fadeOut();
        Engine.getGameStateManager().disableButtons(true);
        GameManager.gameOver();
    }
}
