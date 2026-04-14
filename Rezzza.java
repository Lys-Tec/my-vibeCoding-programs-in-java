public class Rezzza{
    public static void main(String args[]){
        String[] lyrics= {
            "\n\t\t\t\t...Reza Guys...",
            "\n\t\t\t\t...Rezaaaaaa!..",
            "\n\t\t\t\t...Reza Guys...",
            "\n\t\t\t\t...Rezaaaaaa!..",
            "\n\t\t\t\t...YOH YOH YOH YOH YOH...",
            "\n\t\t\t\t...Pubric..",
            "\n\t\t\t\t...pubric...",
            "\n\t\t\t\t...pubric...",
            "\n\t\t\t\t...pubriki...",
            "\n\t\t\t\t...In-Zambia...",
            "\n\t\t\t\t...zambia..",
            "\n\t\t\t\t...zambia...",
            "\n\t\t\t\t...zambia!..",
            "\n",
            "\n",

        };
        int [] delays = {
            0,
            1000,
            1500,
            1000,
            1500,
            1800,
            450,
            450,
            450,
            510,
            780,
            450,
            450,
            450,

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