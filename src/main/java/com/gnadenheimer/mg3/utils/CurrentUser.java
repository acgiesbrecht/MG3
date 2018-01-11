/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg3.utils;

import com.gnadenheimer.mg3.domain.TblRoles;
import com.gnadenheimer.mg3.domain.TblUsers;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CurrentUser {

    private TblUsers user;

    private static final CurrentUser currentUser = new CurrentUser();

    /* A private Constructor prevents any other
     * class from instantiating.
     */

    private CurrentUser() {
    }

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
