package coursework.psp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CrosswordGenerator {

    private Grid grid;
    private int size;
    private List<Word> words;
    private int gridRow;
    private int gridCol;

    IsEnoughSpace isEnoughSpace;
    PlaceWord placeWord;


    public CrosswordGenerator(Grid grid, int size, List<Word> words) {
        this.size = size;
        this.grid = grid;
        this.words = words;

        this.isEnoughSpace = new IsEnoughSpace(grid, grid.getSize(), words);
        this.placeWord = new PlaceWord(grid, grid.getSize(), words);
    }

    public void revealLetters(Word word, List<Word> words) {

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).isHorizontal()) {
                for (int j = 0; j < words.get(i).getName().length(); j++) {
                    if (!Character.isDigit(grid.getValue(words.get(i).getRow(),words.get(i).getCol() + j))) {
                        StringBuilder revealChar = new StringBuilder(words.get(i).getHiddenName());
                        revealChar.setCharAt(j, grid.getValue(words.get(i).getRow(), words.get(i).getCol() + j));
                        words.get(i).setHiddenName(revealChar.toString());
                    }
                }
            }
            else {
                for (int j = 0; j < words.get(i).getName().length(); j++) {
                    if(!Character.isDigit(grid.getValue(words.get(i).getRow() + j,words.get(i).getCol()))) {
                        StringBuilder revealChar = new StringBuilder(words.get(i).getHiddenName());
                        revealChar.setCharAt(j, grid.getValue(words.get(i).getRow() + j,words.get(i).getCol()));
                        words.get(i).setHiddenName(revealChar.toString());
                    }
                }
            }
        }

        for (int i = 0; i < word.getName().length(); i++) {
            StringBuilder revealChar = new StringBuilder(word.getHiddenName());
            revealChar.setCharAt(i, grid.getValue(word.getExtraRow().get(i),word.getExtraCol().get(i)));
            word.setHiddenName(revealChar.toString());
        }
    }


    private boolean canPlaceVertically(int row, int col, Word word, Word previousWord, int index) {

        for (int i = 0; i < word.getName().length(); i++) {
            for (int j = 0; j < previousWord.getName().length(); j++) {
                int spaceNeeded = j;
                if (word.getName().charAt(i) == previousWord.getName().charAt(j) && isEnoughSpace.Vertically(row, col, word, spaceNeeded)) {
                    row = isEnoughSpace.getGridRow() - spaceNeeded;
                    placeWord.Vertically(row, col, word, spaceNeeded, true, index);

                    gridRow = row;
                    gridCol = col;
                    return true;
                }
            }
            col++;
        }
        System.out.println("Cannot place word " + word.getName());
        return false;
    }



    private boolean canPlaceHorizontally(int row, int col, Word word, Word previousWord, int index) {

        int temprow = row;

        for (int i = 0; i < word.getName().length(); i++) {
            row = temprow;
            for (int j = 0; j < previousWord.getName().length(); j++) {
                int spaceNeeded = word.getName().length()-i-1;
                if (word.getName().charAt(i) == previousWord.getName().charAt(j) && isEnoughSpace.Horizontally(row, col, word, spaceNeeded)) {
                    col = isEnoughSpace.getGridCol() - (word.getName().length() - spaceNeeded - 1);

                    placeWord.Horizontally(row, col, word, spaceNeeded, true, index);

                    gridRow = row;
                    gridCol = col;
                    return true;
                }
                row++;
            }
        }
        System.out.println("Cannot place word " + word.getName());
        return false;
    }

    public boolean checkIfWordIsCorrect(String word, int Number) {
        return Objects.equals(word.toUpperCase(), words.get(Number-1).getName());
    }

    public void generateCrossword() {

        PlaceWord placeWord = new PlaceWord(grid, size, words);

        gridRow = size/2;
        gridCol = (size/2) - words.getFirst().getName().length() + 1;

        placeWord.Horizontally(gridRow, gridCol, words.getFirst(), words.getFirst().getName().length() / 2, true, 0);

        words.getFirst().setRow(gridRow);
        words.getFirst().setCol(gridCol);

        for (int i = 1; i < words.size(); i++) {
            if (i % 2 != 0) {
                for (int j = 0; j < words.get(i-1).getName().length(); j++) {
                    if (canPlaceVertically(gridRow, gridCol, words.get(i), words.get(i-1), i)) {
                        break;
                    }
                }
            }
            else {
                for (int j = 0; j < words.get(i-1).getName().length(); j++) {
                    if (canPlaceHorizontally(gridRow, gridCol, words.get(i), words.get(i-1), i)) {
                        break;
                    }
                }

            }
        }

    }
}
