package saison;

import joueur.Parieur;

public class Pari {
    private Match match;
    private double mise;
    private Choix equipe;
    private double cote;
    private Parieur parieur;

    public Pari(Match match, double mise, Choix equipe, Parieur parieur, double cote){
        this.match = match;
        this.mise = mise;
        this.equipe = equipe;
        this.cote = cote;

        this.parieur = parieur;
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
}
