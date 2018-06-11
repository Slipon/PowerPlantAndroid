package pt.isel.poo.li23d.g2.powerplant.model.cell;

import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Dash;
import pt.isel.poo.li23d.g2.powerplant.model.Dir;

public class CurveCell extends Cell {
    private int dashes_number;

    public CurveCell(int dashes_number){
        this.dashes_number=dashes_number;
        Dash dash;
        dashes.add(dash = new Dash(Dir.randomDir()));
        dashes.add(dash.withNextDirection());
    }
}