  import sun.applet.Main;

  import java.util.Calendar;
  import java.util.Date;
  import java.util.List;
  public class Poste {

      private ConsoleJeu console;
      private Ecran ecran;
      private List<Jeu> jeux;
      private Reservation enCours;
      private boolean allumer;
      private Calendar debutUtilisation,finUtilisation;

  // Consructeur :
  public Poste(ConsoleJeu console,Ecran ecran,List<Jeu> jeux){
    this.console=console;
    this.ecran=ecran;
    this.jeux=jeux;
    this.enCours=null;
    this.allumer=false;
    this.debutUtilisation=null;
    this.finUtilisation=null;
  }

  // methods :
public boolean EstReserver(){ // retourn si ce poste est en cours d'utilisation

      return this.enCours!=null;
}
public void Reserver(Reservation reservation){  // setter de l'attribut EnCours (Reservation)
      this.enCours=reservation;


      this.debutUtilisation=Calendar.getInstance(); //creer une instance de type calendrier avec le temps actuel
      this.finUtilisation=Calendar.getInstance();

      switch (this.enCours.getPeriod()){  //period d'utilisation du poste par l'utilis qui vient de reserver
          case Min30:
              this.finUtilisation=Calendar.getInstance();
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
      this.Allumer();
}
   // Controller la periode de fin d'utilisation de poste :
   public void ControlePeriodique(){
      if(this.EstReserver()){
          Calendar Maintenant=Calendar.getInstance(); // get la date et heure actuelle

             // Tester la date actuelle > date de fin  11h > 9h(date fin)
          if(Maintenant.get(Calendar.HOUR_OF_DAY)>= this.finUtilisation.get(Calendar.HOUR_OF_DAY) &&
          Maintenant.get(Calendar.MINUTE)>=this.finUtilisation.get(Calendar.MINUTE)){
              this.Libirer();
          }
      }
}

  public void Libirer(){ // setter de l'attribut EnCours (NULL)
    this.enCours=null;
    this.debutUtilisation=null;
    this.Etteindre();
}
  public void Allumer(){  // message d'affichage

      this.allumer=true;
}
public void Etteindre(){ // message d'affichage

      this.allumer=false;
}



}
