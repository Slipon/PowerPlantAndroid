package pt.isel.poo.li23d.g2.powerplant.view.cellTile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import pt.isel.poo.li23d.g2.powerplant.R;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.SourceCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;

/**
 * DESCRIPTION ABOUT CLASS
 */
public class SourceTile extends PieceView {
    private final Bitmap image;
    private SourceCell cell;

    public SourceTile(Animator animator) {
        super(animator);
        this.image = loadBitmap(R.drawable.power);
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = (SourceCell) cell;
    }

    @Override
    public void draw(Canvas canvas, int tileWidth) {
        drawDashes(canvas,cell,tileWidth);
        drawBitmap(canvas,image);
    }
}
