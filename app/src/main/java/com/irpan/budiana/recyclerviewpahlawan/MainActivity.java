package com.irpan.budiana.recyclerviewpahlawan;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvPahlawan;
    private ArrayList<Pahlawan> list = new ArrayList<>();

    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActionBarTitle(title);

        rvPahlawan = findViewById(R.id.rv_pahlawan);
        rvPahlawan.setHasFixedSize(true);

        list.addAll(PahlawanData.getListData());
        showRecyclerList();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId) {
        switch (itemId) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;
            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                title = "Mode Card View";
                showCardView();
                break;
        }
        setActionBarTitle(title);
    }

    private void showRecyclerList() {
        rvPahlawan.setLayoutManager(new LinearLayoutManager(this));
        ListPahlawanAdapter listPahlawanAdapter = new ListPahlawanAdapter(list);
        rvPahlawan.setAdapter(listPahlawanAdapter);

        listPahlawanAdapter.setOnItemClickCallback(new ListPahlawanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pahlawan data) {
                showSelectedPahlawan(data);
            }
        });
    }

    private void showRecyclerGrid() {
        rvPahlawan.setLayoutManager(new GridLayoutManager(this, 2));
        GridPahlawanAdapter gridPahlawanAdapter = new GridPahlawanAdapter(list);
        rvPahlawan.setAdapter(gridPahlawanAdapter);

        gridPahlawanAdapter.setOnItemClickCallback(new GridPahlawanAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Pahlawan data) {
                showSelectedPahlawan(data);
            }
        });
    }

    private void showCardView() {
        rvPahlawan.setLayoutManager(new LinearLayoutManager(this));
        CardViewPahlawanAdapter cardViewPahlawanAdapter =  new CardViewPahlawanAdapter(list);
        rvPahlawan.setAdapter(cardViewPahlawanAdapter);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedPahlawan(Pahlawan pahlawan) {
        Toast.makeText(this, "Kamu memilih " + pahlawan.getNama(), Toast.LENGTH_SHORT).show();
    }
}