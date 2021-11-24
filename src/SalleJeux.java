import java.util.*;

import static Helpers.ConsoleHelper.Print;
import static Helpers.ConsoleHelper.ReadInt;
import static Helpers.ConsoleHelper.ReadString;

import java.util.Calendar;

public class SalleJeux {

    private List<Poste> postes=new ArrayList<Poste>(); // les poste de la salle des jeux
    private List<Reservation> reservations=new ArrayList<Reservation>(); // liste global des reservations
    private List<Jeu> listeJeux=new ArrayList<Jeu>(); // la liste des jeux
            // Map< key , value>> t = new HashMap()
    private Map<Poste,List<Reservation>> attente=new HashMap<>();

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


        // Initialisation des postes :
        Poste p1=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(fifa, pes));
        postes.add(p1);
                //put(key,value)
        attente.put(p1,new ArrayList<Reservation>());
        Poste p2=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(ac, pes));
        postes.add(p2);
        attente.put(p2,new ArrayList<Reservation>());
        Poste p3=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList( ac));
        postes.add(p3);
        attente.put(p3,new ArrayList<Reservation>());
        Poste p4=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(ac, cs));
        postes.add(p4);
        attente.put(p4,new ArrayList<Reservation>());
        Poste p5=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(cs, pes));
        postes.add(p5);
        attente.put(p5,new ArrayList<Reservation>());
        Poste p6=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList(cs,ac, pes));
        postes.add(p6);
        attente.put(p6,new ArrayList<Reservation>());
        Poste p7=new Poste(ConsoleJeu.XBox, Ecran.DELL, Arrays.asList( pes,fifa));
        postes.add(p7);
        attente.put(p7,new ArrayList<Reservation>());
    }

    public Jeu SelectJeu(){
        // menu de jeu
        int choix=-1;
        do {

            Print("\n------- Selection de jeu -------");

            for(int i=0;i<listeJeux.size();i++){
                Print((i+1)+". "+listeJeux.get(i).getNomJeu()+".");
            }

            Print((listeJeux.size()+1)+". Quitter.");
            choix=ReadInt("Selectionner une periode : ");

        }while(choix<1 || choix >(listeJeux.size()+1));

        if(choix<=listeJeux.size()){
            return listeJeux.get(choix-1);
        }
        return null;
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
            Print("Opération annuler.");
            return;
        }

        Jeu jeu= SelectJeu();
        if(jeu==null){
            Print("Opération annuler.");
            return;
        }

        Period period=SelectPeriod();

        if(period==null){
            Print("Opération annuler.");
            return;
        }

        Reservation reservation=new Reservation(joueur,period,jeu);
        EtatReservation etatReservation=VerifierReservation(reservation);

        switch (etatReservation){
            case ANNULER:
                Print("============================================");
                Print("| Reservation impossible pour aujourd'hui. |");
                Print("============================================");
                break;
            case EN_ATTENTE:
                Poste p=reservation.getPoste();
                if(attente.get(p).size()<10) {  //get(p) = poste -----> return valeur = liste des reservations de ce poste
                    reservations.add(reservation);
                    attente.get(p).add(reservation);

                    Print("===============================================");
                    Print("|                  EN ATTENTE                 |");
                    Print("===============================================");
                    Print( reservation.toString());

                    Print("===============================================");

                }else{
                    Print("Reservation annuler. liste d'attente plein");
                }
                break;

            case SUR_POSTE:
                reservations.add(reservation);
                reservation.getPoste().setEnCours(reservation);
                Print("===============================================");
                Print("|                   SUR POSTE                 |");
                Print("===============================================");
                Print( reservation.toString());

                Print("===============================================");
                break;
        }

    }

    // verification de la reservation -----------------------
    public EtatReservation VerifierReservation(Reservation reservation){

        //Retourn l'etat de la reservation et assign le poste en cas de non annulation

        Calendar limitDate=Calendar.getInstance();
        limitDate.set(Calendar.HOUR_OF_DAY,20);
        limitDate.set(Calendar.MINUTE,00);

        List<Poste> postesJeu=this.GetPosteJeu(reservation.getJeu());
        Calendar MinDate;
if(Calendar.getInstance().compareTo(limitDate)<0) {
    // Cas Poste Libre :

    for (int i = 0; i < postesJeu.size(); i++) {
        if (!postesJeu.get(i).EstReserver()) {
            reservation.setPoste(postesJeu.get(i));
            reservation.setDebutUtilisation(reservation.getDateReservation());
            if (reservation.getFinUtilisation().compareTo(limitDate) <= 0) {
                return EtatReservation.SUR_POSTE;
            } else {
                return EtatReservation.ANNULER;
            }
        }
    }

//------------------- MAX -----------------------------------------------------------------
    List<PosteCalendar> MaxDates = new ArrayList<>(); // liste pour stocker l max date par poste

    for (int i = 0; i < postesJeu.size(); i++) {             // key : get(postesJey.get(i)
        List<Reservation> reservationsPoste = attente.get(postesJeu.get(i)); // return liste reservation par rapport au poste


        Optional<Reservation> MaxPosteReservation = reservationsPoste.stream().max(new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                return o1.getFinUtilisation().compareTo(o2.getFinUtilisation());
            }
        });

        if (MaxPosteReservation.isPresent()) { // si le max existe(des personnes existent dans la liste
            //d'attente de ce poste


            MaxDates.add(new PosteCalendar(postesJeu.get(i), MaxPosteReservation.get().getFinUtilisation()));
        } else  // pas de personne dans la liste d'attence
        {
            MaxDates.add(new PosteCalendar(postesJeu.get(i), postesJeu.get(i).getEnCours().getFinUtilisation()));
        }
    }

