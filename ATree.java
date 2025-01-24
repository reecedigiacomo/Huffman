import java.util.ArrayList;

public abstract class ATree {
  protected int freq;

  ATree(int freq) {
    this.freq = freq;
  }

  ATree() {}

  public ArrayList<Boolean> encode(String letter, ArrayList<Boolean> temp) {
    return new ArrayList<>();
  }

  public boolean here(String letter) {
    return false;
  }

  public String decode(ArrayList<Boolean> temp, ATree start) {
    return "";
  }
}