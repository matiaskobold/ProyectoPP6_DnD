package com.matiaskobold.proyectopp6.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matiaskobold.proyectopp6.model.SteamNews;
import com.matiaskobold.proyectopp6.repository.SongRepository;
import com.matiaskobold.proyectopp6.security.User;
import com.matiaskobold.proyectopp6.security.UserRepository;
import com.matiaskobold.proyectopp6.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class Landing {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model){

        return "landing.html";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login.html";
    }

    @RequestMapping("/logout-success")
    public String logOutPage(){
        return "logout.html";
    }

    @RequestMapping("/newUser")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "newUser.html";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult){

        if(userRepository.existsByUsername(user.getUsername())){
            bindingResult.rejectValue("username", "error.userName", "This username is already in use!!!");
            System.out.println("No se guardo el usuario");
            return "newUser.html";
        }

        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/publicAPI")
    public String publicAPI(Model model, RestTemplate restTemplate) throws JsonProcessingException{
        int gameSteamID=1086940;
        int numberOfNews=3;
        int maxLengthOfNews=1000000;
        String formatResponse="json";


        ResponseEntity<String> responseStr=restTemplate.getForEntity("http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?"+
                        "appid="+gameSteamID+
                        "&count="+numberOfNews+
                        "&maxlength="+maxLengthOfNews+
                        "&format="+formatResponse,
                String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseStr.getBody());
        List<SteamNews> steamNews = new ArrayList<>();

        for (int i=0; i<numberOfNews;i++) {
            String title = root.get("appnews").get("newsitems").get(i).get("title").asText();
            String contents = root.get("appnews").get("newsitems").get(i).get("contents").asText();
            String author = root.get("appnews").get("newsitems").get(i).get("author").asText();
            String url = root.get("appnews").get("newsitems").get(i).get("url").asText();
            steamNews.add(new SteamNews(title, contents, author, url));

        }

        model.addAttribute("steamNews", steamNews);
        model.addAttribute("game", gameSteamID);

        return "publicApi.html";
    }

}
