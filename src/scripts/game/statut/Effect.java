package scripts.game.statut;

import scripts.game.entities.Character;

public abstract class Effect {
    protected int boostAttaque = 0;
    protected int boostDefense = 0;
    protected float boostCritique = 0;
    protected float boostDgCritique = 0;
    protected int degats = 0;
    protected int delai = 0;

    protected boolean passTurn = false;

    protected Character lanceur;

    public Effect(Character lanceur, int delai) {
        this.delai = delai;
        this.lanceur = lanceur;
    }

    /**
     * Fonction appelée lorsque l'effet est appliqué.
     * @param cible
     */
    public abstract void action (Character cible);


    /**
     * @return
     */
    public boolean isPassTurn() {
        return passTurn;
    }

    /**
     * @return
     */
    public Character getLanceur() {
        return lanceur;
    }

    public void setLanceur(Character lanceur) {
        this.lanceur = lanceur;
    }

    public int getBoostAttaque() {
        return boostAttaque;
    }

    public void setBoostAttaque(int boostAttaque) {
        this.boostAttaque = boostAttaque;
    }

    public int getBoostDefense() {
        return boostDefense;
    }

    public void setBoostDefense(int boostDefense) {
        this.boostDefense = boostDefense;
    }

    public float getBoostCritique() {
        return boostCritique;
    }

    public void setBoostCritique(float boostCritique) {
        this.boostCritique = boostCritique;
    }

    public float getBoostDgCritique() {
        return boostDgCritique;
    }

    public void setBoostDgCritique(float boostDgCritique) {
        this.boostDgCritique = boostDgCritique;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }
    public int getDegats() {
        return degats;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    
}
