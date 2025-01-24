import java.util.ArrayList;

public class Leaf extends ATree {
  private final String letter;

  Leaf(String letter, int freq) {
    super(freq);
    this.letter = letter;
  }

  public ArrayList<Boolean> encode(String l, ArrayList<Boolean> temp) {
    return (this.letter.equals(l)) ? temp : new ArrayList<>();
  }

  public String decode(ArrayList<Boolean> temp, ATree start) {
    return (temp.isEmpty()) ? this.letter : this.letter + start.decode(temp, start);
  }

  public boolean here(String l) {
    return this.letter.equals(l);
  }
}