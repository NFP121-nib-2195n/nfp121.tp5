package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Stack;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener {

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    
    private Originator originator;
    private CareTaker caretaker;
    
    public JPanelListe2(List<String> liste,Map<String,Integer> occurrences){
        this.liste = liste;
        this.occurrences = occurrences;
        
        originator = new Originator();
        caretaker = new CareTaker();

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonRetirer.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        boutonAnnuler.addActionListener(this);
        
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);   

    }

    
    public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "
                    + saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                List<String> li_save = new ArrayList<String>(liste);
                
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie
                    .getText());
                
                if(res) {
                    originator.setState(li_save);
                    caretaker.addMemento(originator.saveStateToMemento());
                }
                
                afficheur
                .setText("résultat du retrait de tous les éléments commençant par -->  "
                    + saisie.getText() + " : " + res);
                    
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else
                    afficheur.setText(" -->  ??? ");
            } else if(ae.getSource() == boutonAnnuler){
                if(!caretaker.isEmpty()){
                    this.liste = originator.restoreStateFromMemento(caretaker.getMemento());
                    occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
                }
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        
        List<String> liste_save = new ArrayList<String>(liste);
        
        if (ie.getSource() == ordreCroissant){
            originator.setState(liste_save);
            caretaker.addMemento(originator.saveStateToMemento());
                
            Collections.sort(liste);
        }
        else if (ie.getSource() == ordreDecroissant){
            originator.setState(liste_save);
            caretaker.addMemento(originator.saveStateToMemento());
                
            Collections.sort(liste, Collections.reverseOrder());
        }
        
        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
        
        List<String> li = this.liste;
        Iterator<String> it = li.iterator();
        while(it.hasNext()){
            String str = it.next();
            if(str.startsWith(prefixe)){
                resultat = true;
                it.remove();
                occurrences.put(str,0);
            }
        }
        return resultat;
    }

}