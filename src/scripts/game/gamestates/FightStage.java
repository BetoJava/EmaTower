package scripts.game.gamestates;

import scripts.display.utils.MusicPlayer;
import scripts.game.GameManager;
import scripts.game.entities.Enemy;
import scripts.game.entities.Player;
import scripts.display.gui.components.SButton;
import scripts.display.gui.components.SImage;
import scripts.display.gui.components.SLabel;
import scripts.display.gui.WindowManager;
import scripts.display.gui.components.StatInterface;
import scripts.display.resources.Resources;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class FightStage extends GameState {

    // Widgets _________________________________________________________________________________________________________ //

    // Characters //
    private SImage foe = new SImage(Engine.getGameManager().getCurrentEnemy().getImage(),725-Engine.getGameManager().getCurrentEnemy().getImage().getWidth()/2,175-Engine.getGameManager().getCurrentEnemy().getImage().getHeight()/2,2f);
    private SImage player = new SImage(Engine.getGameManager().getCurrentPlayer().getImage(),275-Engine.getGameManager().getCurrentPlayer().getImage().getWidth()/2,300-Engine.getGameManager().getCurrentPlayer().getImage().getHeight()/2,2f);

    // Titles //
    private SLabel panel = new SLabel(new Color(159, 159, 159, 255), 0, 500, 200, 1000);

    // Stat Interfaces //
    private StatInterface playerStats = new StatInterface(Engine.getGameManager().getCurrentPlayer(), 650, 399);
    private StatInterface foeStats = new StatInterface(Engine.getGameManager().getCurrentEnemy(), 0,0);

    // Spells //
    private SButton sort1 = new SButton(Resources.TEXTURES.get("UISpritePopup"),Engine.getGameManager().getCurrentPlayer().getSort1().getNameSpell(),
            Resources.FONTS.get("Fira"), new Color(255, 255, 255, 100), new Color(119, 119, 119, 255), new Color(185, 185, 185), 0.25f, 28f,
            0,498,100,320,4,4)
    {
        @Override
        public void action()
        {
            Player attaquant = Engine.getGameManager().getCurrentPlayer();
            Enemy cible = Engine.getGameManager().getCurrentEnemy();
            if(attaquant.getEndurance() >= attaquant.getSort1().getEndurance() && !cible.isDead()) {
                attaquant.getSort1().action(attaquant, cible);
                GameManager.nextTurn();
            }
        }
    };

    private SButton sort2 = new SButton(Resources.TEXTURES.get("UISpritePopup"),Engine.getGameManager().getCurrentPlayer().getSort2().getNameSpell(),
            Resources.FONTS.get("Fira"), new Color(255, 255, 255, 100), new Color(119, 119, 119, 255), new Color(185, 185, 185), 0.25f, 28f,
            0,600,100,320,4,4)
    {
        @Override
        public void action()
        {
            Player attaquant = Engine.getGameManager().getCurrentPlayer();
            Enemy cible = Engine.getGameManager().getCurrentEnemy();
            if(attaquant.getEndurance() >= attaquant.getSort1().getEndurance() && !cible.isDead()) {
                attaquant.getSort2().action(attaquant, cible);
                GameManager.nextTurn();
            }
        }
    };

    private SButton sort3 = new SButton(Resources.TEXTURES.get("UISpritePopup"),Engine.getGameManager().getCurrentPlayer().getSort3().getNameSpell(),
            Resources.FONTS.get("Fira"), new Color(255, 255, 255, 100), new Color(119, 119, 119, 255), new Color(185, 185, 185), 0.25f, 28f,
            322,498,100,320,4,4)
    {
        @Override
        public void action()
        {
            Player attaquant = Engine.getGameManager().getCurrentPlayer();
            Enemy cible = Engine.getGameManager().getCurrentEnemy();
            if(attaquant.getEndurance() >= attaquant.getSort1().getEndurance() && !cible.isDead()) {
                attaquant.getSort3().action(attaquant, cible);
                GameManager.nextTurn();
            }
        }
    };

    private SButton sort4 = new SButton(Resources.TEXTURES.get("UISpritePopup"),Engine.getGameManager().getCurrentPlayer().getSort4().getNameSpell(),
            Resources.FONTS.get("Fira"), new Color(255, 255, 255, 100), new Color(119, 119, 119, 255), new Color(185, 185, 185), 0.25f, 28f,
            322,600,100,320,4,4)
    {
        @Override
        public void action()
        {
            Player attaquant = Engine.getGameManager().getCurrentPlayer();
            Enemy cible = Engine.getGameManager().getCurrentEnemy();
            if(attaquant.getEndurance() >= attaquant.getSort1().getEndurance() && !cible.isDead()) {
                attaquant.getSort4().action(attaquant, cible);
                GameManager.nextTurn();
            }
        }
    };

    private SButton passerTour = new SButton(Resources.TEXTURES.get("UISpritePopup"), "Passer",
            Resources.FONTS.get("Fira"), new Color(255, 255, 255, 100), new Color(119, 119, 119, 255), new Color(185, 185, 185), 0.25f, 28f,
            685,550,100,250,4,4)
    {
        @Override
        public void action()
        {
            WindowManager.getFurtiveTextGroup().addInfoText(100, Engine.getGameManager().getCurrentPlayer().getPseudo() + " passe son tour.");
            GameManager.nextTurn();
        }
    };


    /*
    // Potion Buttons //
    private SButton sort2 = new SButton(Resources.TEXTURES.get("UISprite1"),"Swing",
            Resources.FONTS.get("Fira"), new Color(255, 185, 0, 100), new Color(103, 76, 7, 255), new Color(190, 143, 15), 0.25f, 32f,
            0,600,100,180,2,4)
    {
        @Override
        public void action()
        {

        }
    };
    */



    // Quit //
    private SButton quitButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Quitter",
            Resources.FONTS.get("Fira"), new Color(255,0,0,25), new Color(166, 32, 32), new Color(194, 19, 19), 0.35f, 20f,
            WindowManager.WIDTH-105,5,45,100,2,4)
    {
        @Override
        public void action()
        {
            System.exit(0);
        }
    };

    // _________________________________________________________________________________________________________________ //

    public FightStage() {
        init();
    }

    private void init()
    {
        Engine.getWindowManager().getPanel().setBackground(new Color(156, 194, 112,255));
        labelGroup.add(panel);
        buttonGroup.add(sort1);
        buttonGroup.add(sort2);
        buttonGroup.add(sort3);
        buttonGroup.add(sort4);
        buttonGroup.add(passerTour);
        buttonGroup.add(quitButton);
        imageGroup.add(foe);
        imageGroup.add(player);
    }


    @Override
    protected void render(Graphics2D graphics) {
        labelGroup.render(graphics);
        buttonGroup.render(graphics);
        imageGroup.render(graphics);
        playerStats.render(graphics);
        foeStats.render(graphics);
        WindowManager.getFurtiveTextGroup().render(graphics);
    }

    @Override
    protected void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_SPACE) {
            Engine.getGameManager().getCurrentPlayer().setEndurance(100);
        }
        if(keyCode == KeyEvent.VK_P) {
            Engine.getGameManager().getCurrentPlayer().levelUp();
        }
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
