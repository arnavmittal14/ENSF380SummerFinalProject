package edu.ucalgary.ensf380;

import java.util.Locale;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class AnnouncementService {
    private Voice voice;

    public AnnouncementService2() {
        System.setProperty("freetts.voices", 
            "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
    }

    public void announce(String message) {
        if (voice != null) {
            voice.speak(message);
        }
    }

    public void announceNextStation(Station2 station) {
        String message = "Next stop: " + station.getName();
        if (!station.getCommonLines().isEmpty()) {
            message += ", you can change your train to line " + station.getCommonLines();
        }
        announce(message);
    }
}
