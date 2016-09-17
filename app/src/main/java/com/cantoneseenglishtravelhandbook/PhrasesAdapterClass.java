package com.cantoneseenglishtravelhandbook;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Danny on 28/08/2016.
 */

public class PhrasesAdapterClass extends ArrayAdapter {

    Context context;
    ArrayList<TravelPhraseData> travelPhraseData;
    LayoutInflater inflater;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.phrases_row, parent, false);
        TextView travelPhrase= (TextView) row.findViewById(R.id.travelPhraseTextView);
        //travelPhrase.setTypeface(null, Typeface.BOLD);
        travelPhrase.setTextColor(Color.BLACK);

        TextView homePhrase = (TextView) row.findViewById(R.id.homePhraseTextView);
        TextView pronounciation = (TextView) row.findViewById(R.id.pronounciationTextView);

        travelPhrase.setText((CharSequence) travelPhraseData.get(position).getTravelPhrase());
        homePhrase.setText((CharSequence) travelPhraseData.get(position).getHomePhrase());
        pronounciation.setText((CharSequence) travelPhraseData.get(position).getPronounciation());

        //return super.getView(position, convertView, parent);
        return row;
    }

    public PhrasesAdapterClass(Context _context, ArrayList<TravelPhraseData> _travelPhraseData) {
        super(_context, R.layout.phrases_row, _travelPhraseData);

        this.context = _context;
        this.travelPhraseData = _travelPhraseData;

    }
}
