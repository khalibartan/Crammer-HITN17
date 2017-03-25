package com.example.radhe.schedulewish;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by radhe on 25/3/17.
 */

public class ContactsViewHolder {
    TextView contact ;
    CheckBox checkBox;
    public ContactsViewHolder()
    {
    }

    public ContactsViewHolder(TextView textView, CheckBox checkBox)
    {
        this.checkBox = checkBox;
        this.contact = textView;
    }

    public CheckBox getCheckBox()
    {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox)
    {
        this.checkBox = checkBox;
    }

    public TextView getTextView()
    {
        return contact;
    }

    public void setTextView(TextView textView)
    {
        this.contact = textView;
    }

}
