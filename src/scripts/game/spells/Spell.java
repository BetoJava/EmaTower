package scripts.game.spells;

import scripts.display.gui.WindowManager;
import scripts.game.entities.Character;

public abstract class Spell {
    protected String nameSpell;
    protected int degats;
    protected int endurance;

    // valeurs possible : "offensif", "boost", "soin"
    protected String spellType;
    
    public Spell(String nameSpell, int degats, int endurance, String spellType){
        this.nameSpell = nameSpell;
        this.degats = degats;
        this.endurance = endurance;
        this.spellType = spellType;
    }

    /**
     * Fonction appelée lorsque le sort est utilisé.
     * @param attaquant
     * @param cible
     */
    public void action(Character attaquant, Character cible) {
        WindowManager.getFurtiveTextGroup().addInfoText(50, attaquant.getPseudo() + " utilise " + this.nameSpell + ".");

        // On retire l'endurance nécessaire au lancement du sort //
        attaquant.addEndurance(-this.endurance);

        // On calcul les dg infligés //
        int dg;
        if(degats > 0) {
            dg = Character.dg(attaquant, cible, this);
            // On applique les dg sur la cible //
            cible.takeDamage(dg, attaquant);
        } else {
            dg = Character.soin(attaquant, this);
            // On applique les soins sur l'attaquant //
            attaquant.takeDamage(dg, attaquant);
        }

    }


    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public String getSpellType() {
        return spellType;
    }
}
