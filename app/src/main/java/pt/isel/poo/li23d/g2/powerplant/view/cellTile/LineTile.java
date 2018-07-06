package pt.isel.poo.li23d.g2.powerplant.view.cellTile;

import android.graphics.Canvas;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Dir;
import pt.isel.poo.li23d.g2.powerplant.model.cell.LineCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;

/**
 * DESCRIPTION ABOUT CLASS
 */
public class LineTile extends PieceView {
    private LineCell cell;

    public LineTile(Animator animator) {
        super(animator);
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = (LineCell) cell;
    }

    @Override
    public void draw(Canvas canvas, int tileWidth) {
        if((cell.getDirections().get(0) == Dir.UP) || cell.getDirections().get(0) == Dir.DOWN){
            drawVertical(canvas,tileWidth,cell.isConnected());
        }
        else drawHorizontal(canvas,tileWidth,cell.isConnected());
    }

    private void drawVertical(Canvas canvas, int tileWidth, boolean connected) {
        drawLine(canvas,tileWidth/2,0,tileWidth/2,tileWidth, connected);
    }

    private void drawHorizontal(Canvas canvas, int tileWidth, boolean connected) {
        drawLine(canvas,0,tileWidth/2,tileWidth,tileWidth/2, connected);
    }
}
