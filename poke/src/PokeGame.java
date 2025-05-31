import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PokeGame {

    private int p1_wins = 0;
    private int p2_wins = 0;
    
    public void play(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse, create hands, compare, update scores
                p1_wins += 1;
                p2_wins += 2;
            }
        }
        printResults();
    }
    
    private void printResults() {
        System.out.println("Player 1: " + p1_wins + " hands");
        System.out.println("Player 2: " + p2_wins + " hands");
    }
}