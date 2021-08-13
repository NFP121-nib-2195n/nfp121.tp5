package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    public boolean add(T t) {
        return table.add(t);
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        Ensemble<T> union = new Ensemble<T>();
        union.addAll(this);
        union.addAll(e);
        union.removeAll(inter(e));
        return union;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        Ensemble<T> inter = new Ensemble<T>();
        inter.addAll(this);
        inter.retainAll(e);
        return inter;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        Ensemble<T> diff = new Ensemble<T>();
        diff.addAll(this);
        diff.removeAll(e);
        return diff;
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) {
        Ensemble<T> diffS = new Ensemble<T>();
        diffS.addAll(union(e));
        diffS.removeAll(inter(e));
        return diffS;
    }
    
}
