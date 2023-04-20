package scripts.game.utils;

public abstract class Action {

    private int delay = 0;

    public Action(int delay) {
        this.delay = delay;
    }

    /**
     * Fonction à redéfinir par la l'objet qui implémente l'interface Action.
     */
    public abstract void action();

    public void addDelay(int i) {
        delay += i;
    }

    public int getDelay() {
        return delay;
    }
}
