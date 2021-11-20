import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.*;

public class SalleJeux {
        private List<Poste> postes=new ArrayList<Poste>(); // les poste de la salle des jeux
        private List<Reservation> reservations=new ArrayList<Reservation>(); // liste global des reservations
        private List<Reservation> listeAttente=new ArrayList<Reservation>(); // la liste d'attente

    public SalleJeux() {
        // Initialisation Jeux
        Jeu fifa = new Jeu(CategorieJeu.FootBall, "FIFA");
        Jeu pes = new Jeu(CategorieJeu.FootBall, "PES");
        Jeu cs = new Jeu(CategorieJeu.Guerre, "COUNTER-STRIK");
        Jeu ac = new Jeu(CategorieJeu.Guerre, "ASSASIN'S CREED");


        //List<Jeu> p1list=new ArrayList<>();
        //p1list.add(fifa);
        //p1list.add(pes);
        //Poste p1=new Poste(ConsoleJeu.XBox,Ecran.DELL,p1list);
        //postes.add(p1);


        postes.add(new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(fifa, pes)));
        postes.add(new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(fifa, pes, cs)));
        postes.add(new Poste(ConsoleJeu.XBox, Ecran.HP, Arrays.asList(fifa, pes, cs, ac)));
        postes.add(new Poste(ConsoleJeu.PlayStation5, Ecran.ASUS, Arrays.asList(pes, ac)));
        postes.add(new Poste(ConsoleJeu.PlayStation5, Ecran.ASUS, Arrays.asList(pes, cs, ac)));
        postes.add(new Poste(ConsoleJeu.NintendoSwitch, Ecran.ASUS, Arrays.asList(fifa, pes, cs)));
        postes.add(new Poste(ConsoleJeu.NintendoSwitch, Ecran.SAMSUNG, Arrays.asList(fifa, ac, pes)));
    }

    public Jeu SelectJeu(){
        // menu de jeu
    }

    public Period SelectPeriod(){
        // menu de selection periode
    }

    public Joueur SelectJoueur(){
        // menu pour ajouter un joueur par le gerant
    }

    public void AjouterReservation(){
        Joueur joueur=SelectJoueur();
        Jeu jeu= SelectJeu();
        Period period=SelectPeriod();

        Reservation reservation=new Reservation(joueur,period,jeu);
        // verification d'etat de resevation et asigner la la reservation sur poste ou a la liste d'attente selon le cas

    }
    public EtatReservation VerifierReservation(Reservation reservation){
        //Retourn l'etat de la reservation et assign le poste en cas de non annulation
    }

    public void ControlePeriodique(){
        //appel de function controlePeriodique des postes et assign un joueur de la liste d'attente dans le cas
        // de liberation de poste
    }

    public float  CalculGainJournalier(int jour,int mois,int année){

    }
    public float  CalculGainJournalierActuell(){

    }


    public float  CalculGainMonsuelle(int mois,int année){

    }
    public float  CalculGainMonsuelleAcuell(){

    }

}
