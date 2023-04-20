package scripts.game.gamestates;

import scripts.display.gui.components.SButton;
import scripts.display.gui.components.SLabel;
import scripts.display.gui.WindowManager;
import scripts.display.resources.Resources;
import scripts.display.utils.MusicPlayer;
import scripts.game.GameManager;
import scripts.game.entities.Enemy;
import scripts.game.entities.enemies.*;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.Random;

public class StageState extends GameState{

    // Widgets _________________________________________________________________________________________________________ //

    // Labels //
    private SLabel title = new SLabel((Color) null,"Etage " + Engine.getGameManager().getEtage(), Resources.FONTS.get("Fira"),Color.white,72f, WindowManager.WIDTH/2,100,0,0, true);
    private SLabel classLabel = new SLabel((Color) null,"Score : " + Engine.getGameManager().getScore(), Resources.FONTS.get("Fira"),Color.white,32f,WindowManager.WIDTH/2,175,0,0, true);

        // Caractéristiques //
    int x = 15;
    int y = 0;
    float fontSize = 20f;

    private SLabel pseudo = new SLabel((Color) null,Engine.getGameManager().getCurrentPlayer().getPseudo(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,25+y,0,0, false);
    private SLabel niv = new SLabel((Color) null,"Niv : " + Engine.getGameManager().getCurrentPlayer().getNiveau(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,50+y,0,0, false);
    private SLabel exp = new SLabel((Color) null,"XP : " + Engine.getGameManager().getCurrentPlayer().getExp(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,75+y,0,0, false);

    private SLabel pv = new SLabel((Color) null,"PV : " + Engine.getGameManager().getCurrentPlayer().getPv() + "/" + Engine.getGameManager().getCurrentPlayer().getPvMax(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,100+y,0,0, false);
    private SLabel endurance = new SLabel((Color) null,"Endurance : " + Engine.getGameManager().getCurrentPlayer().getEnduranceMax(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,125+y,0,0, false);
    private SLabel atk = new SLabel((Color) null,"Attaque : " + Engine.getGameManager().getCurrentPlayer().getAtk(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,150+y,0,0, false);
    private SLabel def = new SLabel((Color) null,"Défense : " + Engine.getGameManager().getCurrentPlayer().getDefense(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,175+y,0,0, false);
    private SLabel agi = new SLabel((Color) null,"Agilité : " + Engine.getGameManager().getCurrentPlayer().getAgilite(), Resources.FONTS.get("Fira"),Color.white,fontSize,x,200+y,0,0, false);


    // Buttons //
    private SButton continueButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Continuer",
            Resources.FONTS.get("Fira"), new Color(70, 166, 32), new Color(104, 194, 19), 0.35f, 35f,
            WindowManager.WIDTH/2-150,440,80,300,2,4)
    {
        @Override
        public void action()
        {
            newEnemy();
            Engine.getGameStateManager().nextState(new FightStage());
            GameManager.firstTurn();
        }
    };

    private SButton quitButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Quitter",
            Resources.FONTS.get("Fira"), new Color(166, 32, 32), new Color(194, 19, 19), 0.35f, 35f,
            WindowManager.WIDTH/2-150,540,80,300,2,4)
    {
        @Override
        public void action()
        {
            Engine.getGameStateManager().nextState(new EndGameState());
        }
    };

    // _________________________________________________________________________________________________________________ //

    private void newEnemy() {
        Enemy enemy;
        GameManager gameManager = Engine.getGameManager();
        int etage = gameManager.getEtage();

        // Si l'étage est divisible par 10 //
        if((float)(etage/10) == etage/10f) {
            enemy = new DarkLord();
        } else {
            ArrayList<Enemy> enemies = new ArrayList<>();
            enemies.add(new Sanglier());
            enemies.add(new Spider());
            enemies.add(new SqueletteDore());
            enemies.add(new Skeleton());
            enemies.add(new Wolf());

            Random r = new Random();
            enemy = enemies.get(r.nextInt(5));
        }
        enemy.setPseudo(enemy.getClassName());
        enemy.setNiveau(etage);

        for(int i = 1; i < etage+1; i++) {
            enemy.setPvMax((int)(6.5f*Math.pow(i,2)+enemy.getPvMax()));
            enemy.setAtk((int)(enemy.getAtk() + Math.pow(i,2)));
            enemy.setAgilite((int)(enemy.getAgilite() + Math.pow(i,2)));
            enemy.setExp((int)(enemy.getExp() + Math.pow(i+10,2)));
        }

        enemy.setDefense((int)(enemy.getDefense() + Math.pow(etage,2)));
        enemy.setPv(enemy.getPvMax());

        gameManager.setCurrentEnemy(enemy);
    }

    // _________________________________________________________________________________________________________________ //

    public StageState() {
        init();
    }

    private void init()
    {
        Engine.getWindowManager().getPanel().setBackground(new Color(0, 0, 0,255));
        buttonGroup.add(continueButton);
        buttonGroup.add(quitButton);
        labelGroup.add(title);
        labelGroup.add(classLabel);
        labelGroup.add(pseudo);
        labelGroup.add(niv);
        labelGroup.add(exp);
        labelGroup.add(pv);
        labelGroup.add(endurance);
        labelGroup.add(atk);
        labelGroup.add(def);
        labelGroup.add(agi);
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
