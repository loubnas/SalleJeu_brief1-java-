import java.util.*;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.ReadInt;

public class SalleJeux {
    private List<Poste> postes=new ArrayList<Poste>(); // les poste de la salle des jeux
    private List<Reservation> reservations=new ArrayList<Reservation>(); // liste global des reservations
    private List<Reservation> listeAttente=new ArrayList<Reservation>(); // la liste d'attente
    private List<Jeu> listeJeux=new ArrayList<Jeu>(); // la liste des jeux de la salle

    //constructeur :
    
    public SalleJeux() {

        // Initialisation Jeux
        Jeu fifa = new Jeu(CategorieJeu.FootBall, "FIFA");
        listeJeux.add(fifa);
        Jeu pes = new Jeu(CategorieJeu.FootBall, "PES");
        listeJeux.add(pes);
        Jeu cs = new Jeu(CategorieJeu.Guerre, "COUNTER-STRIK");
        listeJeux.add(cs);
        Jeu ac = new Jeu(CategorieJeu.Guerre, "ASSASIN'S CREED");
        listeJeux.add(ac);

        //List<Jeu> p1list=new ArrayList<>();
        //p1list.add(fifa);
        //p1list.add(pes);
        //Poste p1=new Poste(ConsoleJeu.XBox,Ecran.DELL,p1list);
        //postes.add(p1);


        postes.add(new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(fifa, pes)));
        postes.add(new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList( pes, cs)));
        postes.add(new Poste(ConsoleJeu.XBox, Ecran.HP, Arrays.asList( pes, cs, ac)));
        postes.add(new Poste(ConsoleJeu.PlayStation5, Ecran.ASUS, Arrays.asList(pes, ac)));
        postes.add(new Poste(ConsoleJeu.PlayStation5, Ecran.ASUS, Arrays.asList(pes, cs, ac)));
        postes.add(new Poste(ConsoleJeu.NintendoSwitch, Ecran.ASUS, Arrays.asList( pes, cs)));
        postes.add(new Poste(ConsoleJeu.NintendoSwitch, Ecran.SAMSUNG, Arrays.asList(fifa, ac, pes)));
    }

    public Jeu SelectJeu(){
        // menu de jeu
        int choix=-1;
        do {

            Print("------- Selection de jeu -------");

            // boucle liste des jeux :
            for(int i=0;i<listeJeux.size();i++){
                Print((i+1)+". "+listeJeux.get(i).getNomJeu()+".");
            }

            Print((listeJeux.size()+1)+". Quitter.");

            choix=ReadInt("Selectionner un jeu de la liste  : ");

        }while(choix<1 || choix >(listeJeux.size()+1));

        // cas ou il a choisit un jeu :
        if(choix<=listeJeux.size()){
            return listeJeux.get(choix-1);
        }
        return null; //quitter
    }

    public Period SelectPeriod(){
        // menu de selection periode
        return Period.SelectPeriod();
    }

    public Joueur SelectJoueur(){
        // menu pour ajouter un joueur par le gerant
        return Joueur.NouveauJoueur();
    }

    public void AjouterReservation(){
        Joueur joueur=SelectJoueur();
        if(joueur==null){
            Print("Opération annuler."); // le cas ou le nom de joueur est vide
            return;
        }

        Jeu jeu= SelectJeu();
        if(jeu==null){
            Print("Opération annuler."); // le cas ou il a choisit quitter
            return;
        }

        Period period=SelectPeriod();

        if(period==null){
            Print("Opération annuler."); // le cas ou il a choisit quitter
            return;
        }

        Reservation reservation=new Reservation(joueur,period,jeu);
        EtatReservation etatReservation=VerifierReservation(reservation);

        switch (etatReservation){
            case ANNULER:
                Print("Reservation impossible pour aujourd'hui.");
                break;

            case EN_ATTENTE:
                if(listeAttente.size()<10) {
                    listeAttente.add(reservation);
                    Print(" -- ATTENTE" + reservation.toString());
                }else{
                    Print("Reservation annuler. liste d'attente plein");
                }
                break;

            case SUR_POSTE:
                Poste p=reservation.getPoste();
                p.setEnCours(reservation);
                Print(" -- SUR_POSTE "+reservation.toString());
                break;
        }
        // verification d'etat de resevation et asigner la la reservation sur poste ou a la liste d'attente selon le cas

    }
    public EtatReservation VerifierReservation(Reservation reservation){

        //Retourn l'etat de la reservation et assign le poste en cas de non annulation

        Calendar limitDate=Calendar.getInstance();
        limitDate.set(Calendar.HOUR_OF_DAY,20);
        limitDate.set(Calendar.MINUTE,00);

        List<Poste> postesJeu=this.GetPosteJeu(reservation.getJeu());
        Calendar MinDate;

        // Cas Poste Libre :

        for(int i=0;i<postesJeu.size();i++){
            if(!postesJeu.get(i).EstReserver()) { // poste disponible
                reservation.setPoste(postesJeu.get(i));
                reservation.setDebutUtilisation(reservation.getDateReservation());
                if(reservation.getFinUtilisation().compareTo(limitDate)<=0 ) {
                    return EtatReservation.SUR_POSTE;
                }
                else {
                    reservation.ReinitPosteEtDateDebut(); //
                }
            }
        }

        return EtatReservation.ANNULER;

    }


    public List<Poste> GetPosteJeu(Jeu jeu){
        List<Poste> postesJeu=new ArrayList<Poste>();

        for(int i=0;i<this.postes.size();i++){
            if(this.postes.get(i).ContientJeu(jeu)){
                postesJeu.add(this.postes.get(i));
            }
        }
        return postesJeu;
    }


    public void ControlePeriodique(){
        //appel de function controlePeriodique des postes et assign un joueur de la liste d'attente dans le cas
        // de liberation de poste
        for(int i=0;i<postes.size();i++){
            postes.get(i).ControlePeriodique();
        }
    }

    public float  CalculGainJournalier(int jour,int mois,int année){
        return 0.0f;
    }
    public float  CalculGainJournalierActuell(){
        return 0.0f;
    }


    public float  CalculGainMonsuelle(int mois,int année){
    return 0.0f;
    }

    public float  CalculGainMonsuelleAcuell(){
        return 0.0f;
    }

}
