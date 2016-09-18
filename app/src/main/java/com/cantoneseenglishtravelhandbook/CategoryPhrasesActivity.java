package com.cantoneseenglishtravelhandbook;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryPhrasesActivity extends AppCompatActivity {

    ArrayList<TravelPhraseData> travelList;
    ListView phrasesListView;
    ArrayAdapter<String> phraseListViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_category_phrases);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Phrases");

        travelList = getIntent().getParcelableArrayListExtra("phrases");

        setupPhrasesListView();

    }


    public void setupPhrasesListView() {
        final Context context = this;
        phrasesListView = (ListView) findViewById(R.id.phrasesListView);
        //phrasesListView.setOnClickListener(this);
        populatePhrasesListView(travelList);




    }

    public void populatePhrasesListView(ArrayList travelPhrasesList) {
//        ArrayList<String> list = new ArrayList<String>();
//        for (int i = 0; i < travelPhrasesList.size(); ++i) {
//            TravelPhraseData tempTravelPhraseData = new TravelPhraseData();
//            tempTravelPhraseData = (TravelPhraseData) travelPhrasesList.get(i);
//            list.add(tempTravelPhraseData.getTravelPhrase());
//        }
        //phraseListViewAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list);
        //phrasesListView.setAdapter(phraseListViewAdapter);

        PhrasesAdapterClass phrasesAdapter = new PhrasesAdapterClass(this, travelPhrasesList);
        phrasesListView.setAdapter(phrasesAdapter);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
