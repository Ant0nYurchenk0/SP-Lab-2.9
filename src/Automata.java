import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Automata implements Serializable{
  public Integer AlphabetLength;
  public Integer NumOfStates;
  public String StartState;
  public String[] EndStates;
  public Integer NumOfEndStates;
  public Map<String, Map<String, List<String>>> Transitions;

  public Automata(String[] all){
    AlphabetLength = Integer.parseInt(all[0]);
    NumOfStates = Integer.parseInt(all[1]);
    StartState = all[2];
    EndStates = all[3].split(" ");
    NumOfEndStates = Integer.parseInt(EndStates[0]);
    EndStates = Arrays.copyOfRange(EndStates, 1, EndStates.length);
    
    Transitions = new HashMap<>();
    for (var i = 4; i < all.length; i++){
      var trio = all[i].split(" ");
      var initState = trio[0];
      var symbol = trio[1];
      var finalState = trio[2];
      if(!Transitions.containsKey(initState))
        Transitions.put(initState, new HashMap<>());
      if(!Transitions.get(initState).containsKey(symbol))
        Transitions.get(initState).put(symbol, new ArrayList<>());
      Transitions.get(initState).get(symbol).add(finalState);
    }
  }
  public Automata(){ }

  @Override
  public String toString(){
    String result = AlphabetLength + "\n" 
                    + NumOfStates + "\n"
                    + StartState + "\n"
                    + NumOfEndStates + " " + String.join(" ", EndStates) + "\n";
    for (Entry<String, Map<String, List<String>>> transition : Transitions.entrySet()) {
      for (Entry<String, List<String>> entry : transition.getValue().entrySet()) {
        for (String state : entry.getValue()) {
          result += transition.getKey() + " " +  entry.getKey() + " " + state + "\n";          
        }
      }
    }    
    return result;                
  }
}