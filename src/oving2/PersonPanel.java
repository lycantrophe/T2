package oving2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public class PersonPanel extends JPanel implements PropertyChangeListener {

    Person person;
    javax.swing.JTextField NamePropertyComponent;
    javax.swing.JTextField EmailPropertyComponent;
    javax.swing.JTextField DateOfBirthPropertyComponent;
    javax.swing.JComboBox GenderPropertyComponent;
    javax.swing.JSlider HeightPropertyComponent;
    GridBagConstraints c;
    myChangeListener cl;
    myActionListener ae;

    public PersonPanel() {

        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        person = new Person("Dummy");

        NamePropertyComponent = new javax.swing.JTextField("", 50);
        NamePropertyComponent.setName("NamePropertyComponent");
        c.gridy = 0;
        add(new JLabel("name:"), c);
        add(NamePropertyComponent, c);

        EmailPropertyComponent = new javax.swing.JTextField("", 50);
        EmailPropertyComponent.setName("EmailPropertyComponent");
        c.gridy = 1;
        add(new JLabel("email:"), c);
        add(EmailPropertyComponent, c);

        DateOfBirthPropertyComponent = new JTextField("", 10);
        DateOfBirthPropertyComponent.setName("DateOfBirthPropertyComponent");
        c.gridy = 2;
        add(new JLabel("date of birth:"), c);
        add(DateOfBirthPropertyComponent, c);

        setVisible(true);

    }

    public void setUpRest() {

        cl = new myChangeListener();
        ae = new myActionListener();

        NamePropertyComponent.addActionListener(ae);
        EmailPropertyComponent.addActionListener(ae);
        DateOfBirthPropertyComponent.addActionListener(ae);

        GenderPropertyComponent = new JComboBox(Person.Gender.values());
        GenderPropertyComponent.addActionListener(ae);
        GenderPropertyComponent.setName("GenderPropertyComponent");
        c.gridy = 3;
        add(new JLabel("gender:"), c);
        add(GenderPropertyComponent, c);

        HeightPropertyComponent = new JSlider(100, 200);
        HeightPropertyComponent.addChangeListener(cl);
        HeightPropertyComponent.setPaintTicks(true);
        HeightPropertyComponent.setPaintLabels(true);
        HeightPropertyComponent.setMajorTickSpacing(10);
        HeightPropertyComponent.setMinorTickSpacing(3);
        HeightPropertyComponent.setName("HeightPropertyComponent");
        c.gridy = 4;
        add(new JLabel("height:"), c);
        add(HeightPropertyComponent, c);
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
            } else if (ae.getSource() == GenderPropertyComponent) {
                person.setGender((Person.Gender) GenderPropertyComponent.getSelectedItem());
            }
        }
    }

    public void setModel(Person person) {
        this.person = person;
        person.addPropertyChangeListener(this);
    }

    public Person getModel() {
        return person;
    }

    private class myChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent ce) {
            int hval = HeightPropertyComponent.getValue();
            if (hval != person.getHeight()) {
                person.setHeight(hval);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("NAME")) {
            NamePropertyComponent.setText(person.getName());
        } else if (evt.getPropertyName().equals("EMAIL")) {
            EmailPropertyComponent.setText(person.getEmail());
        } else if (evt.getPropertyName().equals("DATEOFBIRTH")) {
            DateOfBirthPropertyComponent.setText(person.getDateOfBirth());
        } else if (evt.getPropertyName().equals("GENDER")) {
            GenderPropertyComponent.setSelectedItem(person.getGender());
        } else if (evt.getPropertyName().equals("HEIGHT")) {
            HeightPropertyComponent.setValue(person.getHeight());
        }
    }

    public static void main(String[] args) {

        JFrame canvas = new JFrame();
        JFrame passiveCanvas = new JFrame();

        PersonPanel pp = new PersonPanel();
        pp.setUpRest();
        PassivePanel passivepp = new PassivePanel();
        passivepp.setUpRest();
        Person person = new Person("Annemerete");

        passivepp.setModel(person);
        pp.setModel(person);

        canvas.add(pp);
        canvas.pack();
        canvas.setVisible(true);

        passiveCanvas.add(passivepp);
        passiveCanvas.pack();
        passiveCanvas.setVisible(true);
    }
}
