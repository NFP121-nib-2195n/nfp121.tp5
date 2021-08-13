package question2;
import java.util.Stack;


/**
 * Write a description of class CareTaker here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CareTaker
{
    private Stack<Memento> mementoStack = new Stack<Memento>();
    
    public void addMemento(Memento m){ mementoStack.push(m); }
    
    public Memento getMemento(){ return mementoStack.pop();}
    
    public Memento peekMemento(){ return mementoStack.peek(); }
    
    public boolean isEmpty(){ return mementoStack.empty(); }
}
