package pt.isel.poo.li23d.g2.powerplant.model.cell;

import pt.isel.poo.li23d.g2.powerplant.model.Cell;
import pt.isel.poo.li23d.g2.powerplant.model.Dash;
import pt.isel.poo.li23d.g2.powerplant.model.Dir;

public class SourceCell extends Cell {
    private int dashes_number;

    public SourceCell(int dashes_number){
        this.dashes_number=dashes_number;
        dashes.add(new Dash(Dir.randomDir()));
        connected=true;
    }

    @Override
    public void setConnection(boolean connectedState) {
        return;
    }
}
