import java.util.Calendar;
import java.util.List;

import static Helpers.ConsoleHelper.Print;


public class Poste {
      static int IDPOSTES=1;
      int id;
      private ConsoleJeu console;
      private Ecran ecran;
      private List<Jeu> jeux;
      private Reservation enCours;
      private boolean allumer;

  // Consructeur :
  public Poste(ConsoleJeu console,Ecran ecran,List<Jeu> jeux){
      this.id=Poste.IDPOSTES;
      Poste.IDPOSTES++;

    this.console=console;
    this.ecran=ecran;
    this.jeux=jeux;
    this.enCours=null;
    this.allumer=false;

  }

  // methods :

    //fonction qui retourn si ce poste est en cours d'utilisation :
    public boolean EstReserver(){

      return this.enCours!=null;
    }

    public void setEnCours(Reservation reservation){  // setter de l'attribut EnCours (Reservation)
      this.enCours=reservation;
      this.Allumer();
    }

      public Reservation getEnCours() {

      return enCours;
      }

// Controller la periode de fin d'utilisation de poste :

   public boolean ControlePeriodique(){
      if(this.EstReserver()){
          Calendar Maintenant=Calendar.getInstance(); // get la date et heure actuelle

             // Tester la date actuelle > date de fin  11h > 9h(date fin)
          if(Maintenant.get(Calendar.HOUR_OF_DAY)>= this.enCours.getFinUtilisation().get(Calendar.HOUR_OF_DAY) &&
          Maintenant.get(Calendar.MINUTE)>=this.enCours.getFinUtilisation().get(Calendar.MINUTE)){
              this.Libirer();
              return true;
          }
      }
      return false;
}

public void Libirer(){ // setter de l'attribut EnCours (NULL)
    this.enCours=null;
    this.Etteindre();
}
  public void Allumer(){  // message d'affichage
        this.allumer=true;
      //Print("===============================================");
      //Print("|           AFFECTATION SUR POSTE             |");
      //Print("===============================================");
      //Print( this.enCours.toString());

      //Print("===============================================");
}
public void Etteindre(){ // message d'affichage

      this.allumer=false;
}

public boolean ContientJeu(Jeu jeu){
      for(int i=0;i<this.jeux.size();i++){
          if(this.jeux.get(i).equals(jeu)){
              return true;
          }
      }
      return false;
}
public String toString(){
      return String.valueOf(this.id);
}

/*@Override
      public String toString() {
          return "Poste{" +
                  "id=" + id +
                  ", console=" + console +
                  ", ecran=" + ecran +
                  ", jeux=" + jeux +
                  ", enCours=" + enCours +
                  ", allumer=" + allumer +
                  '}';
      }*/
  }
