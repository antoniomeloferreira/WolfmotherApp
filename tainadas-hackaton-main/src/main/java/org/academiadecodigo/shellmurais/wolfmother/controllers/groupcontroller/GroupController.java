package org.academiadecodigo.shellmurais.wolfmother.controllers.groupcontroller;

import org.academiadecodigo.shellmurais.wolfmother.controllers.MatchMaker;
import org.academiadecodigo.shellmurais.wolfmother.model.Group;
import org.academiadecodigo.shellmurais.wolfmother.model.Interests;
import org.academiadecodigo.shellmurais.wolfmother.services.CreateGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/groupPack")
@Controller
public class GroupController {

    private MatchMaker matchMaker;
    private CreateGroupService groupService;

    public MatchMaker getMatchMaker() {
        return matchMaker;
    }

    @Autowired
    public void setMatchMaker(MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
    }

    public CreateGroupService getGroupService() {
        return groupService;
    }

    @Autowired
    public void setGroupService(CreateGroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public String startGroup(Model model) {
        model.addAttribute("group", new Group());
        return "groupcreation";
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/", ""})
    public String groupCreation(@ModelAttribute("group") Group group) {
        Group newGroup = new Group();

        newGroup.setGroupName(group.getGroupName());
        newGroup.setGroupSize(group.getGroupSize());
        newGroup.setGroupDescription(group.getGroupDescription());
        newGroup.addInterests(Interests.FOOTBALL);
        newGroup.addInterests(Interests.FOOD);
        newGroup.addInterests(Interests.PARTYING);

        matchMaker.addGroup(newGroup);
        matchMaker.getPeoplePool().get(matchMaker.getPeoplePool().size()-1).setGroup(group);

        return "redirect:/sheep/authentication/gunas";
    }


}
