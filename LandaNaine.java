
public class LandaNaine {
    public static void main(String[] args) {
        String[] lyrics = {
            "\n\t\t\t\t..Lesa tangila...",
            "\n\t\t\t\t...Naine Nkonkemooo",
            "\n\t\t\t\t(wewishibe eko tuleya)",
            "\n\t\t\t\t...Lesa Yambako...",
            "\n\t\t\t\t...naine nkonkemo...",
            "\n\t\t\t\t...Ngatawa ende...'",
            "\n\t\t\t\t...(Ngatawa ende)...",
            "\n\t\t\t\t...Naine Nsha ende...",
            "\n\t\t\t\t...Ngatwasele...",
            "\n\t\t\t\t(Naine Nshasele)",
            "\n\t\t\t\t...Naine Nshasele...'",
            "\n\t\t\t\t...Lesa landa Naine ndandeko'(Lesa Ndandila)... ",
            "\n\t\t\t\t...Lesa landa Naine Ah-ndandeko",
            "\n\t\t\t\t...Nga tawalande '(Lesa Alandeeee... )",
            "\n\t\t\t\t...Nga tawalande, Naine Nshalande....",
            "\n\t\t\t\t...Ngatawalande...",
            "\n\t\t\t\t...Naine nshalande...Eh ehee>>>>>>>>>>",
            "",
            
        };

        int [] delays = {
            0, 
            2200,
            1400,
            1000,
            3300, 
            1900, 
            2200,
            1700, 
            1200, 
            2100, 
            1400, 
            1800,
            2200, 
            3200, 
            2500, 
            2100,
            4800,
            5000,
        };

        try {
            for (int i = 0; i < lyrics.length; i++) {
                Thread.sleep(delays[i]);
                System.out.println(lyrics[i]);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
