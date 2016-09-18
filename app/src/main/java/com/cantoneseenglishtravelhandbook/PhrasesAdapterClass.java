package com.cantoneseenglishtravelhandbook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.CLIPBOARD_SERVICE;

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
        final TextView travelPhrase= (TextView) row.findViewById(R.id.travelPhraseTextView);
        //travelPhrase.setTypeface(null, Typeface.BOLD);
        travelPhrase.setTextColor(Color.BLACK);

        TextView homePhrase = (TextView) row.findViewById(R.id.homePhraseTextView);
        TextView pronounciation = (TextView) row.findViewById(R.id.pronounciationTextView);

        travelPhrase.setText((CharSequence) travelPhraseData.get(position).getTravelPhrase());
        homePhrase.setText((CharSequence) travelPhraseData.get(position).getHomePhrase());
        pronounciation.setText((CharSequence) travelPhraseData.get(position).getPronounciation());

        ImageButton copyPhraseButton = (ImageButton) row.findViewById(R.id.copyImageButton);
        copyPhraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentLinearLayout = ((View) view.getParent());
                ListView parentListview = ((ListView) parentLinearLayout.getParent());
                int position = parentListview.getPositionForView(view);
                String phrase = travelPhraseData.get(position).getTravelPhrase() + "\n" + travelPhraseData.get(position).getHomePhrase() + "\n" + travelPhraseData.get(position).getPronounciation();
                Toast.makeText(context, "Copied to Notepad" + "\n" + phrase, Toast.LENGTH_SHORT).show();
//                ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
//                ClipData clip = ClipData.newPlainText("label", phrase);
//                clipboard.setPrimaryClip(clip);

                NotepadDatabaseHelper notepadDBHelper;
                notepadDBHelper = setupDatabaseHelper();
                notepadDBHelper.insertNotepadData(travelPhraseData.get(position).getHomePhrase(), travelPhraseData.get(position).getTravelPhrase(), new Date());
                notepadDBHelper.close();
            }
        });
        //return super.getView(position, convertView, parent);
        return row;
    }

    public PhrasesAdapterClass(Context _context, ArrayList<TravelPhraseData> _travelPhraseData) {
        super(_context, R.layout.phrases_row, _travelPhraseData);

        this.context = _context;
        this.travelPhraseData = _travelPhraseData;

    }

    public NotepadDatabaseHelper setupDatabaseHelper()
    {
        NotepadDatabaseHelper notepadDBHelper = new NotepadDatabaseHelper(context);

        try {
            notepadDBHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            notepadDBHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
        return notepadDBHelper;
    }
}
