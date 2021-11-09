package joueur;

import saison.Choix;
import saison.Match;
import saison.Pari;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Professor extends Parieur {
    private double mise;
    private double bankrollMartingale;
    /**
     * fais la martingale la plus simple
     */
    public Professor(){
        super();
        bankrollMartingale = 0;
        mise = -1;

    }

    @Override
    public void resetParieur() {
        super.resetParieur();
        bankrollMartingale = 0;
        mise = -1;
    }

    private double coefMultiplicateur(Choix choix, Match match){
        if(choix == Choix.A){
            return match.getCotes().get(2) / (match.getCotes().get(2) - 1);
        }
        if(choix == Choix.D){
            return match.getCotes().get(1) / (match.getCotes().get(1) - 1);
        }
        if(choix == Choix.H){
            return match.getCotes().get(0) / (match.getCotes().get(0) - 1);
        }
        return 1;
    }

    private void appliquerMartingale(Match match, Choix choixEquipe){ // martingale classique
        if(mise == -1){ // si c'est le premier pari effectué
            this.mise = MISEMOYENNE;
            bankrollMartingale = bankroll;
        } else if(bankroll < bankrollMartingale){ // si le pari précédent est perdu
            mise *= Math.abs(coefMultiplicateur(choixEquipe,match));
        }
    }


    @Override
    public Pari parier(Match match) {
        // 1 : t'as un rand : si il est pair -> il parie
        // 2 : meme rand : autre nombre : on multiplie deux rands par 1000 -> si la différence entre les deux est entre -500 et 500 alors c'est un nul; au dessus de 500 -> équipe 1, en dessous de -500, équipe 2
        ArrayList<Choix> listeChoix = new ArrayList();
        listeChoix.add(Choix.A);
        listeChoix.add(Choix.H);
        listeChoix.add(Choix.D);

        for(int i=0;i<10;i++){
            Collections.shuffle(listeChoix);
        }

        appliquerMartingale(match, listeChoix.get(0));

        if(bankroll - mise < DECOUVERTMARTINGALE){
            if(idJourFin.equals("")){
                idJourFin = match.getJournee();
            }
            /*System.out.println(this.getClass().getSimpleName() + " a du emprunter pour jouer :<)");
            System.out.println(this.getClass().getSimpleName() + " est à sec depuis " + this.idJourFin);*/
            return null;
        }


        if(new Random().nextInt()%2 ==0) {
            if (listeChoix.get(0) == Choix.A) {
                bankrollMartingale = bankroll;
                setBankroll(mise);
                nbEff++;
                return new Pari(this, match, Choix.A, match.getCotes().get(2), mise);
            } else {
                if (listeChoix.get(0) == Choix.D) {
                    bankrollMartingale = bankroll;
                    setBankroll(mise);
                    nbEff++;
                    return new Pari(this, match, Choix.D, match.getCotes().get(1), mise);
                } else {
                    if (listeChoix.get(0) == Choix.H) {
                        bankrollMartingale = bankroll;
                        setBankroll(mise);
                        nbEff++;
                        return new Pari(this, match, Choix.H, match.getCotes().get(0), mise);
                    }
                }
            }
        }
        return null;
    }
}
