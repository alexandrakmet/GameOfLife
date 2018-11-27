package sample.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Alexandra on 14.11.2018.
 */
public class Cell extends Rectangle {
    public static final Color ALIVE_COLOR = Color.YELLOW;
    public static final Color DEAD_COLOR = Color.GRAY;
    public static final Color DROP_COLOR = Color.GREENYELLOW;

    private boolean isAlive;
    private boolean isAliveNext;

    private int row = -1;
    private int col = -1;


    public Cell(boolean isAlive, boolean isAliveNext, double size) {
        super(size, size);
        if (isAlive) {
            setFill(ALIVE_COLOR);
        } else {
            setFill(DEAD_COLOR);
        }

        this.isAlive = isAlive;
        this.isAliveNext = isAliveNext;
    }

    public Cell(boolean isAlive, boolean isAliveNext, double size, int row, int col) {
        super(size, size);
        this.row = row;
        this.col = col;
        if (isAlive) {
            setFill(ALIVE_COLOR);
        } else {
            setFill(DEAD_COLOR);//
        }

        this.isAlive = isAlive;
        this.isAliveNext = isAliveNext;
    }


    private void updateColor() {
        if (isAlive) {
            setFill(ALIVE_COLOR);
        } else {
            setFill(DEAD_COLOR);
        }
    }


    public void updateNextState(int numLiveNeighbours) {
        if (isAlive) {
            liveChange(numLiveNeighbours);
        } else {
            deadChange(numLiveNeighbours);
        }
    }

    public void updateCurrentState() {
        isAlive = isAliveNext;
        updateColor();
    }

    private void liveChange(int numLiveNeighbours) {
        if (numLiveNeighbours < 2 || numLiveNeighbours > 3) {
        // перенаселение или наоборот
           isAliveNext = false;
        } else {
            isAliveNext = true;
        }
    }

    private void deadChange(int numLiveNeighbours) {
        if (numLiveNeighbours == 3) {
        //детишки
           isAliveNext = true;
        } else {
            isAliveNext = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        updateColor();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
