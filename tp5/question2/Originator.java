package question2;
import java.util.List;


/**
 * Write a description of class Originator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Originator
{
    private List<String> state;
    
    public void setState(List<String> state){
        this.state = state;
    }
    
    public Memento saveStateToMemento(){
        return new Memento(state);
    }
    
    public List<String> restoreStateFromMemento(Memento memento){
        this.state = memento.getState();
        return state;
    }
    
}
