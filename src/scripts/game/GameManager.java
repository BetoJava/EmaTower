package scripts.game;

import scripts.bot.Bot;
import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;
import scripts.game.entities.Enemy;
import scripts.game.entities.Player;
import scripts.game.gamestates.EndGameState;
import scripts.game.gamestates.StageState;
import scripts.game.utils.Action;
import scripts.main.Engine;

public class GameManager {

    private static Player currentPlayer;
    private static Bot bot = new Bot();
    private static int etage = 1;
    private static long score = 0;

    private static boolean isPlayerTurn = true;

    /**
     * Fonction appelée lorsque c'est le premier tour de jeu.
     */
    public static void firstTurn() {
        Engine.getGameStateManager().getActionStack().clear();
        isPlayerTurn = true;
        currentPlayer.initStats();
        if(currentPlayer.getAgilite() < bot.getEnemy().getAgilite()) {
            isPlayerTurn = false;
            Engine.getGameStateManager().disableButtons(true);
            Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
                @Override
                public void action() {
                    GameManager.nextTurn();
                }
            });
        }
    }

    /**
     * Fonction appelée lorsqu'on passe le tour.
     */
    public static void nextTurn() {
        Engine.getGameStateManager().disableButtons(true);
        if(isPlayerTurn){
            isPlayerTurn = false;

            // Tour du bot après un court délai
            Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
                @Override
                public void action() {
                    // Régénération d'endurance
                    GameManager.bot.getEnemy().addEndurance((int)(GameManager.bot.getEnemy().getEnduranceMax()*0.05));
                    // Application des effets
                    GameManager.bot.getEnemy().applyEffects();
                    // Si le bot ne doit pas passer son tour
                    if(!GameManager.bot.getEnemy().isStuned()) {
                        // Action du bot après un court délai
                        Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
                            @Override
                            public void action() {
                                // Action du bot
                                GameManager.bot.action();
                            }

                        });
                    }

            }});

        } else {
            isPlayerTurn = true;
            Engine.getGameStateManager().disableButtons(false);

            // Régénération d'endurance
            currentPlayer.addEndurance((int)(currentPlayer.getEnduranceMax()*0.05));
            // Application des effets
            currentPlayer.applyEffects();
        }
    }

    /**
     * Fonction appelée lorsque le combat est terminé.
     * @param winner
     * @param loser
     */
    public static void battleEnd(Character winner, Character loser) {

        WindowManager.getFurtiveTextGroup().clearInfoText();
        Engine.getGameStateManager().disableButtons(true);

        WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.NORMAL_DELAY, loser.getPseudo() + " est mort.");

        // Gain d'exp //
        winner.gainExp(loser.getExp());
        updateScore();

        GameManager.etage += 1;
        Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.LONG_DELAY) {
            @Override
            public void action() {
                WindowManager.clearFurtiveTexts();
                Engine.getGameStateManager().nextState(new StageState());
            }
        });

    }

    /**
     * Fonction appelée lorsque le héros est mort.
     */
    public static void gameOver() {
        updateScore();
        Engine.getGameStateManager().disableButtons(true);
        Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
            @Override
            public void action() {
                Engine.getGameStateManager().disableButtons(false);
                WindowManager.clearFurtiveTexts();
                Engine.getGameStateManager().nextState(new EndGameState());
            }
        });
    }

    /**
     * Fonction qui met à jour le score du joueur.
     */
    public static void updateScore() {
        score = (int)(etage*etage*100 + Math.pow(currentPlayer.getNiveau(),3)*100);
    }


    public static Enemy getCurrentEnemy() {
        return GameManager.bot.getEnemy();
    }

    public static void setCurrentEnemy(Enemy currentEnemy) {
        GameManager.bot.setEnemy(currentEnemy);
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        GameManager.currentPlayer = currentPlayer;
    }

    public static int getEtage() {
        return etage;
    }

    public static void setEtage(int etage) {
        GameManager.etage = etage;
    }

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        GameManager.score = score;
    }

    public static boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public static void setIsPlayerTurn(boolean isPlayerTurn) {
        GameManager.isPlayerTurn = isPlayerTurn;
    }
}
