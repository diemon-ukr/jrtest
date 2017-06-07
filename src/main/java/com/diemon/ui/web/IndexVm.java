package com.diemon.ui.web;

import com.diemon.entity.User;
import com.diemon.helper.AppUtil;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

import java.util.*;

/**
 * Created by Diemon on 6/6/2017.
 */
public class IndexVm {

    private final String appInfo = "Test Task for Java Rush, technologies: ZK Spring Hibernate, CRUD Single Table [Maven]";
    private final String[] userStatusData = {"User", "Admin"};

    private User selected;
    private List<User> listOfUsers;

    private ListModelList<User> listModel;
    private List<String> userRoleList;
    private User newUser = new User();

    private String bandBox;

    @Command("filterNames")
    public void onFilterUsers(@BindingParam("filterValue") String filterValue) {
        if(filterValue != null && !filterValue.isEmpty()) {
            listModel.clear();
            for (User user : listOfUsers) {
                if(user.getName().toLowerCase().contains(filterValue)) {
                    listModel.add(user);
                }
            }
        } else {
            listModel.clear();
            listModel.addAll(listOfUsers);
        }
    }


    public IndexVm() {

    }

    @Init
    public void init() {
        setUserRoleList(new ArrayList<>(Arrays.asList(userStatusData)));
        getListOfUsers();
        listModel = new ListModelList<User>(listOfUsers);
    }

    @Command
    @NotifyChange({"selected", "listOfUsers", "bandBox"})
    public void addClick() {
        setSelected(new User());
        bandBox = "";
        listModel.clear();
        listModel.addAll(listOfUsers);
    }

    @Command
    @NotifyChange("bandBox")
    public void delClick() {
        msq("Are you sure to delete this record?", "Index$deleteConfirm", null);
        bandBox = "";
    }

    @GlobalCommand
    @NotifyChange({"selected", "listOfUsers"})
    public void Index$deleteConfirm() {
        if (getSelected() != null) {
            try {
                if (AppUtil.getWebService().delete(getSelected())) {
                    setSelected(null);
                    Clients.showNotification("Deleted successfully.");
                    getListOfUsers();
                    listModel.clear();
                    listModel.addAll(listOfUsers);
                } else {
                    Clients.showNotification("Delete failed.");
                    getListOfUsers();
                    listModel.clear();
                    listModel.addAll(listOfUsers);
                }
            } catch (Exception e) {
                Clients.showNotification("Delete failed.\n" + e.getLocalizedMessage());
            }
        } else {
            Clients.showNotification("Record not found.");
        }
    }

    @Command
    @NotifyChange({"selected", "listOfUsers", "bandBox"})
    public void saveClick() {
        try {
            if (AppUtil.getWebService().save(getSelected())) {
                setSelected(null);
                getListOfUsers();
                bandBox = "";
                listModel.clear();
                listModel.addAll(listOfUsers);
                Clients.showNotification("Saved successfully.");
            }
        } catch (Exception e) {
            Clients.showNotification("Save failed.\n" + e.getLocalizedMessage());
        }
    }

    @Command
    @NotifyChange({"selected", "listOfUsers"})
    public void cancelClick() {
        setSelected(null);
    }

    @Command
    public void infoClick() {
        Clients.showNotification(appInfo + "<br/>" + "First version written by:<br/>Dmitriy Zamogilniy (diemon.ukr@gmail.com)");
    }

    @Command
    @NotifyChange({"bandBox", "listOfUsers"})
    public void refreshClick() {
        setListOfUsers(AppUtil.getWebService().getUsers());
        bandBox = "";
        listModel.clear();
        listModel.addAll(listOfUsers);
    }

    // Confirmation Message Custom Component
    public void msq(String content, String bindMethodYes, String bindMethodNo) {
        Map<String, Object> args = new HashMap<>();

        args.put("sContent", content);
        args.put("sBindMethodYes", bindMethodYes);
        args.put("sBindMethodNo", bindMethodNo);
        Executions.createComponents("/component/msq.zul", null, args);
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

    public ListModelList<User> getListModel() {
        return listModel;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public String getBandBox() {
        return bandBox;
    }

    public void setBandBox(String bandBox) {
        this.bandBox = bandBox;
    }
}
