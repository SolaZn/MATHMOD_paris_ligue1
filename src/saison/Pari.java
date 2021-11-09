package saison;

import joueur.Parieur;

import java.util.ArrayList;

public class Pari {
    private Match match;
    private double mise;
    private Choix equipe;
    private double cote;
    private Parieur parieur;

    private Pari[] listeParisSurebet;
    private boolean isSurebet;

    public Pari(Parieur parieur, Match match, Choix equipe, double cote, double mise){
        this.match = match;
        this.mise = mise;
        this.equipe = equipe;
        this.cote = cote;
        this.isSurebet = false;

        this.parieur = parieur;
    }

    public Pari(Pari pari1, Pari pari2, Pari pari3){
        this.listeParisSurebet = new Pari[]{pari1, pari2, pari3};
        this.isSurebet = true;
    }

    @Override
    public String toString() {
        return "Le pari" + " est de " + parieur.getClass().getSimpleName() + " et concerne le match " + this.match.getDescMatch() + "; Mise : " + mise + " euros sur " + equipe + ", cote : " + cote;
    }

    public Parieur getParieur() {
        return this.parieur;
    }

    public Choix getEquipe() {
        return equipe;
    }

    public double getMise() {
        return this.mise;
    }

    public double getCote() {
        return this.cote;
    }

    public boolean getIsSurebet(){
        return isSurebet;
    }

    public Pari[] getListeParisSurebet(){
        return listeParisSurebet;
    }
}
