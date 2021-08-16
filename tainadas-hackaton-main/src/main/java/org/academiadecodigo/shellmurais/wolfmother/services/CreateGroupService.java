package org.academiadecodigo.shellmurais.wolfmother.services;

import org.academiadecodigo.shellmurais.wolfmother.model.Group;
import org.academiadecodigo.shellmurais.wolfmother.model.Person;
import org.springframework.stereotype.Service;

@Service
public class CreateGroupService {

    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public  void CreateGroup(Person person){
        Group group = new Group();

        group.addMember(person);

    }
}
