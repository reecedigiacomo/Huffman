import java.util.Comparator;

public class ByFreq implements Comparator<ATree> {
  public int compare(ATree o1, ATree o2) {
    return Integer.compare(o1.freq, o2.freq);
  }
}