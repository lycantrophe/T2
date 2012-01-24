/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oving4;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import oving4.Person.Gender;

/**
 *
 * @author lycantrophe
 */
public class PersonListPanel extends JPanel {
    
    private GridBagConstraints c;
    private JList PersonList;
    private PersonPanel PersonPanel;
    private DefaultListModel DefaultListModel;
    private JScrollPane scrollPane;
    private myListSelectionListener ml;
    private JButton NewPersonButton;
    private JButton DeletePerson;
    
    public PersonListPanel() {
        
        setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        
        scrollPane = new JScrollPane();
        
        ml = new myListSelectionListener();
        PersonRenderer cr = new PersonRenderer();
        
        DefaultListModel = new DefaultListModel();
        PersonList = new JList(DefaultListModel);
        PersonList.addListSelectionListener(ml);
        PersonList.setName("PersonList");
        PersonList.setCellRenderer(cr);
        
        PersonPanel = new PersonPanel();
        PersonPanel.setUpRest();
        PersonPanel.setName("PersonPanel");
        
        newPersonAction np = new newPersonAction();
        deletePersonAction dp = new deletePersonAction();
        
        NewPersonButton = new JButton("New person");
        NewPersonButton.setName("NewPersonButton");
        NewPersonButton.setAction(np);
        
        DeletePerson = new JButton("Delete person");
        DeletePerson.setName("DeletePersonButton");
        DeletePerson.setAction(dp);
        
        PersonList.addListSelectionListener(ml);
        
        scrollPane.getViewport().setView(PersonList);
        c.gridx = 0;
        c.gridy = 0;
        add(scrollPane, c);
        
        c.gridx = 0;
        c.gridy = 1;
        add(NewPersonButton, c);
        c.gridx = 1;
        add(DeletePerson, c);
        
        c.gridx = 1;
        c.gridy = 0;
        add(PersonPanel, c);
        
        
        
    }
    
    private class myListSelectionListener implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent lse) {
            if( PersonList.getSelectedIndex() != -1 ){
            PersonPanel.setModel((Person)PersonList.getSelectedValue());
            PersonPanel.selected();
            }
            else {
                PersonPanel.DateOfBirthPropertyComponent.setText("");
                PersonPanel.EmailPropertyComponent.setText("");
                PersonPanel.NamePropertyComponent.setText("");
            }
        }
    }
    
    public void setModel(DefaultListModel dlm) {
        PersonList.setModel(dlm);
        DefaultListModel = dlm;
    }
    
    public DefaultListModel getModel() {
        return DefaultListModel;
    }

    /*
     * public void addPerson() {
     *
     * }
     */
    private class newPersonAction extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            Person p = new Person();
            DefaultListModel.addElement(p);
            PersonList.setSelectedValue(p, enabled);
        }
    }
    
    private class deletePersonAction extends AbstractAction {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if( PersonList.getSelectedIndex() != -1)
            DefaultListModel.remove(PersonList.getSelectedIndex());
        }
    }
    
    public class PersonRenderer extends JLabel implements ListCellRenderer {
        
        final ImageIcon maleIcon = new ImageIcon("long.gif");
        final ImageIcon femaleIcon = new ImageIcon("short.gif");
        
        @Override
        public Component getListCellRendererComponent(JList jlist, Object o, int i, boolean bln, boolean bln1) {
            String s = "";
            if( ((Person)o).getName() != null)
                s = ((Person)o).getName();
            if( ((Person)o).getEmail() != null && !s.equals(""))
                s += " " + ((Person)o).getEmail();
            if( s.equals(""))
                s = "Person..";
            setText(s);
            //String s = o.toString();
            setIcon( ((Person)o).getGender() == Gender.male && ((Person)o).getGender() != null ? maleIcon : femaleIcon);
            
            if (bln) {
                setBackground(jlist.getSelectionBackground());
                setForeground(jlist.getSelectionForeground());
            } else {
                setBackground(jlist.getBackground());
                setForeground(jlist.getForeground());
            }
            setEnabled(jlist.isEnabled());
            setFont(jlist.getFont());
            setOpaque(true);
            return this;
        }
    }
    
    public static void main(String[] args) {
        
        JFrame canvas = new JFrame();
        PersonListPanel plp = new PersonListPanel();
        
        canvas.add(plp);
        canvas.pack();
        canvas.setVisible(true);
    }
}
