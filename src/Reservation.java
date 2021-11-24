import java.util.Calendar;

import static Helpers.ConsoleHelper.padRight;

public class Reservation  {
    private Joueur joueur;
    private Period period;
    private Jeu jeu;
    private Calendar dateRservation;
    private Calendar debutUtilisation,finUtilisation;
    private Poste poste;

    // Constructeur :
    public Reservation(Joueur joueur,Period period,Jeu jeu){
        this.joueur=joueur;
        this.period=period;
        this.jeu=jeu;
        this.dateRservation=Calendar.getInstance();
        this.poste=null;
        this.debutUtilisation=null;
        this.finUtilisation=null;
    }
    public Period getPeriod(){

        return this.period;
    }

    public void setDebutUtilisation(Calendar date){
        this.debutUtilisation=date;
        this.CalculeDateFinUtilisation();
    }

    public Calendar getDateReservation() {

        return this.dateRservation;
    }
    public Calendar getDebutUtilisation() {

        return this.debutUtilisation;
    }
    public Calendar getFinUtilisation() {

        return this.finUtilisation;
    }

    public  void setPoste(Poste poste){

        this.poste=poste;
    }

    public Poste getPoste(){

        return this.poste;
    }

    public Jeu getJeu(){


        return this.jeu;
    }
    public void CalculeDateFinUtilisation(){

        //this.debutUtilisation=Calendar.getInstance(); //creer une instance de type calendrier avec le temps actuel
        this.finUtilisation=(Calendar)  this.debutUtilisation.clone();

        switch (this.period){  //period d'utilisation du poste par l'utilis qui vient de reserver

            case Min30:

                this.finUtilisation.add(Calendar.MINUTE, 30); // date de fin d'utulisation du poste

                //  30min  date occup : 11h45 ---> date fin 12h15  reste 15min
                if(this.debutUtilisation.get(Calendar.HOUR_OF_DAY)<12 && this.finUtilisation.get(Calendar.HOUR_OF_DAY)>=12 ){
                    this.finUtilisation.add(Calendar.HOUR, 2); // 12h15 + 2h = 14h15
                }
                break;

            case Heure1:

                this.finUtilisation.add(Calendar.HOUR, 1); // date de fin d'utulisation du poste


                if(this.debutUtilisation.get(Calendar.HOUR_OF_DAY)<12 && this.finUtilisation.get(Calendar.HOUR_OF_DAY)>=12 ){
                    this.finUtilisation.add(Calendar.HOUR, 2);
                }
                break;

            case Heure2:

                this.finUtilisation.add(Calendar.HOUR, 2); // date de fin d'utulisation du poste


                if(this.debutUtilisation.get(Calendar.HOUR_OF_DAY)<12 && this.finUtilisation.get(Calendar.HOUR_OF_DAY)>=12 ){
                    this.finUtilisation.add(Calendar.HOUR, 2);
                }
                break;

            case Heure5:

                this.finUtilisation.add(Calendar.HOUR, 5); // date de fin d'utulisation du poste


                if(this.debutUtilisation.get(Calendar.HOUR_OF_DAY)<12 && this.finUtilisation.get(Calendar.HOUR_OF_DAY)>=12 ){
                    this.finUtilisation.add(Calendar.HOUR, 2);
                }
                break;

            case Journee:
                this.finUtilisation.set(Calendar.HOUR_OF_DAY,20);
                this.finUtilisation.set(Calendar.MINUTE,0);
                break;
        }

    }




    @Override
    public String toString() {
        return
                "| "+padRight("joueur      = " + joueur,43) +" |\n"+
                        "| "+padRight("period      = " + period ,43) +" |\n"+
                        "| "+padRight("jeu         = " + jeu.getNomJeu(),43) +" |\n"+
                        "| "+padRight("Reservation = " + dateRservation.getTime() ,43) +" |\n"+
                        "| "+padRight("Debut       = " + debutUtilisation.getTime(),43) +" |\n"+
                        "| "+padRight("Fin         = " + finUtilisation.getTime(),43) +" |\n"+
                        "| "+padRight("Poste       = " + poste,43) +" |" ;
    }
}
