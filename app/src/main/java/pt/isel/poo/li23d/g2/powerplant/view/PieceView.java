package pt.isel.poo.li23d.g2.powerplant.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Dir;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Tile;

public abstract class PieceView implements Tile {
    private static Paint p;
    private Animator animator;
    public static Context context;
    private static Rect imageDraw;

    public PieceView(Animator animator){
        this.animator=animator;
        p = new Paint();
        imageDraw = new Rect(0,0,0,0);
    }

    public abstract void setCell(Cell cell);

    @Override
    public abstract void draw(Canvas canvas, int side);

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }

    /**
     * DESCRIPTION
     * */
    protected Bitmap loadBitmap(int id){
        return BitmapFactory.decodeResource(context.getResources(),id);
    }

    /**
     * DESCRIPTION
     */
    public void drawBitmap(Canvas canvas, Bitmap image){
        imageDraw.bottom = image.getHeight();
        imageDraw.right = image.getWidth();
        canvas.drawBitmap(image,imageDraw,canvas.getClipBounds(),p);
    }

    /**
     * DESCRIPTION
     * */
    public void drawLine(Canvas canvas, float startX, float startY, float endX, float endY, boolean connected){
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setColor(connected ? Color.GREEN :  Color.DKGRAY);
        p.setStrokeWidth(canvas.getClipBounds().right/3);
        canvas.drawLine(startX,startY,endX,endY,p);
    }

    /**
     * DESCRIPTION
     * */
    protected void drawDashes(Canvas canvas, Cell cell,int tileWidth) {
        for (Dir d : cell.getDirections()) {
            switch (d){
                case UP : drawUp(canvas,tileWidth,cell.isConnected()); break;
                case DOWN : drawDown(canvas,tileWidth,cell.isConnected()); break;
                case LEFT : drawLeft(canvas,tileWidth,cell.isConnected()); break;
                case RIGHT : drawRight(canvas,tileWidth,cell.isConnected()); break;
            }
        }
    }

    /**
     * DESCRIPTION
     * */
    private void drawDown(Canvas canvas, int tileWidth, boolean connected) {
        drawLine(canvas,tileWidth/2,tileWidth/2,tileWidth/2,tileWidth,connected);
    }

    /**
     * DESCRIPTION
     * */
    private void drawRight(Canvas canvas, int tileWidth, boolean connected) {
        drawLine(canvas,tileWidth,tileWidth/2,tileWidth/2,tileWidth/2, connected);
    }

    /**
     * DESCRIPTION
     * */
    private void drawUp(Canvas canvas, int tileWidth, boolean connected) {
        drawLine(canvas,tileWidth/2,tileWidth/2,tileWidth/2,0, connected);
    }

    /**
     * DESCRIPTION
     * */
    private void drawLeft(Canvas canvas, int tileWidth, boolean connected){
        drawLine(canvas,0,tileWidth/2,tileWidth/2,tileWidth/2, connected);
    }
}
