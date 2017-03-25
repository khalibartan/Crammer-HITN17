package com.example.radhe.schedulewish;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by radhe on 25/3/17.
 */

class CustomArrayAdapter extends ArrayAdapter<Contacts>{
    private LayoutInflater inflater;

    public CustomArrayAdapter(Context context, List<Contacts> contacts)
    {
        super(context, R.layout.list_item, R.id.ind_name, contacts);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Planet to display
        Contacts c  = this.getItem(position);

        // The child views in each row.
        CheckBox checkBox;
        TextView textView;

        // Create a new row view
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.list_item, null);

            // Find the child views.
            textView = (TextView) convertView
                    .findViewById(R.id.ind_name);
            checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);

            // Optimization: Tag the row with it's child views, so we don't
            // have to
            // call findViewById() later when we reuse the row.
            convertView.setTag(new ContactsViewHolder(textView, checkBox));

            // If CheckBox is toggled, update the planet it is tagged with.
            checkBox.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    CheckBox cb = (CheckBox) v;
                    Contacts contact = (Contacts) cb.getTag();
                    contact.setChecked(cb.isChecked());
                }
            });
        }
        // Reuse existing row view
        else
        {
            // Because we use a ViewHolder, we avoid having to call
            // findViewById().
            ContactsViewHolder viewHolder = (ContactsViewHolder) convertView
                    .getTag();
            checkBox = viewHolder.getCheckBox();
            textView = viewHolder.getTextView();
        }

        // Tag the CheckBox with the Planet it is displaying, so that we can
        // access the planet in onClick() when the CheckBox is toggled.
        checkBox.setTag(c);

        // Display planet data
        checkBox.setChecked(c.isChecked());
        textView.setText(c.getName());
        return convertView;
    }

}
