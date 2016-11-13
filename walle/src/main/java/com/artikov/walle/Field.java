package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 15:53
 *
 * @author Artur Artikov
 */
public class Field<T> {
    private String mName;

    public Field(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}