package scripts.game.gamestates;

import scripts.game.utils.ActionStack;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> states;
	private ActionStack actionStack;
	// Current state is the one at the index 0 //
	
	public GameStateManager()
	{
		this.states = new ArrayList<>();
		actionStack = new ActionStack();
	}

	public void nextState(GameState nextState)
	{
		if(!states.isEmpty()){
			states.get(0).clearState();
			states.remove(0);
		}
		states.add(0,nextState);
	}

	public void doNextAction() {
		actionStack.update();
	}

	public void render(Graphics2D graphics)
	{
		this.states.get(0).render(graphics);
	}
	
	public void keyPressed(int keyCode)
	{
		this.states.get(0).keyPressed(keyCode);
	}
	
	public void keyReleased(int keyCode)
	{
		this.states.get(0).keyReleased(keyCode);
	}

	public void mouseClicked(MouseEvent e)
	{
		this.states.get(0).mouseClicked(e);
	}

	public void mousePressed(MouseEvent e)
	{
		this.states.get(0).mousePressed(e);
	}

	public void mouseReleased(MouseEvent e)
	{
		this.states.get(0).mouseReleased(e);
	}

	public void mouseMoved(MouseEvent e) {

	}

    public void mouseWheelMoved(MouseWheelEvent e) {
		this.states.get(0).mouseWheelMoved(e);
    }

	public ActionStack getActionStack() {
		return actionStack;
	}

	public void disableButtons(boolean b) {
		states.get(0).buttonGroup.disableButtons(b);
	}
}
