package com.theironyard;

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

    ArrayList<Message> messageList = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        String username = (String) session.getAttribute("username");

        User user = null;
        if (username != null) {
            user = new User(username);
        }
        String text = (String) session.getAttribute("text");
        Integer id = (Integer) session.getAttribute("id");
        Message msg = null;
        if(text != null) {
            msg = new Message(text);
        }
        model.addAttribute("user", user);
        model.addAttribute("text", text);

        return "home";
    }
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(String username, HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/";
    }
    @RequestMapping(path ="/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping(path ="/add-message", method = RequestMethod.POST)
    public String addMessage(String text, HttpSession session) {

        session.setAttribute("text", text);
        Message msg = new Message(text);
        messageList.add(msg);
        return "redirect:/";
    }
}