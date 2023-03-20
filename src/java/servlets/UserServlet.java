/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Roubeen Chaudhry
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");

        try{
            if (action != null && action.equals("edit")){
                String email = request.getParameter("email");
                User selUser = us.get(email);
                request.setAttribute("selUser", selUser);
                request.setAttribute("email", selUser.getEmail());
            } else if (action != null && action.equals("delete")){
                String email = request.getParameter("email");
                us.delete(email);
                List<User> users = us.getAll();
                    if(users.isEmpty()){
                        request.setAttribute("errorMessage", "No users found. Please add a user.");
                    }
            }
            
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            
            List<Role> roles = rs.getAll();
            request.setAttribute("roles", roles);
            
        } catch (Exception ex){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "error");
        }
        
    getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService us = new UserService();
        RoleService rs = new RoleService();
              
        try{
            String action = request.getParameter("action");
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String userRole = request.getParameter("role");
            
            switch (action){
                case "add": us.insert(email, firstName, lastName, password, rs.get(userRole));
                    break;
                case "update": us.update(email, firstName, lastName, password, rs.get(userRole));
                    break;
            }
             
            List<User> users = us.getAll();
            request.setAttribute("users", users);
 
        } catch (Exception ex){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "errorcatch");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    
    }

}
