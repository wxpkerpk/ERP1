package com.guotion.material.web.constant;

/**
 * Created by wx on 2015/11/3.
 */
public class Node {
    int id;
    String name;
    int _parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_parentId() {
        return _parentId;
    }

    public void set_parentId(int _parentId) {
        this._parentId = _parentId;
    }
}
