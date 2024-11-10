package coursework.psp;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter

public class Grid {
    private char[][] grid;
    private int size;

    public Grid(int size) {
        this.size = size;
        this.grid = new char[size][size];
        for (char[] row : grid) Arrays.fill(row, ' ');
    }

    public void printGrid() {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell == ' ' ? '.' : cell); // Print '.' for empty spaces
            }
            System.out.println();
        }
        System.out.println();
    }
    public void setValue(int row, int column, char cell) {
        grid[row][column] = cell;
    }
    public char getValue(int row, int column) {
        return grid[row][column];
    }
}
