public class Card implements Comparable<Card>{
    private int value;
    private char suit;
    
    public Card(String vaule_suit) {
        char raw_value = vaule_suit.charAt(0);
        this.suit = vaule_suit.charAt(1);
        
        switch (raw_value) {
            case 'T': this.value = 10; break;
            case 'J': this.value = 11; break;
            case 'Q': this.value = 12; break;
            case 'K': this.value = 13; break;
            case 'A': this.value = 14; break;
        
            default: this.value = Character.getNumericValue(raw_value); break;
        }
    }
    
    public int getRawValue() {
        return value;
    }
    
    public char getSuit() {
        return suit;
    }
    
    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.value, o.value);
    }
    
}
