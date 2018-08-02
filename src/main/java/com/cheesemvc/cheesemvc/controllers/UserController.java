package com.cheesemvc.cheesemvc.controllers;

import com.cheesemvc.cheesemvc.models.User;
import com.cheesemvc.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping(value="user")
public class UserController {

    @RequestMapping(value="add", method= {RequestMethod.GET, RequestMethod.POST})
    public String add(
            Model model,
            @ModelAttribute User user,
            HttpServletRequest req,
            String verify
    ){
        HashMap<String,String> errors = new HashMap<>();

        if(req.getMethod().equals("GET")){
            return "user/add";
        }

        if(user.verifyPassword(verify)) {
            errors.put("password","Password is blank or Passwords/Verify Passwords are not matching !");
        }
        else if(!user.validUsername()){
            errors.put("username", "Not a valid Username! *No Spaces and should have a length between 5 amd 15");
        }
        else if(!user.verifyEmail()){
            errors.put("email", "Email shoul not be null!");
        }
        else {
            UserData.add(user);
            return "redirect:/user/userwelcome/"+user.getId();

        }
        System.out.println("Is this comming?????? =====");
        model.addAttribute("errors",errors);
        return "user/add";
    }

    @RequestMapping(value="/{id}")
    public String displayUserDetails(
            Model model,
            @PathVariable Integer id
    ){
        if (id == null || (UserData.getById(id) == null) ) return "redirect:/user";
        model.addAttribute("users",UserData.getById(id)) ;
        model.addAttribute("username",UserData.getById(id).getUsername());
        return "user/details";
    }

    @RequestMapping(value="userwelcome/{id}")
    public String displayHome(
            Model model,
            @PathVariable Integer id
    ){

        if (id == null || (UserData.getById(id) == null) ) return "redirect:/user/";
        model.addAttribute("username",UserData.getById(id).getUsername());
        model.addAttribute("users",UserData.getAll()) ;
        return "user/index";
    }

    @RequestMapping(value="")
    public String index(
            Model model
    ){
        model.addAttribute("users", null);
        return "user/index";
    }

}
