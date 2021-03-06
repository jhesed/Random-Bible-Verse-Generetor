/***
 * VerseAdapter.java: A custom adapter for creating Verse list views
 * @Author: Jhesed Tacadena
 * @Date: November 2016
 * */

package com.jsos.randomverse2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jsos.randomverse2.R;
import com.jsos.randomverse2.VerseDetailsActivity;
import com.jsos.randomverse2.models.Verse;

import java.util.ArrayList;

public class VerseAdapter extends ArrayAdapter<Verse> {

    /* SECTION: Variable Declarations */

    private final String TAG = "verseListAdapter";
    private Context context;

    public VerseAdapter(Context adapterContext, ArrayList<Verse> verseList) {
        super(adapterContext, R.layout.activity_verse_list, verseList);
        context = adapterContext;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        /*
         * Overrides parent for retrieving view
         * */

        View view = convertView;
        final ViewHolder viewHolder;
        Verse verse = getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_verse_list, null);
            viewHolder = new ViewHolder();
        }
        else {
            viewHolder = (ViewHolder)view.getTag();
        }

        // Populate the data into the template view using the data object
        viewHolder.verseId = verse.id;
        viewHolder.verseName = (TextView) view.findViewById(R.id.verseItem);
        viewHolder.verseName.setText(verse.name);

        view.setTag(viewHolder);
        view.setLongClickable(true);

        /* SECTION : Events */

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VerseDetailsActivity.class);
                intent.putExtra("verseId", viewHolder.verseId);
                context.startActivity(intent);
            }
        });
        return view;
    }

    private static class ViewHolder {
        /**
         * Private class for recyclable information
         */
        int verseId;
        TextView verseName;
    }
}