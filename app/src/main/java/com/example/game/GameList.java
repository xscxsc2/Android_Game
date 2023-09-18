package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class GameList extends AppCompatActivity {
    public static GameList instance;
    private ListView listView = null;
    private String[] names = null;
    private String[] infos = null;

    private List<GameListItem> gameList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        instance = this;

        InitData();
        listView = findViewById(R.id.Game_List);

        ListAdapter listAdapter = new ListAdapter(GameList.this, R.layout.game_list_item, gameList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(GameList.this, Game.class);
                MainActivity.instance.finish();
                startActivity(intent);
            }
        });
    }

    void InitData(){
        names = getResources().getStringArray(R.array.gameName);
        infos = getResources().getStringArray(R.array.gameInfo);
        TypedArray images = getResources().obtainTypedArray(R.array.gameList_Image);

        int len = names.length;

        for(int i = 0; i < len; i ++){
            GameListItem item = new GameListItem();

            item.SetItemId(i);
            item.SetImageId(images.getResourceId(i,0));
            Log.d("ListAdapter", String.valueOf(images.getResourceId(i,0)));
            Log.d("ListAdapter", String.valueOf(item.GetImageId()));
            item.SetGameName(names[i]);
            item.SetGameInfo(infos[i]);

            gameList.add(item);
        }
    }
}