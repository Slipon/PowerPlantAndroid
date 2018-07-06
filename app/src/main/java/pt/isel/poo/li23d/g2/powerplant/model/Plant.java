package pt.isel.poo.li23d.g2.powerplant.model;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import pt.isel.poo.li23d.g2.powerplant.GameActivity;
import pt.isel.poo.li23d.g2.powerplant.model.cell.SourceCell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.SpaceCell;

public class Plant {
    private int width, height;
    private Cell[][] map;
    private int moves;
    private Listener listener;
    private GameActivity game = new GameActivity();
    private int movesAux;
    private int level;


    public Plant(int height, int width) {
        this.width=width;
        this.height=height;
        map = new Cell[height][width];
    }

    public void init() {
        boardRefactor();
    }

    public void putCell(int l, int c, Cell cell) {
        map[l][c]=cell;
    }

    public Cell getCell(int l, int c) {
        return (l<0 || l>height || c<0 || c>width) ? null : map[l][c];
    }

    public int getMoves() {
        return moves;
    }

    public boolean isCompleted() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(!(map[i][j] instanceof SpaceCell) && !map[i][j].isConnected())return false;
            }
        }
        return true;
    }


    public boolean touch(int line, int col) {
        if(map[line][col] instanceof SpaceCell) return false;
        ++moves;
        map[line][col].rotateCell();
        boardRefactor();
        rePaintBoard();
        return true;
    }

    private void rePaintBoard() {
        for (int i = 0; i <height; i++) {
            for (int j = 0; j < width; j++) {
                listener.cellChanged(i,j,map[i][j]);
            }
        }
    }

    private void boardRefactor() {
        clearBoard();
        for (int i = 0; i <height; i++) {
            for (int j = 0; j <width; j++) {
                if(map[i][j] instanceof SourceCell){
                    fillConnections(map[i][j], i, j);
                }
            }
        }
    }

    private void fillConnections(Cell cell, int line, int col) {
        for(Dir d : cell.getDirections()){
            Cell neighbour = getNeighbourIfPossible(line,col,d);
            if (neighbour==null)continue;
            if(!neighbour.isConnected() && checkConnection(neighbour,d)){
                neighbour.setConnection(true);
                fillConnections(neighbour,line+d.deltaLine,col+d.deltaCol);
            }
        }
    }

    private boolean checkConnection(Cell neighbour, Dir currDir) {
        return neighbour.getDirections().contains(currDir.opposite());
    }

    private Cell getNeighbourIfPossible(int line, int col, Dir d) {
        line=line+d.deltaLine;
        col=col+d.deltaCol;
        if(line<0 || line>=height || col<0 || col>=width) return null;
        return map[line][col];
    }


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void cellChanged(int lin, int col, Cell cell);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    private void clearBoard(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                    map[i][j].setConnection(false);
            }
        }
    }

    public final void save(OutputStream out) throws IOException{
        try(Closeable objectOutputStream = new ObjectOutputStream(out)){
            ObjectOutputStream outStream = (ObjectOutputStream) objectOutputStream;
            outStream.writeInt(level = game.getLevel());
            outStream.writeInt(movesAux = getMoves());
        }
    }

    public final void load(InputStream in)throws IOException{
        try(Closeable objectInputStream = new ObjectInputStream(in)) {
            ObjectInputStream inStream = (ObjectInputStream) objectInputStream;
            level = inStream.readInt();
            movesAux = inStream.readInt();
        }
    }
 }
