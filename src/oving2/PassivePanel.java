/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oving2;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author lycantrophe
 */
public class PassivePanel extends PersonPanel {

    private JTextField GenderPropertyField;
    private JTextField HeightPropertyField;

    public PassivePanel() {
    }

    @Override
    public void setUpRest() {

        NamePropertyComponent.setEditable(false);
        EmailPropertyComponent.setEditable(false);
        DateOfBirthPropertyComponent.setEditable(false);

        GenderPropertyField = new JTextField("", 20);
        GenderPropertyField.setName("GenderPropertyComponent");
        GenderPropertyField.setEditable(false);
        c.gridy = 3;
        add(new JLabel("gender:"), c);
        add(GenderPropertyField, c);

        HeightPropertyField = new JTextField("", 10);
        HeightPropertyField.setName("HeightPropertyComponent");
        HeightPropertyField.setEditable(false);
        c.gridy = 4;
        add(new JLabel("height:"), c);
        add(HeightPropertyField, c);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("GENDER")) {
            GenderPropertyField.setText(person.getGender().toString());
        } else if (evt.getPropertyName().equals("HEIGHT")) {
            HeightPropertyField.setText(Integer.toString(person.getHeight()));
        } else {
            super.propertyChange(evt);
        }
    }
}
