package com.quicktutorials.learnmicroservices.AccountMicroservice.controllers;

import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class RestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello a tutti";
    }

    @RequestMapping("/prova")
    @ResponseBody
    public String prova(){
        return "prova";
    }


    @RequestMapping("/newuser1")
    @ResponseBody
    public String addUser(User user){ //data binding di User automatico di spring passando gli attributi di user
        return "User added correctly: "+ user.getId();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to @Valid
    @RequestMapping("/newuser2")
    @ResponseBody
    public String addUserValid(@Valid User user){
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to Spring object BindingResult
    @RequestMapping("/newuser3")
    @ResponseBody
    public String addUserValidPlusBinding(@Valid User user, BindingResult result){ // BindingResult elemento di spring che si occupa di verificare se ci sono errore, tramite i @not null ecc
        if(result.hasErrors()){
            return result.toString();
        }
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a SPRING VALIDATOR error message thanks to Spring object BindingResult
    @RequestMapping("/newuser4")
    @ResponseBody
    public String addUserValidPlusBinding2(User user, BindingResult result){ //non c'è Valid, utilizzo un elemento di spring che è validator
        /* Spring validation Non utilizzo più la validazione java ma quella di spring definita nella inner class sotto*/
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if(result.hasErrors()){
            return result.toString();
        }
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    /*---------------------------INNER CLASS------------------------*/
    //Spring Validator Example
    private class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) { //deve supportare la classe utente, validarla
            return User.class.equals(clazz);
        }

        @Override
        public void validate(Object obj, Errors errors) { //carica gli errori presente in base a ciò che definisco
            User user = (User) obj;
            if (user.getPassword().length() < 8) {
                errors.rejectValue("password", "the password must be at least 8 chars long!");
            }
        }
    }
    /*--------------------------------------------------*/
    @AllArgsConstructor
    public class JsonResponseBody{
        @Getter @Setter
        private int server;
        @Getter @Setter
        private Object response;
    }
}
