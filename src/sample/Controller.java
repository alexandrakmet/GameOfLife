package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.objects.Cell;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private Stage mainStage;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private GridPane golPane;


    @FXML
    private Label generationLabel;

    @FXML
    private Label livecellsLabel;

    @FXML
    private Label selectedpatternLabel;


    private static final double CELL_SIZE = 10;
    private static final int ROW = 57;
    private static final int COL = 60;
    private static int generation = 0;
    private static int liveCells = 0;

    // 1 update per speed (in millisecond)
    private static final int SPEED = 100;

    private static Cell[][] cells;
    private static boolean playing = false;
    private static boolean isReset = false;
    private static boolean isPaused = true;
    private static boolean isRandom = false;



    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    @FXML
    private void initialize() {
        init();
        gameLoop();
    }


    public void init() {
        getCenterPane();
    }


    public void gameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (playing) {

                        if (liveCells > 0) {
                            prepare();
                            update();
                            updateGenerationLabel();
                            updateLiveCellsLabel();
                            //updateSelectedPatternLabel();
                        }
                    }

                    if (!playing && isPaused) {
                        updateGenerationLabel();
                        updateLiveCellsLabel();
                        //updateSelectedPatternLabel();
                    }

                });
            }
        }, 0, SPEED);
    }

    private static void prepare() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].updateNextState(getNumLiveNeighbors( i, j));
            }
        }
    }

    private static void update() {
        liveCells = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].updateCurrentState();
                if (cells[i][j].isAlive()) liveCells++;
            }
        }
        generation++;

    }

    public void updateGenerationLabel() {
        generationLabel.setText(Integer.toString(generation));
    }

    public void updateLiveCellsLabel() {
        livecellsLabel.setText(Integer.toString(liveCells));
    }

    private static int getNumLiveNeighbors(int i, int j) {
        int numLiveNeighbors = 0;
        int r;
        int c;

        // top
        r = (i - 1 < 0) ? ROW - 1 : i - 1;
        c = j;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // top-left
        r = (i - 1 < 0) ? ROW - 1 : i - 1;
        c = (j - 1 < 0) ? COL - 1 : j - 1;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // left
        r = i;
        c = (j - 1 < 0) ? COL - 1 : j - 1;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // bottom-left
        r = (i + 1 < ROW) ? i + 1 : 0;
        c = (j - 1 < 0) ? COL - 1 : j - 1;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // bottom
        r = (i + 1 < ROW) ? i + 1 : 0;
        c = j;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // bottom-right
        r = (i + 1 < ROW) ? i + 1 : 0;
        c = (j + 1 < COL) ? j + 1 : 0;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // right
        r = i;
        c = (j + 1 < COL) ? j + 1 : 0;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        // top-right
        r = (i - 1 < 0) ? ROW - 1 : i - 1;
        c = (j + 1 < COL) ? j + 1 : 0;
        if (cells[r][c].isAlive()) numLiveNeighbors++;

        return numLiveNeighbors;
    }

    private void getCenterPane() {
        Cell cell;

        cells = new Cell[ROW][COL];
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                cell = new Cell(false, false, CELL_SIZE, r, c);
                cells[r][c] = cell;
                golPane.add(cell, c, r);
                //setCellMouseClickAction(cell);
                //setCellMouseEnteredAction(cell);
                //setCellExitedAction(cell);


                //setCellDragDetectedAction(cell);
                //setCellDragOverAction(cell);
                //setCellDragEnteredAction(cell);
                //setCellDragDroppedAction(cell);
                //setCellDragDoneAction(cell);

            }
        }

    }


//    private static void setCellMouseClickAction(Cell cell) {
//        cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                int[][] data;
//                int r;
//                int c;
//                int centerR;
//                int centerC;
//
//                if (selectedPatternCell.getPattern() == null) {
//                    cell.setAlive(true);
//                    liveCells++;
//                } else {
//
//                    data = selectedPatternCell.getData();
//                    r = cell.getRow();
//                    c = cell.getCol();
//                    centerR = (int) data.length / 2;
//                    centerC = (int) data[0].length / 2;
//
//                    for (int i = 0; i < data.length; i++) {
//
//                        for (int j = 0; j < data[i].length; j++) {
//
//                            if (0 <= (r + i - centerR) &&
//                                    (r + i - centerR) < ROW &&
//                                    0 <= (c + j - centerC) &&
//                                    (c + j - centerC) < COL) {
//
//                                if (data[i][j] == 1) {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.ALIVE_COLOR);
//
//                                    if (!cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            isAlive()) {
//                                        liveCells++;
//                                    }
//
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setAlive(true);
//
//                                } else {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.DEAD_COLOR);
//
//                                    if (cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            isAlive()) {
//                                        liveCells--;
//                                    }
//
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setAlive(false);
//                                }
//                            }
//
//                        }
//                    }
//
//                    selectedPatternCell.setPattern(null);
//                }
//
//
//            }
//        });
//    }
//
//    private static void setCellMouseEnteredAction(Cell cell) {
//        cell.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                int[][] data;
//                int r;
//                int c;
//                int centerR;
//                int centerC;
//
//                if (selectedPatternCell.getPattern() == null) {
//                    cell.setFill(Cell.HOVER_COLOR);
//                } else {
//
//                    data = selectedPatternCell.getData();
//                    r = cell.getRow();
//                    c = cell.getCol();
//                    centerR = (int) data.length / 2;
//                    centerC = (int) data[0].length / 2;
//
//                    for (int i = 0; i < data.length; i++) {
//
//                        for (int j = 0; j < data[i].length; j++) {
//
//                            if (0 <= (r + i - centerR) &&
//                                    (r + i - centerR) < ROW &&
//                                    0 <= c + j - centerC &&
//                                    c + j - centerC < COL) {
//
//                                if (data[i][j] == 1) {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.HOVER_COLOR);
//                                } else {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.DEAD_COLOR);
//                                }
//                            }
//
//                        }
//                    }
//                }
//
//            }
//        });
//    }
//
//    private static void setCellExitedAction(Cell cell) {
//        cell.setOnMouseExited(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                int[][] data;
//                int r;
//                int c;
//                int centerR;
//                int centerC;
//
//                if (selectedPatternCell.getPattern() == null) {
//                    if (cell.isAlive()) {
//                        cell.setFill(Cell.ALIVE_COLOR);
//                    } else {
//                        cell.setFill(Cell.DEAD_COLOR);
//                    }
//                } else {
//                    data = selectedPatternCell.getData();
//                    r = cell.getRow();
//                    c = cell.getCol();
//                    centerR = (int) data.length / 2;
//                    centerC = (int) data[0].length / 2;
//
//                    for (int i = 0; i < data.length; i++) {
//
//                        for (int j = 0; j < data[i].length; j++) {
//
//                            if (0 <= (r + i - centerR) &&
//                                    (r + i - centerR) < ROW &&
//                                    0 <= (c + j - centerC) &&
//                                    (c + j - centerC) < COL) {
//
//                                if (cells[r + i - centerR]
//                                        [c + j - centerC].
//                                        isAlive()) {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.ALIVE_COLOR);
//                                } else {
//                                    cells[r + i - centerR]
//                                            [c + j - centerC].
//                                            setFill(Cell.DEAD_COLOR);
//                                }
//                            }
//
//                        }
//                    }
//                }
//            }
//        });
//    }

    private static void initRandom() {
        Random random = new Random();
        for (int i = 0; i < ROW * COL / 2; i++) {
            cells[random.nextInt(ROW)][random.nextInt(COL)].
                    setAlive(true);
            liveCells++;
        }
    }
}


