import java.util.ArrayList;

public class Huffman {
  protected ArrayList<ATree> trees = new ArrayList<>();

  public Huffman(ArrayList<String> stringList, ArrayList<Integer> intList) {
    if (stringList.size() < 2) {
      throw new IllegalArgumentException("string list must have two or more items");
    } else if (stringList.size() != intList.size()) {
      throw new IllegalArgumentException("both lists must be equal in size");
    }
    this.trees = this.buildList(stringList, intList);
    this.buildTree();
  }

  private ArrayList<ATree> buildList(ArrayList<String> stringList, ArrayList<Integer> intList) {
    if (!stringList.isEmpty() && !intList.isEmpty()) {
      trees.add(new Leaf(stringList.get(0), intList.get(0)));
      stringList.remove(0);
      intList.remove(0);
      return this.buildList(stringList, intList);
    }
    return trees;
  }

  private void sort() {
    this.trees.sort(new ByFreq());
  }

  private void insert(ATree tree, int temp) {
    if (temp + 1 == this.trees.size()) {
      this.trees.add(tree);
    } else if (trees.get(temp).freq <= tree.freq) {
      this.insert(tree, temp + 1);
    } else {
      this.trees.add(temp, tree);
    }
  }

  public void buildTree() {
    this.sort();
    while (this.trees.size() > 1) {
      ATree first = this.trees.get(0);
      ATree second = this.trees.get(1);
      this.insert(new Forest(first, second), 0);
      this.trees.remove(0);
      this.trees.remove(0);
    }
  }

  public ArrayList<Boolean> encode(String word) {
    ArrayList<Boolean> fin = new ArrayList<>();
    for (int i = 0; i < word.length(); i++) {
      if (!Character.isLetter(word.charAt(i))) {
        throw new IllegalArgumentException("Tried to encode " + word.charAt(i) + " but that is not part of the language.");
      } else {
        fin.addAll(this.trees.get(0).encode(word.substring(i, i + 1), new ArrayList<>()));
      }
    }
    return fin;
  }

  public String decode(ArrayList<Boolean> code) {
    StringBuilder word = new StringBuilder();
    while (!code.isEmpty()) {
      word.append(this.trees.get(0).decode(code, this.trees.get(0)));
    }
    return word.toString();
  }
}