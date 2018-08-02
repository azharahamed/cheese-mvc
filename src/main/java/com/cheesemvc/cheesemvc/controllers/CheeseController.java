package com.cheesemvc.cheesemvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping(value="cheese")
public class CheeseController {

    static HashMap<String, String> cheeses = new HashMap<>();

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("cheeses",cheeses);
        model.addAttribute("title","My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title","Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddCheeseForm(Model model, @RequestParam(required = false) String cheeseName,@RequestParam String description) {

        if(!isValid(cheeseName) || cheeseName == null ||  cheeseName==""){
            model.addAttribute("title","Add Cheese");
            model.addAttribute("error","Sorry! Cheese name is required and accepts only alphabetic character with spaces");
            return "cheese/add";
        }
        cheeseName = cheeseName.trim();
        if(cheeses.get(cheeseName) != null){
            model.addAttribute("title","Add Cheese");
            model.addAttribute("error","Sorry! " + cheeseName + " Already exists - Duplicate names are not allowed");
            return "cheese/add";
        }
        cheeses.put(cheeseName,description);
        return "redirect:/cheese";
    }

    @RequestMapping(value="remove", method = RequestMethod.GET)
    public String remove(Model model,@RequestParam(required=false) String cheeseName){
        if(cheeseName != null){
            if(cheeses.get(cheeseName) == null){
                model.addAttribute("cheeses",cheeses);
                model.addAttribute("title","My Cheese");
                model.addAttribute("error","Sorry! " + cheeseName + " Doesn't Exists and can't be deleted");
                return "cheese/index";
            }
            cheeses.remove(cheeseName);
            return "redirect:/cheese";
        }
        model.addAttribute("title","Select and Remove Cheese");
        model.addAttribute("cheeses",cheeses);
        return "cheese/remove";
    }

    @RequestMapping(value="remove", method = RequestMethod.POST)
    public String processRemoveCheese(Model model, @RequestParam(value="cheeseName", required = false) String[] cheeseNames) {
        if(cheeseNames != null){
            for(String cheeseName:cheeseNames){
                if(cheeses.get(cheeseName) != null){
                    cheeses.remove(cheeseName);
                }
            }
        }

        return "redirect:/cheese";
    }

    public Boolean isValid(String cheeseName){
        cheeseName = cheeseName.trim();
        if(cheeseName.length() == 0) return false;
        for(char letter:cheeseName.toCharArray()){
           if((!Character.isLetter(letter)) && (letter != ' ')){
               return false;
           }
        }
        return true;
    }

}
