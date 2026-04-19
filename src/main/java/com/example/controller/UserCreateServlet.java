package com.example.controller;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.util.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/create-user")
public class UserCreateServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(firstName, lastName, email, password);

        Map<String, String> errors = ValidationUtil.validate(newUser);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("user", newUser);
            request.getRequestDispatcher("user-form.jsp").forward(request, response);
            return;
        }

        userDAO.save(newUser);

        response.sendRedirect("users");
    }
}
