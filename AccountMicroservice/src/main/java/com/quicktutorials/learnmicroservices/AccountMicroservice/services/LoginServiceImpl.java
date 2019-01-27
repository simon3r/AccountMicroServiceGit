package com.quicktutorials.learnmicroservices.AccountMicroservice.services;

import com.quicktutorials.learnmicroservices.AccountMicroservice.daos.UserDao;
import com.quicktutorials.learnmicroservices.AccountMicroservice.entities.User;
import com.quicktutorials.learnmicroservices.AccountMicroservice.utils.EncryptionUtils;
import com.quicktutorials.learnmicroservices.AccountMicroservice.utils.JwtUtils;
import com.quicktutorials.learnmicroservices.AccountMicroservice.utils.UserNotLoggedException;
import jdk.internal.jline.internal.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    EncryptionUtils encryptionUtils;
    @Autowired
    UserDao userDao;

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public Optional<User> getUserFromDbAndVerifyPassword(String id, String password) throws UserNotLoggedException{
        Optional<User> userr = userDao.findById(id);
        if(userr.isPresent()){ //l'option permette di utilizzare questo metodo
            User user = userr.get(); //dall'option ottengo l'utente effettivo con get()
            String dbPassword = encryptionUtils.decrypt(user.getPassword()); //effettuo la decriptazione della password dal db
            if (dbPassword.equals(password)){
                Log.info("username and passowrd verified");
            }else{
                Log.info("Username verified but password not");
                throw new UserNotLoggedException("User not correctly logged In");
            }
        }
        return userr;
    }

    @Override
    public String createJwt(String subject, String name, String permission, Date datenow) throws UnsupportedEncodingException {//messagio Json che viene codificato in una stringa che contiene tutti questi dati
        Date expDate = new Date(); //Definiamo la data in cui questo JWT ha validità, quindi come la sessione, diciamo quando la sessione scade
        expDate.setTime(datenow.getTime() + (300*1000));
        Log.info("JWT Creation. Expiration time: "+ expDate.getTime());
        String token = JwtUtils.generateJwt(subject,expDate,name,permission); //creato il jwt avremo l'exception UnsupportedEncodingException che però vogliamo gestire nel controller quindi andremo a fare il throws nella firma del metodo, e dichiareremo che questo metodo lancia un exception che dovrà poi essere catturata
        return token;
    }

    @Override
    public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException{
        String jwt = JwtUtils.getJwtFromHttpRequest(request); //l'utente quando effettuerà delle richiesta allegerà alla richiesta nel header il json web token JWT, che abbiamo creato nel metodo sopra e abbiamo inviato in precedenza quindi al browser, il browser lo salva in memoria e lo reinvia ad ogni successiva richiesta
        //questo token accerta che l'utente è loggato, e quindi va verificato sia valido, non si scaduto ecc
        if(jwt == null){
            throw new UserNotLoggedException("Autentichation token non found in that request");
        }
        Map<String, Object> userData = JwtUtils.jwt2Map(jwt);

        return userData;
    }
}
