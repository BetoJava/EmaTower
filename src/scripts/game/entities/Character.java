package scripts.game.entities;

import scripts.display.gui.WindowManager;
import scripts.game.GameManager;
import scripts.game.gamestates.StageState;
import scripts.game.spells.Spell;
import scripts.game.statut.Effect;
import scripts.game.utils.Action;
import scripts.main.Engine;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Character {

    protected String className;
    protected String pseudo;
    protected BufferedImage image;

    protected int pvMax;
    protected int pv; // Point de vie du joueur, quand il est nul ou négatif c'est game over
    protected int enduranceMax;
    protected int endurance; // Energie du joueur, essentiel pour lancer une attaque. Elle varie pendant le combat
    protected int atk; // Attaque du joueur, c'est une constante qu'on pourra améliorer dans le jeu via des équipements ou en lvlup si on est vraiment trop chaud
    protected int defense; // Défense du joueur, c'est une constante qu'on pourra améliorer dans le jeu via des équipements ou en lvlup si on est vraiment trop chaud
    protected int agilite; // Pour déterminer qui commence au tour 1
    protected float critique; // Probabilité de critique
    protected float dgCrit;   // Dégat mutilplicatif de critique
    protected int exp;
    protected int niveau;
    
    protected ArrayList<Effect> effects = new ArrayList<>(); // Liste des Statuts du joueur
    protected boolean isStuned = false;
          
    protected Spell sort1;
    protected Spell sort2;
    protected Spell sort3;
    protected Spell sort4;
    protected ArrayList<Spell> spells = new ArrayList<>();

    
    // Fonctions de combat
    /**
     * Renvoie le nombre de dégat infligés par un sort à la cible.
     * @param attaquant
     * @param cible
     * @param sort
     * @return
     */
    public static int dg(Character attaquant, Character cible, Spell sort) {
        // Dégats infligés
        int dg = (int)(sort.getDegats()*((attaquant.getAtk() + attaquant.sommeBoostAtk())/100f - (cible.getDefense()+cible.sommeBoostDef())/100f + 1));
        // Coup critique
        Random r = new Random();
        int rd = r.nextInt(100);
        if(rd/100f < (attaquant.critique+attaquant.sommeBoostCritique())) {
            dg = (int)(dg * (attaquant.dgCrit+attaquant.sommeBoostDgCritique()));
            WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, "Coup Critique !");
        }
        return Math.max(0, dg);
    }

    /**
     * Renvoie le soin appliqué par un sort à la cible.
     * @param attaquant
     * @param sort
     * @return
     */
    public static int soin(Character attaquant, Spell sort) {
        // Soin effectués
        int soin = (int)(-attaquant.getPvMax()*sort.getDegats()/100f*(attaquant.getAtk()/100f + 1));
        // Coup critique
        Random r = new Random();
        int rd = r.nextInt(100);
        if(rd/100f < attaquant.critique) {
            soin = (int)(soin * attaquant.dgCrit);
            WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, "Coup Critique !");
        }

        if(soin > attaquant.getPvMax() - attaquant.getPv()) {
            soin = attaquant.getPvMax() - attaquant.getPv();
        }

        return -Math.max(0, soin);
    }

    /**
     * Applique les dommage à l'objet character.
     * @param dg
     * @param attaquant
     */
    public void takeDamage(int dg, Character attaquant) {
        this.pv -= dg;
        WindowManager.getFurtiveTextGroup().addDgText(WindowManager.NORMAL_DELAY, dg, this.equals(Engine.getGameManager().getCurrentPlayer()));
        if(pv <= 0) {
            pv = 0;
            die(attaquant, this);

        }
    }

    /**
     * Applique et vérifie l'utilisation de l'effet.
     */
    public void applyEffects(){
        boolean passTurn = false;
        ArrayList<Effect> toRemove = new ArrayList<>();

        for (Effect e : effects){
            // Réduire le délai
            e.setDelai(e.getDelai()-1);
            // Supprimer l'effet si délai nul
            if (e.getDelai() == 0){
                toRemove.add(e);
            }

            // Appliquer l'effet
            e.action(this);
            // Si l'effet fait passer le tour
            if(e.isPassTurn()) {
                this.isStuned = true;
                passTurn = true;
            }
        }
        for (Effect e : toRemove) {
            effects.remove(e);
        }

        if (passTurn) {
            GameManager.nextTurn();
        } else {
            this.isStuned = false;
        }

    }

    /**
     * Fonction qui sera appelée lors de la mort de l'objet character.
     * @param attaquant
     * @param loser
     */
    public abstract void die(Character attaquant, Character loser);

    /**
     * Fonction qui attribue l'exp à cet objet character.
     * @param exp
     */
    public void gainExp(int exp) {
        this.exp += exp;
        Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.LONG_DELAY) {
            @Override
            public void action() {
                WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.LONG_DELAY, pseudo + " gagne " + String.valueOf(exp) + " exp !");

            }
        });

        boolean up = false;
        while(this.exp >= Math.pow(4+niveau,3)) {
            this.exp -= Math.pow(4+niveau,3);
            levelUp();
            up = true;
        }
        if(up) {
            Engine.getGameStateManager().getActionStack().addAction(new Action(WindowManager.SHORT_DELAY) {
                @Override
                public void action() {
                    WindowManager.getFurtiveTextGroup().addInfoText(WindowManager.SHORT_DELAY, pseudo + " passe niveau " + String.valueOf(niveau) + " !");
                }
            });
        }
    }

    /**
     * Gain de niveau pour cet objet character.
     */
    public void levelUp() {
        niveau += 1;
    }

    /**
     * Fonction qui est appelée pour faire disparaître l'image de l'ennemi.
     */
    public void fadeOut() {
        /*
        for(int i = 0; i < 127; i++) {
            this.image = DisplayFunctions.changeColor(this.image, Color.black,(255-i*2)/255);
        }
         */
    }

    /**
     * Fonction qui initialise l'endurance pour les début de combat.
     */
    public void initStats() {
        endurance = enduranceMax;
    }

    /**
     * Ajoute les sorts dans la liste des sorts.
     */
    protected void initSpellList() {
        if(sort1 != null) {
            spells.add(sort1);
        }
        if(sort2 != null) {
            spells.add(sort2);
        }
        if(sort3 != null) {
            spells.add(sort3);
        }
        if(sort4 != null) {
            spells.add(sort4);
        }
    }

    /**
     * Renvoi la somme de boosts d'atk.
     * @return
     */
    public int sommeBoostAtk() {
        int somme = 0;
        for (Effect e : effects){
            somme += e.getBoostAttaque();
        }
        return somme;
    }

    /**
     * Renvoi la somme de boosts de défense.
     * @return
     */
    public int sommeBoostDef() {
        int somme = 0;
        for (Effect e : effects){
            somme += e.getBoostDefense();
        }
        return somme;
    }

    /**
     * Renvoi la somme de boosts de critique.
     * @return
     */
    public int sommeBoostCritique() {
        int somme = 0;
        for (Effect e : effects){
            somme += e.getBoostCritique();
        }
        return somme;
    }

    /**
     * Renvoi la somme de boosts de dégats critiques.
     * @return
     */
    public int sommeBoostDgCritique() {
        int somme = 0;
        for (Effect e : effects){
            somme += e.getBoostDgCritique();
        }
        return somme;
    }

    /**
     * Getters and Setters
    */
    public void addStatut(Effect effet){
        effects.add(effet);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getPvMax() {
        return pvMax;
    }

    public void setPvMax(int pvMax) {
        this.pvMax = pvMax;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getEnduranceMax() {
        return enduranceMax;
    }

    public void setEnduranceMax(int enduranceMax) {
        this.enduranceMax = enduranceMax;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAgilite() {
        return agilite;
    }

    public void setAgilite(int agilite) {
        this.agilite = agilite;
    }

    public float getCritique() {
        return critique;
    }

    public void setCritique(float critique) {
        this.critique = critique;
    }

    public float getDgCrit() {
        return dgCrit;
    }

    public void setDgCrit(float dgCrit) {
        this.dgCrit = dgCrit;
    }

    public Spell getSort1() {
        return sort1;
    }

    public void setSort1(Spell sort1) {
        this.sort1 = sort1;
    }

    public Spell getSort2() {
        return sort2;
    }

    public void setSort2(Spell sort2) {
        this.sort2 = sort2;
    }

    public Spell getSort3() {
        return sort3;
    }

    public void setSort3(Spell sort3) {
        this.sort3 = sort3;
    }

    public Spell getSort4() {
        return sort4;
    }

    public void setSort4(Spell sort4) {
        this.sort4 = sort4;
    }
    
    public int getExp() {
        return exp;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public ArrayList<Effect> getStatut() {
        return effects;
    }

    public void setStatut(ArrayList<Effect> statut) {
        this.effects = statut;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public boolean isStuned() {
        return isStuned;
    }

    public void setStuned(boolean stuned) {
        isStuned = stuned;
    }

    public void addPV(int toAdd) {
        pv += toAdd;
        if(pv > pvMax) {
            pv = pvMax;
        }
        if(pv < 0) {
            pv = 0;
        }
    }

    public void addEndurance(int toAdd) {
        endurance += toAdd;
        if(endurance > enduranceMax) {
            endurance = enduranceMax;
        }
        if(endurance < 0) {
            endurance = 0;
        }
    }

    public void addAtk(int toAdd) {
        atk += toAdd;
        if(atk < 0) {
            atk = 0;
        }
    }

    public void addDef(int toAdd) {
        defense += toAdd;
        if(defense < 0) {
            defense = 0;
        }
    }

    public void addAgilite(int toAdd) {
        agilite += toAdd;
        if(agilite < 0) {
            agilite = 0;
        }
    }

    public void addCritique(int toAdd) {
        critique += toAdd;
        if(critique < 0) {
            critique = 0;
        }
    }

    public void addDgCritique(int toAdd) {
        dgCrit += toAdd;

        if(dgCrit < 0) {
            dgCrit = 0;
        }
    }

    public void addExp(int toAdd) {
        exp += toAdd;
    }

}




