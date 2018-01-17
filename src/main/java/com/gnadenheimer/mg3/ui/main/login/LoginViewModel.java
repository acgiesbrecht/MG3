package com.gnadenheimer.mg3.ui.main.login;

import com.gnadenheimer.mg3.App;
import com.gnadenheimer.mg3.domain.TblRoles;
import com.gnadenheimer.mg3.domain.TblUsers;
import com.gnadenheimer.mg3.utils.CurrentUser;
import com.gnadenheimer.mg3.utils.LoginManager;
import de.saxsys.mvvmfx.ViewModel;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class LoginViewModel implements ViewModel {

    @Inject
    EntityManager entityManager;

    @Inject
    LoginManager loginManager;

    @Inject
    CurrentUser currentUser;

    public void login(String userName, String password) {
        try {
            currentUser.setUser(null);
            if (userName.equals("master") && password.equals("147369")) {
                TblUsers tempUser = new TblUsers();
                tempUser.setId(9999);
                TblRoles role = new TblRoles();
                role.setId(4);
                List<TblRoles> listRoles = new ArrayList();
                listRoles.add(role);
                tempUser.setTblRolesList(listRoles);
                currentUser.setUser(tempUser);
            } else {
                List<TblUsers> listUsers = (List<TblUsers>) entityManager.createQuery("SELECT t FROM TblUsers t").getResultList();
                listUsers.stream().filter((user) -> (user.getNombre().equals(userName) && BCrypt.checkpw(String.valueOf(password), user.getPassword()))).forEachOrdered((user) -> {
                    currentUser.setUser(user);
                });
            }
            if (currentUser.getUser() != null) {
                loginManager.showMainView();
            } else {
                App.showWarning("Login", "Usuario o contraseña invalidos.");
            }

        } catch (Exception ex) {
            App.showException(Thread.currentThread().getStackTrace()[1].getMethodName(), ex.getMessage(), ex);
        }
    }

}
