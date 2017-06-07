package com.diemon.ui.web;

import com.diemon.entity.User;
import com.diemon.helper.AppUtil;
import com.diemon.ui.web.template.CrudFormTemplate;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Diemon on 6/7/2017.
 */
public class Menu02Vm extends CrudFormTemplate {

    private final String appInfo = "ZK Spring Hibernate CRUD Single Table [Maven] (Toolbar Template)";
    private final String[] userStatusData = {"User", "Admin"};

    private User selected;
    private List<User> listOfUsers;
    private List<String> userRoleList;
    private ListModelList<User> listModel;

    private String bandBox;


    public Menu02Vm() {
    }

    @Init()
    public void init() {
        super.init();
        setVisibleRefresh(false);
        setVisibleInfo(true);

        setUserRoleList(new ArrayList<>(Arrays.asList(userStatusData)));
        getListOfUsers();

        listModel = new ListModelList<User>(listOfUsers);

    }

    @Command
    @Override
    @NotifyChange({"selected", "listOfUsers"})
    public void addClick() {
        setSelected(new User());
        super.addClick();
    }

    @Override
    @Command
    public void selectedRecord() {
        super.selectedRecord();
    }

    @Command
    @Override
    public void editClick() {
        super.editClick();
    }

    @Command
    @Override
    @NotifyChange({"selected", "listOfUsers"})
    public void deleteClick() {
        if (getSelected() != null) {
            try {
                if (AppUtil.getWebService().delete(getSelected())) {
                    setSelected(null);
                    super.deleteClick();
                    Clients.showNotification("Delete successful.");
                } else {
                    Clients.showNotification("Delete failed.");
                }
            } catch (Exception e) {
                Clients.showNotification("Delete failed.\n" + e.getLocalizedMessage());
            }
        } else {
            Clients.showNotification("Record not found.");
        }
    }

    @Command
    @Override
    @NotifyChange({"selected", "listOfUsers"})
    public void saveClick() {
        try {
            if (AppUtil.getWebService().save(getSelected())) {
                setSelected(null);
                getListOfUsers();
                super.saveClick();
                Clients.showNotification("Save successful.");
            }
        } catch (Exception e) {
            Clients.showNotification("Save failed.\n" + e.getLocalizedMessage());
        }
    }

    @Command
    @Override
    @NotifyChange({"selected", "listOfUsers"})
    public void cancelClick() {
        setSelected(null);
        super.cancelClick();
    }

    // ============== Setter & Getter ====================
    public User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        this.selected = selected;
    }

    public List<String> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<String> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public List<User> getListOfUsers() {
        listOfUsers = AppUtil.getWebService().getUsers();
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

}
