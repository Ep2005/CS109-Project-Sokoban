package controller;

import model.Direction;
import model.MapMatrix;
import view.game.Box;
import view.game.GamePanel;
import view.game.GridComponent;
import view.game.Hero;

/**
 * It is a bridge to combine GamePanel(view) and MapMatrix(model) in one game.
 * You can design several methods about the game logic in this class.
 */
public class GameController {
    private final GamePanel view;
    private final MapMatrix model;

    public GameController(GamePanel view, MapMatrix model) {
        this.view = view;
        this.model = model;
        view.setController(this);
    }


    //Implemented restart
    public void restartGame() {
        System.out.println("Do restart game here");
        this.model.resetMapMatrix();
        this.view.restartGame();
    }

    public boolean doMove(int row, int col, Direction direction) {
        GridComponent currentGrid = view.getGridComponent(row, col);
        //target row can column.
        int tRow = row + direction.getRow();
        int tCol = col + direction.getCol();
        int uRow = tRow + direction.getRow();
        int uCol = tCol + direction.getCol();
        GridComponent targetGrid = view.getGridComponent(tRow, tCol);
        int[][] map = model.getMatrix();
        if (map[tRow][tCol] == 0 || map[tRow][tCol] == 2) {

            //update hero in MapMatrix
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;
            //Update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //Update the row and column attribute in hero
            h.setRow(tRow);
            h.setCol(tCol);
            if (finish()) {
                System.out.println("you win, next level");
            }
            else if (gameover()) {
                System.out.println("you lose");
            }

            return true;
        } else if (map[tRow][tCol] == 10 && (map[uRow][uCol] == 2 || map[uRow][uCol] == 0)) {
            map[tRow][tCol] -= 10;
            map[uRow][uCol] += 10;
            Box x = view.getGridComponent(tRow, tCol).removeBoxFromGrid();
            view.getGridComponent(uRow, uCol).setBoxInGrid(x);
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;
            //Update hero in GamePanel
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            //Update the row and column attribute in hero
            h.setRow(tRow);
            h.setCol(tCol);
            if (finish()) {
                System.out.println("you win, next level");
            }
            else if (gameover()) {
                System.out.println("you lose");
            }
            return true;
        } else if (map[tRow][tCol] == 12 && (map[uRow][uCol] == 2 || map[uRow][uCol] == 0)) {
            map[tRow][tCol] -= 10;
            map[uRow][uCol] += 10;
            Box x = view.getGridComponent(tRow, tCol).removeBoxFromGrid();
            view.getGridComponent(uRow, uCol).setBoxInGrid(x);
            model.getMatrix()[row][col] -= 20;
            model.getMatrix()[tRow][tCol] += 20;
            Hero h = currentGrid.removeHeroFromGrid();
            targetGrid.setHeroInGrid(h);
            h.setRow(tRow);
            h.setCol(tCol);
            if (finish()) {
                System.out.println("you win, next level");
            }
            else if (gameover()) {
                System.out.println("you lose");
            }
            return true;
        }
        return false;

    }

    public boolean finish() {
        int[][] matrix = model.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 10) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean gameover() {
        int[][] matrix = model.getMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 10 && (matrix[i-1][j]==1 && matrix[i][j-1]==1 ||matrix[i-1][j]==1 && matrix[i][j+1]==1||matrix[i+1][j]==1 && matrix[i][j-1]==1 ||matrix[i+1][j]==1 && matrix[i][j+1]==1)){
                    return true;
                }
            }
        }
        return false;
    }
}



    //todo: add other methods such as loadGame, saveGame...

