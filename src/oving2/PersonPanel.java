package oving2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author lycantrophe
 */
public class PersonPanel extends JPanel {

    Person person;
    javax.swing.JTextField NamePropertyComponent;
    javax.swing.JTextField EmailPropertyComponent;
    javax.swing.JTextField DateOfBirthPropertyComponent;
    javax.swing.JComboBox GenderPropertyComponent;
    javax.swing.JSlider HeightPropertyComponent;
    
    public PersonPanel() {

       // JPanel pane = new JPanel(new GridBagLayout());
        //JPanel pane = new JPanel();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        myChangeListener cl = new myChangeListener();
        myActionListener ae = new myActionListener();
        
        person = new Person("Dummy");

        NamePropertyComponent = new javax.swing.JTextField("", 50);
        NamePropertyComponent.addActionListener(ae);
        NamePropertyComponent.setName("NamePropertyComponent");
        c.gridy = 0;
        add(new JLabel("name:"),c);
        add(NamePropertyComponent,c);
        

        EmailPropertyComponent = new javax.swing.JTextField("", 50);
        EmailPropertyComponent.addActionListener(ae);
        EmailPropertyComponent.setName("EmailPropertyComponent");
        c.gridy = 1;
        add(new JLabel("email:"),c);
        add(EmailPropertyComponent,c);

        DateOfBirthPropertyComponent = new JTextField("", 10);
        DateOfBirthPropertyComponent.addActionListener(ae);
        DateOfBirthPropertyComponent.setName("DateOfBirthPropertyComponent");
        c.gridy = 2;
        add(new JLabel("gender:"),c);
        add(DateOfBirthPropertyComponent,c);
        
        GenderPropertyComponent = new JComboBox(Person.Gender.values());
        GenderPropertyComponent.addActionListener(ae);
        GenderPropertyComponent.setName("GenderPropertyComponent");
        c.gridy = 3;
        add(new JLabel("date of birth:"),c);
        add(GenderPropertyComponent,c);

        HeightPropertyComponent = new JSlider(100, 200);
        HeightPropertyComponent.addChangeListener(cl);
        HeightPropertyComponent.setPaintTicks(true);
        HeightPropertyComponent.setPaintLabels(true);
        HeightPropertyComponent.setMajorTickSpacing(10);
        HeightPropertyComponent.setMinorTickSpacing(3);
        HeightPropertyComponent.setName("HeightPropertyComponent");
        c.gridy = 4;
        add(new JLabel("height:"),c);
        add(HeightPropertyComponent,c);
        
        setVisible(true);
        
    }

    private class myActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == NamePropertyComponent && !NamePropertyComponent.getText().equals("")) {
                    person.setName(NamePropertyComponent.getText());
            } else if (ae.getSource() == EmailPropertyComponent && !EmailPropertyComponent.getText().equals("")) {
                    person.setEmail(EmailPropertyComponent.getText());
            } else if (ae.getSource() == DateOfBirthPropertyComponent && !DateOfBirthPropertyComponent.getText().equals("")) {
                    person.setDateOfBirth(DateOfBirthPropertyComponent.getText());
            }  else if (ae.getSource() == GenderPropertyComponent) {
                person.setGender((Person.Gender)GenderPropertyComponent.getSelectedItem());
            }
        }
    }

    public void setModel(Person person) {
        this.person = person;
    }

    public Person getModel() {
        return person;
    }
    
    private class myChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent ce){
            int hval = HeightPropertyComponent.getValue();
            if (hval != person.getHeight()) { person.setHeight(hval); }
        }
    }
    
        public static void main(String[] args){
        
        JFrame canvas = new JFrame();
        PersonPanel pp = new PersonPanel();
        
        canvas.add(pp);
        canvas.pack();
        canvas.setVisible(true);
    }
}
