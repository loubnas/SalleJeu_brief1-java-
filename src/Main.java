import java.util.Timer;
import static Helpers.ConsoleHelper.*;
public class Main {
    public static void main(String[] args) {

        SalleJeux salleJeux=new SalleJeux();
        VerificationTimer controlePeriodique=new VerificationTimer(salleJeux);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(controlePeriodique, 0, 60*1000); //1min

        //boucle global et menu principal (ajouter resevation , calcul des gains journalier et monsuelle , quitter)
        int choix=-1;
        do{
            Print("----------- Menu Principale -----------");
            Print("1: Ajouter une Reservation.");
            Print("2: Statistiques");
            Print("3: Quitter.");
            choix=ReadInt("Veuillez Choisir une option : ");
            switch (choix){
                case 1: salleJeux.AjouterReservation();
                break;
                case 2:
                break;
            }

        }while(choix!=3);
        timer.cancel();
    }
}
