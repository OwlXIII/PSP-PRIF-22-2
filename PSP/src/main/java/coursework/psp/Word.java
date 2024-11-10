package coursework.psp;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Word {
    private String ID;
    private String Name;
    private String hiddenName;
    private String ItemPool;
    private String Type;
    private String DLC;
    private String Quote;
    private int Quality;
    private boolean isActive;
    private int row;
    private int col;
    private int spaceNeeded;
    private boolean isHorizontal;
    private int index;
    private List<Integer> extraRow = new ArrayList<>();
    private List<Integer> extraCol = new ArrayList<>();

    public Word(String name, String itemPool, String type, String DLC, String quote, int quality, boolean isActive) {
        this.Name = name;
        this.ItemPool = itemPool;
        this.Type = type;
        this.DLC = DLC;
        this.Quote = quote;
        this.Quality = quality;
        this.isActive = isActive;

        hideName();
    }

    public Word(String name, String itemPool, String type, String DLC, String quote, int quality, boolean isActive, int row, int col, int spaceNeeded, boolean isHorizontal, int index) {
        this.Name = name;
        this.ItemPool = itemPool;
        this.Type = type;
        this.DLC = DLC;
        this.Quote = quote;
        this.Quality = quality;
        this.isActive = isActive;
        this.row = row;
        this.col = col;
        this.spaceNeeded = spaceNeeded;
        this.isHorizontal = isHorizontal;
        this.index = index;

        hideName();
    }

    private void hideName() {
        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < this.Name.length(); i++) {
            hiddenWord.append('*');
        }
        this.hiddenName = hiddenWord.toString();
    }

    public void revealName() {
        this.hiddenName = this.Name;
    }
}
