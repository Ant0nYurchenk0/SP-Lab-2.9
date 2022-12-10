import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

public class App {
  public static void main(String[] args) throws Exception {
    var scanner = new Scanner(System.in);
    System.out.print("Enter data file path: ");
    var path = scanner.nextLine();
    System.out.print("Enter word length: ");
    var length = Integer.parseInt(scanner.nextLine());
    scanner.close();
    var all = FileReader.Read(path).split("\n");
    var automata = new Automata(all);
    System.out.println(checkAllWords(automata, length, automata.StartState, 0));
  }

  private static Boolean checkAllWords(Automata automata, Integer length, String currentState, Integer currentLength) {
    if (currentLength == length)
      return Arrays.asList(automata.EndStates).contains(currentState);

    var transitions = automata.Transitions.get(currentState).entrySet();
    if (transitions.size() < automata.AlphabetLength)
      return false;
    for (Entry<String, List<String>> transition : transitions) {
      for (String nextState : transition.getValue())
        if (!checkAllWords(automata, length, nextState, currentLength + 1))
          return false;
    }
    return true;
  }
}
