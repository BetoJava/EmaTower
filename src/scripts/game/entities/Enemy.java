package scripts.game.entities;


import scripts.game.GameManager;
import scripts.main.Engine;

public abstract class Enemy extends Character {

    private boolean isDead = false;

    public Enemy (String className, int pvMax, int enduranceMax, int atk, int defense, int agilite, float critique, float dgCrit, int exp){
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
        this.exp = exp;
    }

    @Override
    public void die(Character attaquant, Character loser) {
        this.fadeOut();
        Engine.getGameStateManager().disableButtons(true);
        GameManager.setIsPlayerTurn(false);
        if(!isDead) {
            GameManager.battleEnd(attaquant, loser);
        }
        isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }
}

