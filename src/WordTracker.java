/**
 * Created by Perry on 1/9/2017.
 */
public class WordTracker {
    private String word, soFar;
    private int incorrect;
    private final int maxErrors = 7;

    public WordTracker(String word) {
        this.word = word;
        this.soFar = createSoFar();
        this.incorrect = 0;
    }

    public String getSoFar() {return this.soFar;}
    public String getSoFar(int i) {return getSoFar().substring(i, i+1);}
    public String getWord() {return this.word;}
    public void increaseIncorrect() {this.incorrect++;}

    public String createSoFar() {
        String blank = "";
        for(int i = 0; i < this.word.length(); i++)
            blank += "-";

        return blank;
    }

    public boolean gameContinues() {return !win() && !lose();}

    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int isCorrect(String guess) {
        int index = -1;
        for(int i = 0; i < getWord().length(); i++) {
            String sub = getWord().substring(i, i+1);
            if(guess.equals(sub)) {
                index = i;
                updateSoFar(guess, i);
            }
        }
        return index;
    }

    public boolean repeatedGuess(String guess) {
        for(int i = 0; i < getWord().length(); i++) {
            if(getSoFar(i).equals(guess)) {
                return true;
            }
        }
        return false;
    }

    public void updateSoFar(String guess, int index) {
        String updated = "";
        for(int i = 0; i < getSoFar().length(); i++) {
            if(i != index) {
                updated += getSoFar().substring(i, i+1);
            } else {
                updated += guess;
            }
        }
        this.soFar = updated;
    }

    public boolean win() {
        return getSoFar().equals(this.getWord());
    }

    public boolean lose() {
        return this.incorrect == maxErrors;
    }

    public void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
