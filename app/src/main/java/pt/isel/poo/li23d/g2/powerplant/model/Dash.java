package pt.isel.poo.li23d.g2.powerplant.model;

public class Dash{
    private Dir dir;

    public Dash(Dir dir){
        this.dir=dir;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dash withOppositeDirection() {
        return new Dash(dir.opposite());
    }

    public Dash withNextDirection() {
        return new Dash(dir.nextPos());
    }
}