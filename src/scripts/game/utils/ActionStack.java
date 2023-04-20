package scripts.game.utils;

import java.util.ArrayList;

/**
 * Classe qui est composée d'une file d'Actions.
 */
public class ActionStack {

    private ArrayList<Action> actions = new ArrayList<>();

    /**
     * Ajoute une action à la file des Actions.
     * @param a
     */
    public void addAction(Action a) {
        actions.add(a);
    }

    /**
     * Nettoi la file d'actions.
     */
    public void clear() {
        actions = new ArrayList<>();
    }

    /**
     * Fonction qui met à jour les délais des Actions de la file.
     */
    public void update() {
        if(!actions.isEmpty()) {
            actions.get(0).addDelay(-1);
            if(actions.get(0).getDelay() <= 0) {
                actions.get(0).action();
                actions.remove(0);
            }
        }

    }
}
