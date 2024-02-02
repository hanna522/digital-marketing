/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Personnel;

/**
 *
 * @author kal bugrara
 */
public class Profile {
    Person person;
    String role;

    public Profile(Person p, String r) {
        person = p;
        role = r;
    }

    public String getRole() {
        return role;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isMatch(String id) {
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

}
