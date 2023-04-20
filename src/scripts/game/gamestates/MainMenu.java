package scripts.game.gamestates;

import scripts.display.gui.components.SButton;
import scripts.display.gui.components.SLabel;
import scripts.display.gui.WindowManager;
import scripts.display.resources.Resources;
import scripts.display.utils.MusicPlayer;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MainMenu extends GameState {

    // Widgets _________________________________________________________________________________________________________ //

    // Titles //
    private SLabel title = new SLabel((Color) null,"EMA'ToWer",Resources.FONTS.get("Fira"),Color.white,118f, WindowManager.WIDTH/2,100,0,0, true);
    private SLabel subTitle = new SLabel((Color) null,"made by EdViThiJib",Resources.FONTS.get("Fira"),new Color(145, 145, 145),48f,WindowManager.WIDTH/2,200,0,0, true);


    // Nouvelle Partie //
    private SButton connectionButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Nouvelle partie",
            Resources.FONTS.get("Fira"), new Color(25,50,100), new Color(30, 70, 148), 0.25f, 70f,
            WindowManager.WIDTH/2-300,250,160,600,2,4)
    {
        @Override
        public void action()
        {
            // Changement de state //
            Engine.getGameStateManager().nextState(new CharSelectState());
        }
    };



    // Quit //
    private SButton quitButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Quitter",
            Resources.FONTS.get("Fira"), new Color(166, 32, 32), new Color(194, 19, 19), 0.35f, 70f,
            WindowManager.WIDTH/2-300,440,160,600,2,4)
    {
        @Override
        public void action()
        {
            System.exit(0);
        }
    };

    // _________________________________________________________________________________________________________________ //

    public MainMenu() {
        init();
    }

    private void init()
    {
        buttonGroup.add(connectionButton);
        buttonGroup.add(quitButton);
        labelGroup.add(title);
        labelGroup.add(subTitle);
    }


    @Override
    protected void render(Graphics2D graphics) {
        labelGroup.render(graphics);
        buttonGroup.render(graphics);
    }

    @Override
    protected void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_S) {
            MusicPlayer.stopMusic();
        }
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
