package org.academiadecodigo.shellmurais.wolfmother.model;

import org.springframework.stereotype.Component;

import java.util.*;


public class Group {

    private String groupName;
    private String groupDescription;
    private Integer groupSize;
    private Set<Person> groupMembers = new HashSet<>();

    public Set<Interests> getGroupInterests() {
        return groupInterests;
    }

    public void setGroupInterests(Set<Interests> groupInterests) {
        this.groupInterests = groupInterests;
    }

    public Set<Group> getMatchedGroups() {
        return matchedGroups;
    }

    public void setMatchedGroups(Set<Group> matchedGroups) {
        this.matchedGroups = matchedGroups;
    }

    private Set<Interests> groupInterests = new HashSet<>();
    private Set<Group> matchedGroups = new HashSet<>();
    private Group currentMatch;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupSize(Integer size){
        this.groupSize = size;
    }

    public Integer getGroupSize() {
        return this.groupMembers.size();
    }

    public Set<Person> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Set<Person> groupMembers) {
        this.groupMembers = groupMembers;
    }



    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public Set<Group> getMatched() {
        return matchedGroups;
    }

    public void setMatched(Set<Group> matched) {
        this.matchedGroups = matched;
    }

    public Group getCurrentMatch() {
        return currentMatch;
    }

    public void setCurrentMatch(Group currentMatch) {
        this.currentMatch = currentMatch;
    }

    public void addMember(Person person){
        this.groupMembers.add(person);
        //updateInterests(person);
        person.setGroup(this);
    }

    public void removeMember(Person person){
        this.groupMembers.remove(person);
    }

    /*public void updateInterests(Person newMember){
        Set<Interests> memberInterests = newMember.getInterests();

        for (Interests interest : memberInterests){
            if (!groupInterests.containsKey(interest)){
                groupInterests.put(interest, 1);
                continue;
            }
                groupInterests.put(interest, groupInterests.get(interest) + 1);
        }
    }*/

   /* public void addPersonInterest(Interests interest){
        if (groupInterests.isEmpty()) {
            groupInterests.put(interest,1);
            return;
        }
        if (!groupInterests.containsKey(interest)){
            groupInterests.put(interest, 1);
            return;
        }
        groupInterests.put(interest, groupInterests.get(interest) + 1);
    }

    public void removePersonInterest(Interests interest) {
        if (groupInterests.isEmpty()) {
            return;
        }
        if (groupInterests.get(interest) == 1) {
            groupInterests.remove(interest);
            return;
        }
        groupInterests.put(interest, groupInterests.get(interest) - 1);
    }*/

    public void addInterests(Interests interest){
        groupInterests.add(interest);
    }

    public void addMatchedGroup(Group group){
        matchedGroups.add(group);
    }
}
