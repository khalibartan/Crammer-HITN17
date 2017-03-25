package com.example.radhe.schedulewish;

/**
 * Created by radhe on 25/3/17.
 */

class Contacts
{
    private String details = "";
    private boolean checked = false;

    public Contacts()
    {
    }

    public Contacts(String name)
    {
        this.details = name;
    }

    public Contacts(String name, boolean checked)
    {
        this.details = name;
        this.checked = checked;
    }

    public String getName()
    {
        return details;
    }

    public void setName(String name)
    {
        this.details = name;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public String toString()
    {
        return details;
    }

    public void toggleChecked()
    {
        checked = !checked;
    }
}

