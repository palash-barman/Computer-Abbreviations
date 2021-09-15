package com.example.computerabbreviations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CustomAdaptor customAdaptor;
    List<String> listDataHeader;
    HashMap<String,List<String>> listDataChild;

    private  int lastexpandablelist = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareListData();

        expandableListView = findViewById(R.id.expandablelist_id);
        customAdaptor = new CustomAdaptor (this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdaptor);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                String groupname= listDataHeader.get(i);

                Toast.makeText(MainActivity.this, "Group name : "+groupname,Toast.LENGTH_SHORT).show();


                return false;
            }
        });



        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if (lastexpandablelist !=-1 && lastexpandablelist !=i){

                    expandableListView.collapseGroup(lastexpandablelist);

                }
                lastexpandablelist  =i;

            }
        });


    }

    public void prepareListData(){

        String[] headerString = getResources().getStringArray(R.array.Abbreviations_name);
        String[] childString = getResources().getStringArray(R.array.Child_name);
        listDataHeader = new ArrayList<>();
        listDataChild =new HashMap<>();

        for (int i=0;i<headerString.length;i++){

            listDataHeader.add(headerString[i]);

            List<String> child = new ArrayList<>();
            child.add(childString[i]);


            listDataChild.put(listDataHeader.get(i),child);


        }


    }
}