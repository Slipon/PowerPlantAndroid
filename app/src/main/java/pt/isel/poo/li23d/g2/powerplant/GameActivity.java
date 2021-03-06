package pt.isel.poo.li23d.g2.powerplant;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Loader;
import pt.isel.poo.li23d.g2.powerplant.model.Plant;
import pt.isel.poo.li23d.g2.powerplant.model.cell.BranchCell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.CurveCell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.HomeCell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.LineCell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.SourceCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.cellTile.BranchTile;
import pt.isel.poo.li23d.g2.powerplant.view.cellTile.CurveTile;
import pt.isel.poo.li23d.g2.powerplant.view.cellTile.HomeTile;
import pt.isel.poo.li23d.g2.powerplant.view.cellTile.LineTile;
import pt.isel.poo.li23d.g2.powerplant.view.cellTile.SourceTile;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;
import pt.isel.poo.li23d.g2.powerplant.view.tile.OnTileTouchListener;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Tile;
import pt.isel.poo.li23d.g2.powerplant.view.tile.TilePanel;

public class GameActivity extends Activity {
    private TilePanel panel;
    private Plant model;
    private Animator animator;
    private Tile tiles[][];
    private BufferedReader br;
    private InputStream i;
    private Button backMenu, nextLevel;
    private int level=1;
    private Loader loader;
    private TextView movesView;
    private TextView levelView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        backMenu = findViewById(R.id.button_back);
        backMenu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onBackPressed();
          }});

        nextLevel = findViewById(R.id.button_nextLevel);
        nextLevel.setEnabled(false);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level<4) {
                    loadLevel(++level);
                    loadLevelView();
                    levelView.setText(getResources().getString(R.string.level)+level);
                    movesView.setText(getResources().getString(R.string.moves)+0);
                    nextLevel.setEnabled(false);
                }
                else Toast.makeText(getApplicationContext(),getResources().getString(R.string.noLevels),Toast.LENGTH_LONG).show();
            }
        });

        i = getResources().openRawResource(R.raw.levels);
        br = new BufferedReader(new InputStreamReader(i));
        loader = new Loader(new Scanner(br));

        panel= findViewById(R.id.panel);
        panel.setBackgroundColor(Color.rgb(200,255,200));
        movesView = findViewById(R.id.text_moves);
        levelView = findViewById(R.id.text_level);
        movesView.setText(getResources().getString(R.string.moves)+0);
        levelView.setText(getResources().getString(R.string.level)+level);

        loadLevel(level);
        loadLevelView();
    }

    /**
     * Method to save the state of the game.
     * Called when there's a screen rotation
     */
    @Override
    protected void onSaveInstanceState(Bundle state) {
        //TODO
        super.onSaveInstanceState(state);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try{
            model.save(os);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to load the game with the previous saved game
     * Called when there's a screen rotation, after the save.
     */
    @Override
    protected void onRestoreInstanceState(Bundle state) {
        //TODO
        super.onRestoreInstanceState(state);
        InputStream in = null;
        try {
            model.load(in);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Method to load the current level VIEW to play.
     */
    private void loadLevelView() {
        panel.setSize(model.getWidth(),model.getHeight());
        tiles = new Tile[model.getWidth()][model.getHeight()];
        panel.setAllTiles(tiles);
        panel.setListener(new MyPanelListener());
        animator = panel.getAnimator();
        PieceView.context = this;
        paintBoard();
    }

    /**
     * Method to load the current level to play.
     * @param level has the current level to play.
     */
    private void loadLevel(int level) {
        try {
            model = loader.load(level);
            model.setListener(new ModelListener());
        } catch (Loader.LevelFormatException e) {
            Log.e("GAME ACTIVITY",e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to paint the game board with all the respective tiles.
     */
    private void paintBoard() {
        for (int j = 0; j < model.getHeight(); j++) {
            for (int k = 0; k < model.getWidth(); k++) {
                paintCell(model.getCell(j,k), j, k);
            }
        }
    }

    /**
     * Method that checks the type of each tile and defines it.
     */
    private void paintCell(Cell cell, int j, int k) {
        PieceView view = null;

        if(cell instanceof HomeCell){
            view = new HomeTile(animator);
        }
        else if(cell instanceof SourceCell){
            view = new SourceTile(animator);
        }
        else if(cell instanceof BranchCell){
            view = new BranchTile(animator);
        }
        else if(cell instanceof CurveCell){
            view = new CurveTile(animator);
        }
        else if(cell instanceof LineCell){
            view = new LineTile(animator);
        }

        if(view!=null) {
            view.setCell(cell);
        }

        if(panel.getTile(k,j)!= view){
            panel.setTile(k,j,view);
        }
    }

    /**
     * DESCRIPTION
     */
    private class MyPanelListener implements OnTileTouchListener {

        @Override
        public boolean onClick(int xTile, int yTile) {
            model.touch(yTile,xTile);
            movesView.setText(getResources().getString(R.string.moves)+ model.getMoves());
            if(model.isCompleted()){
                nextLevel.setEnabled(true);
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.completed),Toast.LENGTH_LONG).show();
            }
            else nextLevel.setEnabled(false);
            return true;
        }

        @Override
        public boolean onDrag(int xFrom, int yFrom, int xTo, int yTo) {
            return false;
        }

        @Override
        public void onDragEnd(int x, int y) {

        }

        @Override
        public void onDragCancel() {

        }
    }

    private class ModelListener implements Plant.Listener{

        @Override
        public void cellChanged(int lin, int col, Cell cell) {
            panel.invalidate(lin,col);
        }
    }


}
