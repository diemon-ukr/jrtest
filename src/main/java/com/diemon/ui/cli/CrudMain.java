package com.diemon.ui.cli;

import com.diemon.entity.User;
import com.diemon.helper.AppUtil;

import java.util.Date;

/**
 * Created by Diemon on 6/6/2017.
 */
public class CrudMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // CLEAR EXISTING DB DATA MANUALLY
        if (AppUtil.getService().truncateDb()) {
            System.out.println("Truncate existing DB Data done!");
        }
        System.out.println("");

        // CREATE / INSERT
        User p1 = new User();
        p1.setName("Test");
        p1.setAge(35);
        p1.setIsAdmin(true);
        p1.setCreatedDate(new Date());
        if (AppUtil.getService().save(p1)) {
            System.out.println("p1 was saved successfully!");
        }
        System.out.println("");

        // UPDATE
        p1.setAge(99);
        if (AppUtil.getService().save(p1)) {
            System.out.println("p1 was updated successfully!");
        }
        System.out.println("");

        // READ
        User p2 = AppUtil.getService().getUsers().get(0);
        System.out.println("Table Index 0/ Top 1");
        System.out.println("User Name: " + p2.getName());
        System.out.println("Age      : " + p2.getAge());
        System.out.println("Is Admin     : " + p2.getIsAdmin());
        System.out.println("CreatedDate   : " + p2.getCreatedDate());


        // DELETE
//        if (AppUtil.getService().delete(p2)) {
//            System.out.println("Delete done!");
//        }
//        System.out.println("");

        // QUERY ALL
        System.out.println("Current DB Size: " + AppUtil.getService().getUsers().size());

    }

}
