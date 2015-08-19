package fr.bigmag.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.bigmag.R;
import fr.bigmag.pojo.Message;

public class MessageAdapter extends ArrayAdapter<Message> {
    public MessageAdapter(Context context, ArrayList<Message> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Message message = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView messageName = (TextView) convertView.findViewById(R.id.messageTitle);
        messageName.setText(message.name);

        TextView messagePromotion = (TextView) convertView.findViewById(R.id.messageDescription);
        messagePromotion.setText(message.promotion);

        // Return the completed view to render on screen
        return convertView;
    }
}