package ru.job4j.models;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ValidateService {
    private static final ValidateService validateService = new ValidateService();
    //private final Store persistent = MemoryStore.getMemoryStore();
    private final Store persistent = DBStore.getDBStore();
    private String message = "";
    private User myUser;


    public static ValidateService getValidateService() {
        return validateService;
    }

    public String getMessage() {
        return message;
    }

    public void add(User user) {
        persistent.add(user);
    }

    public String update(User user) {
        if (checkNumber(String.valueOf(user.getId())) && checkExistence(String.valueOf(user.getId()))) {
            persistent.update(user);
        }
        return message;
    }

    public String delete(String index) {
        if (checkNumber(index) && checkExistence(index)) {
            persistent.delete(Integer.parseInt(index));
        }
        return message;
    }

    public List<User> findAll() {
        return persistent.findAll();
    }

    public User findById(String index) throws Exception {
        if (checkNumber(index) && checkExistence(index)) {
            return persistent.findById(Integer.parseInt(index));
        }
        throw new Exception(message);
    }


    public boolean checkNumber(String indexS) {
        boolean result = true;
        int index;
        try {
            index = Integer.parseInt(indexS);
        } catch (Exception ex) {
            message = "index should be a number";
            result = false;
        }
        return result;
    }

    public boolean checkExistence(String indexS) {
        boolean result = true;
        try {
            User user = this.findAll().get(Integer.parseInt(indexS));
        } catch (IndexOutOfBoundsException ex) {
            message = "there isn't such user";
            result = false;
        }
        return result;
    }

    public User isCredential(String login, String password) {
        return persistent.isCredential(login, password);
    }

}