//---------------------------MIN -------------------------------------------------------------

    Optional<PosteCalendar> selectedPoste = MaxDates.stream().min(new Comparator<PosteCalendar>() {
        @Override
        public int compare(PosteCalendar o1, PosteCalendar o2) {

            return o1.getDate().compareTo(o2.getDate()); //return Date MIN
        }
    });

    if (selectedPoste.isPresent()) {
        reservation.setPoste(selectedPoste.get().getPoste());
        reservation.setDebutUtilisation(selectedPoste.get().getDate()); // date debut d'utilisation
        if (reservation.getFinUtilisation().compareTo(limitDate) <= 0) {  // cas ou la periode chosisit < = 20h
            return EtatReservation.EN_ATTENTE;
        }
    }
}

        return EtatReservation.ANNULER;
    }
    //-----------------------------------------------------------------------------------------

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
            if(postes.get(i).ControlePeriodique()){
                //prendre une reservation de la liste d'attente et la mettre encours sur le poste et la supprimer de la liste
            }
        }
    }

    public float  CalculGainJournalier(){
        int jour;
        int mois;
        int annee;

        Print("\n--------- Calcule journalier ------------------- : ");
        jour=ReadInt("Entrez un jour : ");
        mois=ReadInt("Entrez un mois : ");
        annee=ReadInt("Entrez une année : ");

        float Somme=0;

        for (int i=0; i<reservations.size();i++ ) {
          Calendar date=reservations.get(i).getDateReservation();
        if(date.get(Calendar.DAY_OF_MONTH)==jour && date.get(Calendar.MONTH)+1==mois && date.get(Calendar.YEAR)==annee){

            Somme+=reservations.get(i).getPeriod().getPrix();
        }
        }
        Print("Le montant total du jour ("+jour+"/"+mois+"/"+annee+") est : "+Somme+" DH.");
        return Somme;
    }



    public float  CalculGainMonsuelle(){

        int mois;
        int annee;

        Print("\n--------- Calcule Mensuelle ------------------- : ");

        mois=ReadInt("Entrez un mois : ");
        annee=ReadInt("Entrez une année : ");

        float Somme=0;

        for (int i=0; i<reservations.size();i++ ) {
            Calendar date=reservations.get(i).getDateReservation();
            if (date.get(Calendar.MONTH)+1==mois && date.get(Calendar.YEAR)==annee){
                Somme+=reservations.get(i).getPeriod().getPrix();}
        }

        Print("Le montant total du mois ("+mois+"/"+annee+") est : "+Somme+" DH.");
        return Somme;
    }



}
