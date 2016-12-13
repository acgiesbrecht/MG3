/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnadenheimer.mg.utils;

import com.gnadenheimer.mg.domain.TblRoles;
import com.gnadenheimer.mg.domain.TblUsers;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CurrentUser extends Component {

    private PropertyChangeSupport propChangeSupport = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propChangeSupport.addPropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propChangeSupport.removePropertyChangeListener(listener);
    }

    private TblUsers user;

    private static CurrentUser currentUser = new CurrentUser();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private CurrentUser() {
    }

    /* Static 'instance' method */
    public static CurrentUser getInstance() {
        return currentUser;
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
        propChangeSupport.firePropertyChange("user", userOld, user);
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
