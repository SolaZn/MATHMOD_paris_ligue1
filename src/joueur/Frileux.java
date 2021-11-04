package joueur;

import saison.Match;
import saison.Pari;

import java.util.ArrayList;
import java.util.Collections;

public class Frileux extends Parieur {
    public Frileux(){
        super();
    }

    public Pari parier(Match match){
        ArrayList<Double> cotesMatch = new ArrayList<>();
        cotesMatch.addAll(match.getCotes());

        double TRJPari = calculTRJ(cotesMatch);

        Collections.sort(cotesMatch);
        Double coteChoisie = cotesMatch.get(0); // on récupère la première valeur, qui équivaut à la plus petite

        /* double critere = pariOptimalparKelly(coteChoisie, TRJPari);
        double mise = critere * bankroll;

        // si l'application du critère de Kelly renvoie un nombre négatif -> pari risqué -> renvoi d'une valeur correspondant à la moyenne des joueurs
        double coefMoyenBankroll = 0.025;
        while(mise < 1){
            mise = coefMoyenBankroll * bankroll;
            coefMoyenBankroll += 0.01;
        }

        int temp = (int)(mise*100.0);
        mise = ((double)temp)/100.0; */

        double mise = bankroll * 0.025;
        if(mise < 1  || bankroll < 0){
            if(idJourneeSansleSou.equals("")){
                idJourneeSansleSou = match.getJournee();
            }
            System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Pas assez d'argent... Mise refusée");
            System.out.println(this.getClass().getSimpleName() + " est à sec " + this.idJourneeSansleSou);
            return null;
        }


        if(coteChoisie > Parieur.PETITECOTE){
            System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Cote trop risquée pour " + this.getClass().getSimpleName());
            return null;
        }else {
            setBankroll(mise);
            nombreParisEffectues++;
            return new Pari(this, match, match.getChoixbyCote(coteChoisie), coteChoisie, mise);
        }
    }
}
