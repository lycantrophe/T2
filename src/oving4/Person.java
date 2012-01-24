package oving4;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lycantrophe
 */
public class Person {
        
    public enum Gender { male, female};
    
    
    private String name;
    private String dateOfBirth;
    private Gender gender;
    private String email;
    private int height;
    private PropertyChangeSupport pcs;
    
    public Person(String name) {
        this.name = name;
        pcs = new PropertyChangeSupport(this);
    }
    public Person() {
        pcs = new PropertyChangeSupport(this);
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        pcs.firePropertyChange("NAME", oldValue, name);
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(String argOfBirth) {
        String oldValue = this.dateOfBirth;
        this.dateOfBirth = argOfBirth;
        pcs.firePropertyChange("DATEOFBIRTH", oldValue, dateOfBirth);
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        String oldValue = this.email;
        this.email = email;
        pcs.firePropertyChange("EMAIL", oldValue, email);
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        int oldValue = this.height;
        this.height = height;
        pcs.firePropertyChange("HEIGHT", oldValue, height);
    }
    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        Gender oldValue = this.gender;
        this.gender = gender;
        pcs.firePropertyChange("GENDER", oldValue, gender);
    }
    
    public void createPerson(String name, String dateOfBirth, Gender gender, String email, int height){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.height = height;
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
    
}
