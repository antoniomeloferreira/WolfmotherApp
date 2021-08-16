package org.academiadecodigo.shellmurais.wolfmother.controllers;

import org.academiadecodigo.shellmurais.wolfmother.model.Group;
import org.academiadecodigo.shellmurais.wolfmother.model.Interests;
import org.academiadecodigo.shellmurais.wolfmother.model.Person;
import org.springframework.stereotype.Controller;

import java.util.*;

@Controller
public class MatchMaker {

    private Integer counter = 0;
    private Map<Group, Integer> availableGroups = new HashMap<>();
    private List<Person> peoplePool = new LinkedList<>();

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Map<Group, Integer> getAvailableGroups() {
        return availableGroups;
    }

    public void setAvailableGroups(Map<Group, Integer> availableGroups) {
        this.availableGroups = availableGroups;
    }

    public List<Person> getPeoplePool() {
        return peoplePool;
    }

    public void setPeoplePool(List<Person> peoplePool) {
        this.peoplePool = peoplePool;
    }

    public void addGroup(Group group){
        if (availableGroups.containsKey(group)){
            return;
        }
        availableGroups.put(group, counter++);
    }

    public void showGroup(Group group){
        int tries = 0;
        int randomGroupID = getRandomGroupID();
        while (randomGroupID == availableGroups.get(group)){
            if (tries >= 5){
                System.out.println("Could not find matching group");
                return;
            }
            tries++;
            randomGroupID = getRandomGroupID();
        }

        Group matchedGroup = getRandomGroup(randomGroupID);
        Set<Interests> matchedGroupInterests = matchedGroup.getGroupInterests();

        if(!areInterestsCompatible(group.getGroupInterests(),matchedGroupInterests)){
            System.out.println("No common interests found");
            return;
        }

        System.out.println("Found group: " + matchedGroup.getGroupName());
    }

    public void addPerson(Person person){
        peoplePool.add(person);
    }


    private int getRandomGroupID(){
        return (int) (Math.random() * availableGroups.size());
    }

    private Group getRandomGroup(int id){
        for (Group matchedGroup : availableGroups.keySet()){
            if (id == availableGroups.get(matchedGroup)){
                return matchedGroup;
            }
        }
        return null;
    }

    public boolean areInterestsCompatible(Set<Interests> group1, Set<Interests> group2){
        for(Interests interest : group1){
            for (Interests interest2 :group2){
                if(interest.equals(interest2)){
                    return true;
                }
            }
        }
        return false;
    }
}
