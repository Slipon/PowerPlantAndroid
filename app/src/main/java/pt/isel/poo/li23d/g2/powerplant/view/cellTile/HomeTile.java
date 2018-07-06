package pt.isel.poo.li23d.g2.powerplant.view.cellTile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import pt.isel.poo.li23d.g2.powerplant.R;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.cell.HomeCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;

/**
 * DESCRIPTION ABOUT CLASS
 */
public class HomeTile extends PieceView {
    private final Bitmap[] images;
    private HomeCell cell;
    public HomeTile(Animator animator) {
        super(animator);
        images = new Bitmap[2];
        this.images[0] = loadBitmap(R.drawable.house_off);
        this.images[1] = loadBitmap(R.drawable.house_on);
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = (HomeCell) cell;
    }

    @Override
    public void draw(Canvas canvas, int tileWidth) {
        drawDashes(canvas,cell,tileWidth);
        drawBitmap(canvas,this.cell.isConnected() ? images[1] : images[0]);
    }
}
