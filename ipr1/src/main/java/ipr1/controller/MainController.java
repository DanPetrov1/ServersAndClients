package ipr1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String getMethodsClass() {
        return "index";
    }

    @RequestMapping(value = "/response", method = RequestMethod.GET)
    public String outputMethodsClass(Model model, @ModelAttribute("class") String target) {
        try {
            Class<?> clazz = Class.forName(target);
            Method[] methods = clazz.getMethods();
            ArrayList<String> methodsList = new ArrayList<>();
            for (Method method : methods) {
                methodsList.add(method.getName());
            }
            model.addAttribute("methods", methodsList);
        } catch (ClassNotFoundException e) {
            model.addAttribute("message", "There're no such class!");
        }

        return "response";
    }
}
