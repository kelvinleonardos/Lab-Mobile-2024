package Utils;
import java.time.LocalDateTime;
public class Time {

    public static String getDayTime() {
        LocalDateTime now = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            now = LocalDateTime.now();
        }

        int jam = 0;
        String waktu = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            jam = now.getHour();
        }

        if (jam >= 0 && jam < 12) {
            waktu = "Pagi";
        } else if (jam >= 12 && jam < 15) {
            waktu = "Siang";
        } else if (jam >= 15 && jam < 19) {
            waktu = "Sore";
        } else if (jam >= 19 && jam < 24) {
            waktu = "Malam";
        }

        return waktu;
    }

}
