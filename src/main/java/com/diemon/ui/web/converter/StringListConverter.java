package com.diemon.ui.web.converter;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zul.Combobox;

import java.util.List;

/**
 * Created by Diemon on 6/6/2017.
 */
public class StringListConverter implements Converter<String, Boolean, Combobox> {

    @Override
    public String coerceToUi(Boolean b, Combobox c, BindContext bc) {
        if (b != null) {
            List<String> list = (List<String>) c.getModel();

            if(b==false) {return list.get(0); }
            else { return list.get(1); }
        } else {
            return "";
        }
    }

    @Override
    public Boolean coerceToBean(String u, Combobox c, BindContext bc) {
        if (u != null && !u.isEmpty()) {
            List<String> list = (List<String>) c.getModel();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(u)) {
                    if(i==0) { return false; }
                    else if (i==1) { return true; }
                }
            }
            return null;
        } else {
            return null;
        }
    }
}