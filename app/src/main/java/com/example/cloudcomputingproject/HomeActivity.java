package com.example.cloudcomputingproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;

public class HomeActivity extends AppCompatActivity {
    GridLayout mainGrid;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        if(finalI == 0){
                            Intent intent = new Intent(HomeActivity.this, AllActivity.class);
                            startActivity(intent);
                        }else if(finalI == 1){
                            Intent intent = new Intent(HomeActivity.this, preventionActivity.class);
                            startActivity(intent);
                        }else if (finalI == 2){
                            Intent intent = new Intent(HomeActivity.this, staticActivity.class);
                            startActivity(intent);
                        }else if (finalI == 3){
                            Intent intent = new Intent(HomeActivity.this, aboutActivity.class);
                            startActivity(intent);
                        }else if (finalI == 4){
                            Intent intent = new Intent(HomeActivity.this, customActivity.class);
                            startActivity(intent);
                        }else if (finalI == 5){
                            Intent intent = new Intent(HomeActivity.this, LogOutActivity.class);
                            startActivity(intent);
                        }

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}