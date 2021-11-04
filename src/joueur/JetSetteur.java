package joueur;

import saison.Match;
import saison.Pari;

import java.util.ArrayList;
import java.util.Collections;

public class JetSetteur extends Parieur {

    public JetSetteur(){
        super();
    }

    public Pari parier(Match match){
        ArrayList<Double> cotesMatch = new ArrayList<>();
        cotesMatch.addAll(match.getCotes());

        // on calcule le TRJ du pari
        double TRJPari = calculTRJ(cotesMatch);

        Collections.sort(cotesMatch);
        Double coteChoisie = cotesMatch.get(2); // on récupère la dernière valeur, qui équivaut à la plus grosse

        // il faudrait déterminer à quoi correspond une grosse cote (genre 2.5 minimum) pour que le parieur ne parie que
        // sur les matches qui ont de grosses cotes (et pas seulement la plus grosse de chaque match)
        /*double critere = pariOptimalparKelly(coteChoisie, TRJPari);
        double mise = critere * bankroll;

        // si l'application du critère de Kelly renvoie un nombre négatif -> pari risqué -> renvoi d'une valeur correspondant à la moyenne des joueurs

        double coefMoyenBankroll = 0.025;
        while(mise < 1){
            mise = coefMoyenBankroll * bankroll;
            coefMoyenBankroll += 0.01;
        }

        int temp = (int)(mise*100.0);
        mise = ((double)temp)/100.0;*/

        /* double mise = bankroll * 0.15;*/

        double mise = MISEMOYENNE;

        if(mise < 1  || bankroll < 0){
            if(idJourneeSansleSou.equals("")){
                idJourneeSansleSou = match.getJournee();
            }
            System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Pas assez d'argent... Mise refusée");
            System.out.println(this.getClass().getSimpleName() + " est à sec depuis la " + this.idJourneeSansleSou);
            return null;
        }

        if(coteChoisie <= Parieur.GROSSECOTE){
            System.out.println(this.getClass().getSimpleName() + " n'a pas pu parier :<(");
            System.out.println("Cote pas assez risquée pour " + this.getClass().getSimpleName());
            return null;
        }else{
            setBankroll(mise);
            nombreParisEffectues++;
            return new Pari(this, match, match.getChoixbyCote(coteChoisie), coteChoisie, mise);
        }
    }
}
