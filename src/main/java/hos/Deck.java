package hos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Deck {
    private Boolean shuffled;
    private String deck_id ;
    private Boolean success;
    private int remaining;
    private Card cards[];
    public Boolean getShuffled() {
        return shuffled;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getRemaining() {
        return remaining;
    }

    public String getDeck_id() {
        return deck_id;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void setDeck_id(String deck_id) {
        this.deck_id = deck_id;
    }

    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }
}
