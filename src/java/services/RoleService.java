/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Role;
import dataaccess.RoleDB;
import java.util.List;

/**
 *
 * @author Roubeen Chaudhry
 */
public class RoleService {
    
    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        return roles;
    }
    
    public int get(String roleId) throws Exception {
         if (roleId.equals("system admin")) {
            return 1;
        } else {
            return 2;
        }
    }
    
}
