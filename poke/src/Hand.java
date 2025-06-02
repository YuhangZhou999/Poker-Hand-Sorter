import java.util.*;

public class Hand implements Comparable<Hand>{
    private List<Card> cards;
    private Integer handrank;
    private List<Integer> uni_values;
    
    public Hand(List<Card> cards) {
        this.cards = new ArrayList<Card>(cards);
        Collections.sort(this.cards, Collections.reverseOrder());
        HandResult result = Evaluate();
        this.handrank = result.rank;
        this.uni_values = result.highcard_list;
    }
    
    // use this function to parse hand rank and value
    private HandResult Evaluate() {
        Map<Integer, Integer> num_counts = new HashMap<>();
        boolean is_flush = true;
        boolean is_straight = true;
        
        // count cards nums
        char suit = this.cards.get(0).getSuit();
        for (Card card : this.cards) {
            // check whether flush
            if (card.getSuit() != suit) {
                is_flush = false;
            }
            num_counts.put(card.getValue(), num_counts.getOrDefault(card.getValue(), 0) + 1);
        }
        
        // check whether straight
        int prev_value = 0;
        for (Card card : this.cards) {
            if (prev_value == 0) {
                prev_value = card.getValue();
                continue;
            } else if (prev_value - 1 != card.getValue()) {
                is_straight = false;
                break;
            } else {
                prev_value = card.getValue();
            }
        }
        
        // reverse sort num_count with first key count number, second key card value (num_count key value)
        List<Integer> sorted_keys = new ArrayList<>(num_counts.keySet());
        sorted_keys.sort((a, b) -> {
            int count_cmp = num_counts.get(b).compareTo(num_counts.get(a));
            if (count_cmp == 0) {
                return b - a;
            }
            return count_cmp;
        });
        
        // Royal Flush
        if (is_flush && is_straight && sorted_keys.get(0) == 14) {
            return new HandResult(10, sorted_keys);
        }
        // Straight flush
        if (is_flush && is_straight) {
            return new HandResult(9, sorted_keys);
        }
        // Four of a kind
        if (num_counts.containsValue(4)) {
            return new HandResult(8, sorted_keys);
        }
        // Full house
        if (num_counts.containsValue(3) && num_counts.containsValue(2)) {
            return new HandResult(7, sorted_keys);
        }
        // Flush
        if (is_flush) {
            return new HandResult(6, sorted_keys);
        }
        // Straight
        if (is_straight) {
            return new HandResult(5, sorted_keys);
        }
        // Three of a kind
        if (num_counts.containsValue(3)) {
            return new HandResult(4, sorted_keys);
        }
        // two pair and pair
        int pairCount = Collections.frequency(new ArrayList<>(num_counts.values()), 2);
        if (pairCount == 2) {
            return new HandResult(3, sorted_keys);   
        }
        if (pairCount == 1) {
            return new HandResult(2, sorted_keys);  
        }
        // High card
        return new HandResult(1, sorted_keys);
        
    }
    
    // compare hand, first key rank, second key sorted card value
    @Override
    public int compareTo(Hand o) {
        // different rank, return the compare result
        if (this.handrank != o.handrank) {
            return this.handrank - o.handrank;
        }
        // same rank, compare the uni_values
        int tsize = this.uni_values.size();
        int osize = o.uni_values.size();
        int cmp_result = 0;
        for (int i = 0; i < tsize; i++) {
            if (i >= osize) return 1;
            cmp_result = this.uni_values.get(i) - o.uni_values.get(i);
            if (cmp_result != 0) return cmp_result;
        }
        return cmp_result;
    }
    
    
    private class HandResult {
        Integer rank;
        List<Integer> highcard_list;
        
        HandResult(Integer rank_in, List<Integer> highcard_list_in) {
            this.rank = rank_in;
            this.highcard_list = highcard_list_in;
        }
    }
    
}
