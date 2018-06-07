package ru.job4j.servlets;

import ru.job4j.models.User;
import ru.job4j.models.ValidateService;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;


public class UserServlet extends HttpServlet {
    List<User> listUser = new ArrayList();

    private final ValidateService logic = ValidateService.getValidateService();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        Iterator<User> iterator = listUser.iterator();
        while (iterator.hasNext()) {
            res.getWriter().println(iterator.next());
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        choseAction(req);
        doGet(req,res);
    }

    public void choseAction(HttpServletRequest req) {
        Enumeration action = req.getParameterNames();
        boolean end = true;
        while (action.hasMoreElements() && end) {
            switch ((String) action.nextElement()) {
                case "add":
                    logic.add(req);
                    end = false;
                    break;
                case "delete":
                    logic.delete(req);
                    end = false;
                    break;
                case "update":
                    logic.update(req);
                    end = false;
                    break;
            }
        }
    }

    public void addUser(HttpServletRequest req) {
        listUser.add(new User((String) req.getParameter("name")));
    }

    public void deleteUser(HttpServletRequest req) {
        listUser.remove(Integer.parseInt(req.getParameter("index")));
    }

    public void updateUser(HttpServletRequest req) {
        listUser.get(Integer.parseInt(req.getParameter("index"))).setName((String) req.getParameter("name"));
    }
}