package scripts.main;

import scripts.display.utils.MusicPlayer;
import scripts.game.GameManager;
import scripts.game.gamestates.MainMenu;
import scripts.display.gui.WindowManager;
import scripts.game.gamestates.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Engine {

    private static GameStateManager gameStateManager;
    private static GameManager gameManager;
    private static WindowManager windowManager;

    /**
     * Fonction appelée pour initialiser les attributs statiques.
     */
    public static void init() {
        gameStateManager = new GameStateManager();
        windowManager = new WindowManager();
        gameManager = new GameManager();
    }

    /**
     * Fonction appelée pour débuter le programme.
     */
    public static void start() {
        //MusicPlayer.playMusic();
        gameStateManager.nextState(new MainMenu());
        windowManager.addPanel(new GamePanel());
        windowManager.addKeyListener(new Keyboard());
        Mouse m = new Mouse();
        windowManager.addMouseListener(m, m, m);
        windowManager.createWindow();
    }

    public static GameStateManager getGameStateManager() {
        return gameStateManager;
    }
    public static GameManager getGameManager() {
        return gameManager;
    }
    public static WindowManager getWindowManager() {return windowManager;}


    //________________________________________________________________//
    private static class GamePanel extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        protected void paintComponent(Graphics g) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Graphics2D graphics = (Graphics2D)g;

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            super.paintComponent(graphics);
            gameStateManager.render(graphics);
            gameStateManager.doNextAction();
            repaint();
        }
    }

    //________________________________________________________________//
    private static class Keyboard implements KeyListener {

        @Override
        public void keyPressed(KeyEvent key) {
            gameStateManager.keyPressed(key.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent key) {
            gameStateManager.keyReleased(key.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent arg0) {}

    }

    //________________________________________________________________//
    private static class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            gameStateManager.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            gameStateManager.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gameStateManager.mouseReleased(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            gameStateManager.mouseMoved(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            gameStateManager.mouseWheelMoved(e);
        }
    }

}
