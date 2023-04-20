package scripts.game.gamestates;

import scripts.display.gui.groups.ButtonGroup;
import scripts.display.gui.groups.FurtiveTextGroup;
import scripts.display.gui.groups.ImageGroup;
import scripts.display.gui.groups.LabelGroup;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class GameState {

	protected GameStateManager gameStateManager;
	protected ImageGroup imageGroup = new ImageGroup();
	protected LabelGroup labelGroup = new LabelGroup();
	protected ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Fonction d'affichage de l'élément.
	 * @param graphics Graphics2D
	 */
	protected abstract void render(Graphics2D graphics);

	public void clearState() {
		imageGroup.clear();
		labelGroup.clear();
		buttonGroup.clear();
		Engine.getWindowManager().getFrame().repaint();
	}

	/**
	 * Fonction appelée quand une touche est pressée.
	 * @param keyCode
	 */
	protected abstract void keyPressed(int keyCode);

	/**
	 * Fonction appelée quand une touche est relâchée.
	 * @param keyCode
	 */
	protected abstract void keyReleased(int keyCode);

	/**
	 * Fonction appelée quand la souris est clickée.
	 * @param e
	 */
	protected abstract void mouseClicked(MouseEvent e);

	/**
	 * Fonction appelée quand la souris est pressée.
	 * @param e
	 */
	protected abstract void mousePressed(MouseEvent e);

	/**
	 * Fonction appelée quand la souris est relâchée.
	 * @param e
	 */
	protected abstract void mouseReleased(MouseEvent e);

	/**
	 * Fonction appelée quand la molette de la souris est bougée.
	 * @param e
	 */
	protected abstract void mouseWheelMoved(MouseWheelEvent e);

}
