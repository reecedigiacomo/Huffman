import java.util.ArrayList;

public class Forest extends ATree {
  private final ATree left;
  private final ATree right;

  Forest(ATree left, ATree right) {
    this.left = (left.freq <= right.freq) ? left : right;
    this.right = (right.freq <= left.freq) ? right : left;
    this.freq = left.freq + right.freq;
  }

  public ArrayList<Boolean> encode(String letter, ArrayList<Boolean> temp) {
    if (this.left.here(letter)) {
      temp.add(false);
      return this.left.encode(letter, temp);
    } else if (this.right.here(letter)) {
      temp.add(true);
      return this.right.encode(letter, temp);
    }
    throw new IllegalArgumentException("Tried to encode " + letter + " but that is not part of the language.");
  }

  public String decode(ArrayList<Boolean> code, ATree start) {
    if (code.isEmpty()) {
      return "?";
    } else if (!code.get(0)) {
      code.remove(0);
      return this.left.decode(code, start);
    }
    code.remove(0);
    return this.right.decode(code, start);
  }

  public boolean here(String letter) {
    return this.left.here(letter) || this.right.here(letter);
  }
}