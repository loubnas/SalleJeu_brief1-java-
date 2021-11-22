import java.util.TimerTask;

public class VerificationTimer extends TimerTask {
    private SalleJeux salleJeux;

    //constructeur :
    public VerificationTimer(SalleJeux salleJeux) {

        this.salleJeux=salleJeux;
    }

    @Override
    public void run() {

        this.salleJeux.ControlePeriodique();
    }
}
