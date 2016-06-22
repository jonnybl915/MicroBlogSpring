package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by jonathandavidblack on 6/20/16.
 */
@Controller
public class MicroBlogController {
    @Autowired
    UserRepository users;
    @Autowired
    MessageRepository messages;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");

        if (username == null) {
            return "login";
        }
        else {
                Iterable<Message> msgs = messages.findAll();
                model.addAttribute("messages", msgs);
                model.addAttribute("name", username);
                return "home";
        }
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session, String password) throws Exception {
        User user = users.findByName(username);
        if (user == null) {
            user = new User(username, password);
            users.save(user); //saves the created user object and inserts it into our table using our repository
        }
        else if (!user.password.equals(password)) {
            return "redirect:/";
        }
        else if(username.equals("") || password.equals("")) {        //**************STILL NOT WORKING
            return "redirect:/";
        }
        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path ="/add-message", method = RequestMethod.POST)
    public String addMessage(String text, HttpSession session) {

        Message msg = new Message(text);
        messages.save(msg);
        return "redirect:/";
    }
    @RequestMapping(path="/delete-message", method = RequestMethod.POST)
    public String deleteMessage(Integer id) {
        messages.delete(id);
        return "redirect:/";
    }
    @RequestMapping(path ="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path ="/edit-message", method = RequestMethod.POST)
    public String edit(Integer id, String text) {
        Message message = new Message(id, text);
        messages.save(message);
        return "redirect:/";
    }
}
