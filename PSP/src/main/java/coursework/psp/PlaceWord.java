package coursework.psp;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter

public class PlaceWord {

    private Grid grid;
    private int size;
    private List<Word> words;

    public PlaceWord(Grid grid, int size, List<Word> words) {
        this.grid = grid;
        this.size = size;
        this.words = words;
    }

    public void Vertically(int row, int col, Word word, int spaceNeeded, boolean isHidden, int index) {
        word.setRow(row);
        word.setCol(col);
        word.setSpaceNeeded(spaceNeeded);
        word.setHorizontal(false);
        word.setIndex(index);

        if (isHidden) {
            for (int i = 0; i < word.getName().length(); i++) {
                char charIndex = (char)(word.getIndex()+1 + '0');
                grid.setValue(row+i,col,charIndex);
            }
            System.out.println("Word " + word.getName() + " successfully added to grid");
        }

        else {
            for (int i = 0; i < word.getName().length(); i++) {
                grid.setValue((row+i) ,col, word.getName().toUpperCase().charAt(i));
            }
        }

    }

    public void Horizontally(int row, int col, Word word, int spaceNeeded, boolean isHidden, int index) {
        word.setRow(row);
        word.setCol(col);
        word.setSpaceNeeded(spaceNeeded);
        word.setHorizontal(true);
        word.setIndex(index);

        if (isHidden) {
            for (int k = 0; k < word.getName().length(); k++) {
                char charIndex = (char)(word.getIndex()+1 + '0');
                grid.setValue(row,col+k,charIndex);
            }
            System.out.println("Word " + word.getName() + " successfully added to grid");
        }

        else {
            for (int k = 0; k < word.getName().length(); k++) {
                grid.setValue(row,col+k,word.getName().toUpperCase().charAt(k));
            }
        }

    }

    public void putExtraWord(Word extraWord, int Start) {
        for (int i = Start; i < extraWord.getName().length(); i++) {
            for (Word word : words) {
                for (int k = 0; k < word.getName().length(); k++) {
                    if (word.getName().toUpperCase().charAt(k) == extraWord.getName().toUpperCase().charAt(i) && !Character.isDigit(extraWord.getHiddenName().toUpperCase().charAt(i))) {
                        if (word.isHorizontal()) {
                            extraWord.getExtraRow().add(word.getRow());
                            extraWord.getExtraCol().add(word.getCol() + k);

                            StringBuilder hideChar = new StringBuilder(extraWord.getHiddenName());
                            hideChar.setCharAt(i, grid.getValue(word.getRow(),word.getCol()+k));
                            extraWord.setHiddenName(hideChar.toString());
                        }
                        else {
                            extraWord.getExtraRow().add(word.getRow() + k);
                            extraWord.getExtraCol().add(word.getCol());

                            StringBuilder hideChar = new StringBuilder(extraWord.getHiddenName());
                            hideChar.setCharAt(i, grid.getValue(word.getRow()+k,word.getCol()));
                            extraWord.setHiddenName(hideChar.toString());
                        }
                        putExtraWord(extraWord, i + 1);
                    }
                }
            }
        }
    }
}
