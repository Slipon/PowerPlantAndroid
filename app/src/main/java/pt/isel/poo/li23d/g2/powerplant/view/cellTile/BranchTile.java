package pt.isel.poo.li23d.g2.powerplant.view.cellTile;

import android.graphics.Canvas;
import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Dir;
import pt.isel.poo.li23d.g2.powerplant.model.cell.BranchCell;
import pt.isel.poo.li23d.g2.powerplant.view.PieceView;
import pt.isel.poo.li23d.g2.powerplant.view.tile.Animator;

public class BranchTile extends PieceView {
    private BranchCell cell;

    public BranchTile(Animator animator) {
        super(animator);
    }

    @Override
    public void setCell(Cell cell) {
        this.cell = (BranchCell) cell;
    }

    @Override
    public void draw(Canvas canvas, int tileWidth) {
        drawDashes(canvas, cell, tileWidth);
    }
}
