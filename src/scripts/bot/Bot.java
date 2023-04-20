package scripts.bot;

import scripts.display.gui.WindowManager;
import scripts.game.GameManager;
import scripts.game.entities.Enemy;
import scripts.game.entities.Player;
import scripts.game.spells.Spell;
import scripts.game.utils.Action;
import scripts.main.Engine;

import java.util.ArrayList;
import java.util.Random;


public class Bot {

    private Enemy enemy;

    private int offensiveCoeff = 50;
    private int boostCoeff = 50;
    private int healingCoeff = 0;

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }


    /**
     * Cette méthode fait jouer l'ennemi : sélection intelligente du sort, attaque et tour suivant
     */
    public void action() {
        Player player = Engine.getGameManager().getCurrentPlayer();

        //Sélection du sort que va jouer l'ennemi + attaque
        Spell spell = choseSpell();
        spell.action(enemy, player);

        //Attente pendant qu'un message est affiché + tour suivant
        Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
            @Override
            public void action() {
                GameManager.nextTurn();
            }
        });
    }

    private Spell choseSpell() {
        // On range les sorts en fonction de leur usage
        ArrayList<Spell> offensiveSpells = new ArrayList<>();
        ArrayList<Spell> boostSpells = new ArrayList<>();
        ArrayList<Spell> healingSpells = new ArrayList<>();

        for (Spell s : enemy.getSpells()) {
            switch (s.getSpellType()) {
                case "offensif":
                    offensiveSpells.add(s);
                    break;
                case "soin" :
                    healingSpells.add(s);
                    break;
                default :
                    boostSpells.add(s);
            }
        }

        // On calcule des coefficients afin d'utiliser les sorts intelligemment
        healingCoeff = (int)(100 * (1 - enemy.getPv()/((float)(enemy.getPvMax()))));

        // On rajoute une part d'aléatoire dans les coeff
        Random r = new Random();
        offensiveCoeff += r.nextInt(100);
        boostCoeff += r.nextInt(100);
        healingCoeff += r.nextInt(100);

        // S'il n'y a pas de sort de boost ou de soin, on applique un coeff négatif
        if(boostSpells.size() == 0) {
            boostCoeff =-1;
        }
        if(healingSpells.size() == 0) {
            healingCoeff = -1;
        }

        // On choisit le coeff max, et on retourne un sort aléatoire dans la liste du type du coeff
        if (offensiveCoeff >= healingCoeff && offensiveCoeff >= boostCoeff) {
            return randomSpell(offensiveSpells);
        } else if (healingCoeff >= offensiveCoeff && healingCoeff >= boostCoeff) {
            return randomSpell(healingSpells);
        } else {
            // On réduit la probabilité de lancer 2 sorts de boost d'affilé
            boostCoeff = 15;
            return randomSpell(boostSpells);
        }

    }

    private Spell randomSpell(ArrayList<Spell> spellList) {
        Random r = new Random();
        int index = r.nextInt(spellList.size());
        return spellList.get(index);
    }

}
