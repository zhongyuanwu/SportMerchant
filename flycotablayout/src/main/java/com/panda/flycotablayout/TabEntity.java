package com.panda.flycotablayout;


import com.panda.flycotablayout.listener.CustomTabEntity;

import java.io.Serializable;
import java.util.ArrayList;

public class TabEntity implements CustomTabEntity, Serializable {
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;
    public int menuId;

    /**
     * menuId : 10001
     * parentId : 100
     * menuName : 我的收藏
     * grade : 2
     * menuType : 6
     * field1 : null
     * count : 0
     */

    public int parentId;
    public String menuName;
    public int grade;
    public int menuType;
    public String field1;
    public String field2;
    public int count;
    public ArrayList<TabEntity> subList;
    public ArrayList<TabEntity> topMenuList;
    public boolean isVerticalSelect;
    public boolean isHorizontalSelect;

    //收藏菜单字段
    public String name;
    public int sportId;

    public TabEntity() {

    }

    public TabEntity(String title, int unSelectedIcon, int selectedIcon) {
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
