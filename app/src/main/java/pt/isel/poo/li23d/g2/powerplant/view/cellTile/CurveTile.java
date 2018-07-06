package pt.isel.poo.li23d.g2.powerplant.view.cellTile;

import android.graphics.Canvas;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.CurveCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;

/**
 * DESCRIPTION ABOUT CLASS
 */
public class CurveTile extends PieceView {
    private CurveCell cell;

    public CurveTile(Animator animator) {
        super(animator);
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = (CurveCell) cell;
    }

    @Override
    public void draw(Canvas canvas, int tileWidth) {
        drawDashes(canvas,cell,tileWidth);
    }
}
