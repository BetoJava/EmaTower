package scripts.game.gamestates;

import scripts.display.gui.WindowManager;
import scripts.display.gui.components.SButton;
import scripts.display.gui.components.SLabel;
import scripts.display.resources.Resources;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EndGameState extends GameState {

    // Titles //
    private SLabel title = new SLabel((Color) null,"Fin de partie", Resources.FONTS.get("Fira"),Color.white,72f, WindowManager.WIDTH/2,100,0,0, true);

    // Stats //
    private SLabel score = new SLabel((Color) null,"Score : " + Engine.getGameManager().getScore(), Resources.FONTS.get("Fira"),new Color(255, 172, 0, 255),40f,WindowManager.WIDTH/2,200,0,0, true);

    private SLabel pseudo = new SLabel((Color) null,Engine.getGameManager().getCurrentPlayer().getPseudo(), Resources.FONTS.get("Fira"),new Color(185, 185, 185,255),30,WindowManager.WIDTH/2,275,0,0, true);
    private SLabel niv = new SLabel((Color) null,"Niv : " + Engine.getGameManager().getCurrentPlayer().getNiveau(), Resources.FONTS.get("Fira"),new Color(185, 185, 185, 255),30,WindowManager.WIDTH/2,317,0,0, true);
    private SLabel exp = new SLabel((Color) null,"XP : " + Engine.getGameManager().getCurrentPlayer().getExp(), Resources.FONTS.get("Fira"),new Color(185, 185, 185,255),30,WindowManager.WIDTH/2,359,0,0, true);

    // Quit //
    private SButton quitButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Quitter",
            Resources.FONTS.get("Fira"), new Color(166, 32, 32), new Color(194, 19, 19), 0.35f, 32f,
            WindowManager.WIDTH/2-150,500,80,300,2,4)
    {
        @Override
        public void action()
        {
            System.exit(0);
        }
    };


    public EndGameState() {
        Engine.getWindowManager().getPanel().setBackground(new Color(0, 0, 0,255));
        buttonGroup.add(quitButton);
        labelGroup.add(title);
        labelGroup.add(score);
        labelGroup.add(pseudo);
        labelGroup.add(niv);
        labelGroup.add(exp);
    }


    @Override
    protected void render(Graphics2D graphics) {
        labelGroup.render(graphics);
        buttonGroup.render(graphics);
    }

    @Override
    protected void keyPressed(int keyCode) {

    }

    @Override
    protected void keyReleased(int keyCode) {

    }

    @Override
    protected void mouseClicked(MouseEvent e) {

    }

    @Override
    protected void mousePressed(MouseEvent e) {
        buttonGroup.pressed(e);
    }

    @Override
    protected void mouseReleased(MouseEvent e) {
        buttonGroup.released(e);
    }

    @Override
    protected void mouseWheelMoved(MouseWheelEvent e) {

    }
}
