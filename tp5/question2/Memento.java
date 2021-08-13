package question2;
import java.util.List;


/**
 * Write a description of class Memento here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Memento
{
    private List<String> state;
    
    public Memento(List<String> state){
        this.state = state;
    }
    
    public List<String> getState(){
        return state;
    }
}
