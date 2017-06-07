package com.diemon.ui.cli;

import com.diemon.entity.UserDto;
import com.diemon.helper.AppUtil;

import java.util.List;

/**
 * Created by Diemon on 6/6/2017.
 */
public class NativeQuery {
    public static void main(String[] args) {
        List<UserDto> lists = AppUtil.getService().getUserQueryByIsAdmin(true);
        System.out.println("Lists size: " + lists.size());
        for (UserDto p : lists) {
            System.out.println("Id: " +p.getId()+ ", Name: " + p.getName()+", Age: "+ p.getAge());
        }
    }
}
