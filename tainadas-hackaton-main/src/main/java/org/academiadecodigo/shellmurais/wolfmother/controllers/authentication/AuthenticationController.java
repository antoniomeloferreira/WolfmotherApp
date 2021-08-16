package org.academiadecodigo.shellmurais.wolfmother.controllers.authentication;

import org.academiadecodigo.shellmurais.wolfmother.controllers.MatchMaker;
import org.academiadecodigo.shellmurais.wolfmother.model.Group;
import org.academiadecodigo.shellmurais.wolfmother.model.Person;
import org.academiadecodigo.shellmurais.wolfmother.services.CreateProfileService;
import org.academiadecodigo.shellmurais.wolfmother.services.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@RequestMapping("/sheep")
@Controller
public class AuthenticationController {

    private CreateProfileService createProfileService;
    private LogInService logInService;

    public MatchMaker getMatchMaker() {
        return matchMaker;
    }

    @Autowired
    public void setMatchMaker(MatchMaker matchMaker) {
        this.matchMaker = matchMaker;
    }

    private MatchMaker matchMaker;

    public LogInService getLogInService() {
        return logInService;
    }

    @Autowired
    public void setLogInService(LogInService logInService) {
        this.logInService = logInService;
    }

    public CreateProfileService getCreateProfileService() {
        return createProfileService;
    }

    @Autowired
    public void setCreateProfileService(CreateProfileService createProfileService) {
        this.createProfileService = createProfileService;
    }

    @RequestMapping(method= RequestMethod.GET, path = { "/", ""} )
    public String homeScreen(){
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/authentication/logIn")
    public String authenticate(Model model){
        model.addAttribute("user", new Person());
        return "authentication";
    }

    @RequestMapping(method = RequestMethod.POST, path = { "/authentication/logIn"})
    public String logIn(@ModelAttribute("user") Person person, BindingResult bindingResult) {
        if(person.getEmail().isEmpty() || person.getPassword().isEmpty() || person.getPassword() == null || person.getEmail() ==null){
            return "index";
        }
        if(!logInService.start(person.getEmail(), person.getPassword())){
            return "index";
        }
        return "redirect:/sheep/groupView";

    }

    @RequestMapping(method = RequestMethod.GET, path = { "/authentication/gunas"})
    public String gunas(Model model) {
        model.addAttribute("groups", matchMaker.getAvailableGroups());
        return "gunas";
    }

    @RequestMapping(method = RequestMethod.POST, path = { "/authentication/gunas"})
    public String postGUnas(@ModelAttribute("groups") Map<Group,Integer> map, BindingResult bindingResult) {
        return "gunas";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/authentication/signIn")
    public String startAPack(Model model){
        model.addAttribute("person", new Person());
        return "signin";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/authentication/signIn")
    public String packCreation(@ModelAttribute("person") Person person, BindingResult result){
        Person newPerson = new Person();

        newPerson.setPassword(person.getPassword());
        newPerson.setEmail(person.getEmail());
        newPerson.setLastName(person.getLastName());
        newPerson.setFirstName(person.getFirstName());
        newPerson.setAge(person.getAge());

        matchMaker.addPerson(newPerson);
        createProfileService.addUser(person.getEmail(), person.getPassword());

        return "redirect:/groupPack";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/groupView")
    public String showGroups(Model model){
        model.addAttribute("groups", matchMaker.getAvailableGroups().keySet());
        return "gunas";
    }


}
