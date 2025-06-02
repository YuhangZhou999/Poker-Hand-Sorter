import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PokeGame {

    private int p1_wins = 0;
    private int p2_wins = 0;
    
    public void play(InputStream input) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] cards = line.trim().split(" ");
                if (cards.length != 10) {
                    continue;
                }
                
                List<Card> p1cards = new ArrayList<>();
                List<Card> p2cards = new ArrayList<>();
                
                for (int i = 0; i < 5; i++) {
                    p1cards.add(new Card(cards[i]));
                }
                for (int i = 5; i < 10; i++) {
                    p2cards.add(new Card(cards[i]));
                }
                
                Hand p1hand = new Hand(p1cards);
                Hand p2hand = new Hand(p2cards);
                
                if (p1hand.compareTo(p2hand) > 0) {
                    p1_wins++;
                } else if (p1hand.compareTo(p2hand) < 0) {
                    p2_wins++;
                }
            }
        }
        printResults();
    }
    
    private void printResults() {
        System.out.println("Player 1: " + p1_wins + " hands");
        System.out.println("Player 2: " + p2_wins + " hands");
    }
}