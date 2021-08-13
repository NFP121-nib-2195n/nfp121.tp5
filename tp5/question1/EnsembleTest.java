package question1;

public class EnsembleTest extends junit.framework.TestCase {

    public void testUnion() {
        question1.Ensemble<Integer> e1, e2;
        e1 = new question1.Ensemble<Integer>();
        
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));

        e2 = new question1.Ensemble<Integer>();
        assertEquals(true, e2.add(3));
        assertEquals(true, e2.add(4));

        question1.Ensemble<Integer> union = e1.union(e2);
        
        assertEquals(3, union.size());
        
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    

    public void testAdd()
    {
        question1.Ensemble<String> ensemble2 = new question1.Ensemble<String>();
        assertEquals(true, ensemble2.add("testElement1"));
        assertEquals(true, ensemble2.add("testElement2"));
        assertEquals(true, ensemble2.add(null));
        
        question1.Ensemble<String> ensemble1 = new question1.Ensemble<String>();
        assertEquals(true, ensemble1.add("testElement3"));
        
        assertEquals(3, ensemble2.size());
        assertEquals(1, ensemble1.size());
        
        assertEquals(true, ensemble2.addAll(ensemble1));
        assertEquals(4, ensemble2.size());
        assertEquals(1, ensemble1.size());
        
        assertEquals(true, ensemble2.contains("testElement3"));
        assertEquals(true, ensemble2.contains("testElement1"));
    }

    public void testInter()
    {
        question1.Ensemble<Integer> ensemble1 = new question1.Ensemble<Integer>();
        question1.Ensemble<Integer> ensemble2 = new question1.Ensemble<Integer>();
        
        assertEquals(true, ensemble1.add(1));
        assertEquals(true, ensemble1.add(2));
        
        assertEquals(true, ensemble2.add(3));
        assertEquals(true, ensemble2.add(1));
        
        
        question1.Ensemble<Integer> inter = ensemble1.inter(ensemble2);
        assertEquals(1, inter.size());
        assertEquals(true, inter.contains(1));
        assertEquals(false, inter.contains(2));
    }


    public void testDiff()
    {
        question1.Ensemble<Integer> ensemble1 = new question1.Ensemble<Integer>();
        question1.Ensemble<Integer> ensemble2 = new question1.Ensemble<Integer>();
        
        assertEquals(true, ensemble1.add(1));
        assertEquals(true, ensemble1.add(2));
        assertEquals(true, ensemble2.add(1));
        assertEquals(true, ensemble2.add(3));
        
        question1.Ensemble<java.lang.Integer> diff = (question1.Ensemble<java.lang.Integer>)ensemble1.diff(ensemble2);
        
        assertEquals(1, diff.size());
        assertEquals(false, diff.contains(1));
        assertEquals(true, diff.contains(2));
    }

    public void testDiffS()
    {
        question1.Ensemble<Integer> ensemble1 = new question1.Ensemble<Integer>();
        question1.Ensemble<Integer> ensemble2 = new question1.Ensemble<Integer>();
        
        assertEquals(true, ensemble1.add(1));
        assertEquals(true, ensemble1.add(2));
        
        assertEquals(true, ensemble2.add(1));
        assertEquals(true, ensemble2.add(3));
        
        question1.Ensemble<java.lang.Integer> diffS = (question1.Ensemble<java.lang.Integer>)ensemble1.diffSym(ensemble2);
        
        assertEquals(2, diffS.size());
        assertEquals(true, diffS.contains(2));
        assertEquals(true, diffS.contains(3));
    }
}




