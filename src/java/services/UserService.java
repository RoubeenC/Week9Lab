/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import dataaccess.RoleDB;
import dataaccess.UserDB;
import models.User;
import java.util.List;
import models.Role;

/**
 *
 * @author Roubeen Chaudhry
 */
public class UserService {
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }
    
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public void insert(String email, String firstName, String lastName, String password, int roleId) throws Exception {
        User user = new User(email, firstName, lastName, password);
        RoleDB roledb = new RoleDB();
        Role role = roledb.get(roleId);
        user.setRole(role);
        
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, String firstName, String lastName, String password, int roleId) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleId);
        
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setRole(role);
        
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);

        userDB.delete(user);
    }
    
}
