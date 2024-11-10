package coursework.psp;

import lombok.Getter;

import java.util.List;

@Getter

public class IsEnoughSpace {

    private Grid grid;
    private int size;
    private List<Word> words;
    private int gridRow;
    private int gridCol;
    private int spaceNeeded;

    public IsEnoughSpace(Grid grid, int size, List<Word> words) {
        this.grid = grid;
        this.size = size;
        this.words = words;
    }

    public boolean Vertically(int row, int col, Word word, int spaceNeeded) {

        if (spaceNeeded + size < size || spaceNeeded - size > 0) {
            return false;
        }

        row = row - spaceNeeded;
        for (int i = 0; i < spaceNeeded; i++) {
            if (grid.getValue(row + i,col) != ' ') {
                return false;
            }
        }

        row = row + spaceNeeded;
        spaceNeeded = word.getName().length() - spaceNeeded - 1;

        for (int i = 1; i <= spaceNeeded; i++) {
            if (grid.getValue(row + i,col) != ' ') {
                return false;
            }
        }
        gridRow = row;
        this.spaceNeeded = spaceNeeded;
        return true;
    }

    public boolean Horizontally(int row, int col, Word word, int spaceNeeded) {
        if (spaceNeeded == 0) {
            spaceNeeded++;
        }

        if (spaceNeeded + size < size || spaceNeeded - size > 0) {
            return false;
        }
        col = col + spaceNeeded;
        for (int i = 1; i <= spaceNeeded; i++) {
            if (grid.getValue(row,col+i) != ' ') {
                return false;
            }
        }

        col = col - spaceNeeded;
        spaceNeeded = word.getName().length() - spaceNeeded - 1;

        for (int i = 1; i <= spaceNeeded; i++) {
            if (grid.getValue(row,col-i) != ' ') {
                return false;
            }
        }

        gridCol = col;
        this.spaceNeeded = spaceNeeded;
        return true;
    }

}
