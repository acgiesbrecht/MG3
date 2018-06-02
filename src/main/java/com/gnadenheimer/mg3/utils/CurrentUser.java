/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils;

import com.gnadenheimer.mg3.model.domain.TblRoles;
import com.gnadenheimer.mg3.model.domain.TblUsers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrentUser {

    private TblUsers user;

    @PostConstruct
    public void afterCreate() {
        System.out.println("User created");
    }

    //private static final CurrentUser currentUser = new CurrentUser();

    /* A private Constructor prevents any other
     * class from instantiating.
     *
    private CurrentUser() {
    }

    /* Static 'instance' method
    public static CurrentUser getInstance() {
        return currentUser;
    }*/

    /**
     * @return the user
     */

    public TblUsers getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(TblUsers user) {
        TblUsers userOld = this.user;
        this.user = user;
    }

    public boolean hasRole(Integer roleLevel) {

        if (user != null) {
            for (TblRoles r : user.getTblRolesList()) {
                if (r.getId() >= roleLevel) {
                    return true;
                }
            }
        }
        return false;
    }

}
