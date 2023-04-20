package scripts.game.gamestates;

import scripts.display.gui.components.SButton;
import scripts.display.gui.components.SLabel;
import scripts.display.gui.WindowManager;
import scripts.display.resources.Resources;
import scripts.game.entities.Player;
import scripts.game.entities.players.Assassin;
import scripts.game.entities.players.Warrior;
import scripts.game.entities.players.Wizard;
import scripts.main.Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class CharSelectState extends GameState {

    private boolean capsLocked = false;
    private String pseudo = "";

    // Widgets _________________________________________________________________________________________________________ //

    // Labels //
    private SLabel nameLabel = new SLabel((Color) null,"Pseudo : ", Resources.FONTS.get("Fira"),Color.white,32f,120,110,0,0, false);
    private SLabel classLabel = new SLabel((Color) null,"Classe :", Resources.FONTS.get("Fira"),Color.white,32f,120,185,0,0, false);

    // Bouttons Classe //
    private SButton warriorButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Warrior",
            Resources.FONTS.get("Fira"), new Color(25,50,100), new Color(30, 70, 148), 0.25f, 30f,
            260,150,60,150,2,4)
    {
        @Override
        public void action()
        {
            if(pseudo.length() > 0) {
                createCharacter("warrior");
                Engine.getGameStateManager().nextState(new StageState());
            }
        }
    };
    private SButton wizardButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Wizard",
            Resources.FONTS.get("Fira"), new Color(25,50,100), new Color(30, 70, 148), 0.25f, 30f,
            430,150,60,150,2,4)
    {
        @Override
        public void action()
        {
            if(pseudo.length() > 0) {
                createCharacter("wizard");
                Engine.getGameStateManager().nextState(new StageState());
            }
        }
    };

    private SButton assassinButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Assassin",
            Resources.FONTS.get("Fira"), new Color(25,50,100), new Color(30, 70, 148), 0.25f, 30f,
            600,150,60,150,2,4)
    {
        @Override
        public void action()
        {
            if(pseudo.length() > 0) {
                createCharacter("assassin");
                Engine.getGameStateManager().nextState(new StageState());
            }
        }
    };




    // Quit //
    private SButton quitButton = new SButton(Resources.TEXTURES.get("UISprite1"),"Quitter",
            Resources.FONTS.get("Fira"), new Color(166, 32, 32), new Color(194, 19, 19), 0.35f, 35f,
            WindowManager.WIDTH/2-150,440,80,300,2,4)
    {
        @Override
        public void action()
        {
            System.exit(0);
        }
    };

    // _________________________________________________________________________________________________________________ //

    public CharSelectState() {
        init();
    }

    private void init()
    {
        buttonGroup.add(warriorButton);
        buttonGroup.add(wizardButton);
        buttonGroup.add(assassinButton);
        buttonGroup.add(quitButton);
        labelGroup.add(nameLabel);
        labelGroup.add(classLabel);
    }

    public void createCharacter(String classe) {
        Player player;
        switch(classe) {
            case "warrior" :
                player = new Warrior();
                break;
            case "wizard" :
                player = new Wizard();
                break;
            default :
                player = new Assassin();
        }
        player.setPseudo(pseudo);
        Engine.getGameManager().setCurrentPlayer(player);
    }

    @Override
    protected void render(Graphics2D graphics) {
        labelGroup.render(graphics);
        buttonGroup.render(graphics);
    }

    @Override
    protected void keyPressed(int keyCode) {
        // Système de récupération des touches clavier //
            // Shift //
        if(keyCode == KeyEvent.VK_SHIFT) {
            capsLocked = true;
        }
            // MAJ //
        if(keyCode == KeyEvent.VK_CAPS_LOCK) {
            if(capsLocked) {
                capsLocked = false;
            }
            else {
                capsLocked = true;
            }
        }
            // A-Z //
        if(keyCode >= KeyEvent.VK_A && keyCode <= KeyEvent.VK_Z) {
            String letter = KeyEvent.getKeyText(keyCode);
            if(!capsLocked) {
                letter = letter.toLowerCase();
            }
            if(pseudo.length() < 16) {
                pseudo += letter;
                nameLabel.setText("Pseudo : " + pseudo);
            }
        }
            // Espace //
        if(keyCode == KeyEvent.VK_SPACE) {
            if(pseudo.length() < 16) {
                pseudo += " ";
                nameLabel.setText("Pseudo : " + pseudo);
            }
        }
            // Tiret - //
        if(keyCode == KeyEvent.VK_6) {
            if(pseudo.length() < 16) {
                pseudo += "-";
                nameLabel.setText("Pseudo : " + pseudo);
            }
        }
            // Retour //
        if(keyCode == KeyEvent.VK_BACK_SPACE) {
            if(pseudo.length() > 0) {
                pseudo = pseudo.substring(0, pseudo.length() - 1);
                nameLabel.setText("Pseudo : " + pseudo);
            }
        }
    }

    @Override
    protected void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_SHIFT) {
            capsLocked = false;
        }
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
