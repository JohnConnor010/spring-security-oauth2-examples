package com.example.springbootoauth2jwt.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2Controller {
    @RequestMapping(value = "/oauth/confirm_access")
    public String getAccessConfirmation(Model model, HttpServletRequest request){
        List<String> scopes = new ArrayList<>();
        String scope = request.getParameter("scope");
        if (scope != null) {
            String[] split = scope.split(" ");
            for (String s : split) {
                scopes.add(s);
            }
        }
        model.addAttribute("scopes",scopes);
        return "oauth2/confirm_access";
    }

    @RequestMapping(value = "/oauth/login")
    public String login(Model model, @Nullable Boolean error,HttpServletRequest request){
        if (error == null) {
            model.addAttribute("error",error);
        }else{
            if ("logout".equals(request.getQueryString())) {
                return "redirect:/";
            }
        }
        return "oauth2/login";
    }
}
