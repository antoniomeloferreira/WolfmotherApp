package org.academiadecodigo.shellmurais.wolfmother.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Person {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private Group group;
/*    private Set<Interests> interests = new HashSet<>();
    private Set<Person> friendList = new HashSet<>();*/

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

   /* public void setInterests(Set<Interests> interests) {
        this.interests = interests;
    }

    public Set<Interests> getInterests() {
        return interests;
    }
*/
    /*public Set<Person> getFriendList() {
        return friendList;
    }

    public void setFriendList(Set<Person> friendList) {
        this.friendList = friendList;
    }*/

    /*public void addInterest(Interests interest){
        this.interests.add(interest);
        if(group != null) {
            group.addPersonInterest(interest);
        }
    }

    public void removeInterest(Interests interest){
        this.interests.remove(interest);
        if(group != null) {
            group.removePersonInterest(interest);
        }
    }*/
}
