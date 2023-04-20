package scripts.main;

import scripts.display.resources.Loader;

public class Main {

    /**
     * Fonction d'entrée du programme.
     * @param args
     */
    public static void main(String[] args) {
        new Loader().load();
        Engine.init();
        Engine.start();

    }
}
