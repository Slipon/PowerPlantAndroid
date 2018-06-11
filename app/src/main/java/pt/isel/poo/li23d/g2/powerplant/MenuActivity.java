package pt.isel.poo.li23d.g2.powerplant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button exit = findViewById(R.id.button_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button play = findViewById(R.id.button_play);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent play = new Intent(MenuActivity.this, GameActivity.class);
                MenuActivity.this.startActivity(play);
            }
        });

        Button levels = findViewById(R.id.button_levels);
        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent levels = new Intent(MenuActivity.this, LevelsActivity.class);
                MenuActivity.this.startActivity(levels);
            }
        });

        Button highScores = findViewById(R.id.button_scores);
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highScores = new Intent(MenuActivity.this, HighScoresActivity.class);
                MenuActivity.this.startActivity(highScores);
            }
        });
    }
}
