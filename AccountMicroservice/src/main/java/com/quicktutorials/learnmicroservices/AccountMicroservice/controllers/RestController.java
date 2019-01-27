package com.quicktutorials.learnmicroservices.AccountMicroservice.controllers;

import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.Operation;
import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping("/newuser2")
    @ResponseBody
    public String addUserValid(@Valid User user){
        //se l'utente esce dal metodo è torna un errore strutturato di Java senza possibilità di cambiarlo
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a JAVA JSR-303 error message thanks to Spring object BindingResult
    @RequestMapping("/newuser3")
    @ResponseBody
    public String addUserValidPlusBinding(@Valid User user, BindingResult result){
        // BindingResult elemento di spring che si occupa di verificare se ci sono errore, tramite i @not null ecc, in questo caso come si vede il messaggio di ritorno lo decidiamo noi non Java
        if(result.hasErrors()){
            return result.toString();
        }
        return "User added correctly:" + user.getId() + ", "+ user.getUsername();
    }

    //if pwd is null it will return a SPRING VALIDATOR error message thanks to Spring object BindingResult
    @RequestMapping("/newuser4")
    @ResponseBody
    public String addUserValidPlusBinding2(User user, BindingResult result){ //non c'è Valid, utilizzo un elemento di spring che è validator
        /* Spring validation Non utilizzo più la validazione java ma quella di spring definita nella inner class sotto, quindi customizzabile come si vuole*/
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


    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> loginUser(@RequestParam(value ="id") String id, @RequestParam(value="password") String pwd){
        //check if user exists in DB -> if exists generate JWT and send back to client
        return null;
    }


    @RequestMapping("/operations/account/{account}")
    public ResponseEntity<JsonResponseBody> fetchAllOperationsPerAccount(HttpServletRequest request, @PathVariable(name = "account") String account){
        //request -> fetch JWT -> check validity -> Get operations from the user account
        return null;
    }

    @RequestMapping(value = "/accounts/user", method = POST)
    public ResponseEntity<JsonResponseBody> fetchAllAccountsPerUser(HttpServletRequest request){
        //request -> fetch JWT -> recover User Data -> Get user accounts from DB
        return null;
    }

    @RequestMapping(value = "/operations/add", method=POST)
    public ResponseEntity<JsonResponseBody> addOperation(HttpServletRequest request, @Valid Operation operation, BindingResult bindingResult){
        //request -> fetch JWT -> recover User Data -> Save valid operation in DB
        return null;
    }
}
