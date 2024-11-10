package coursework.psp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static void readClientInput(String cmd, Scanner scanner, CrosswordGenerator crossword, List<Word> Words, Word extraWord) {
        System.out.print("Input your answer : ");
        String wordCmd = scanner.nextLine();
        if (crossword.checkIfWordIsCorrect(wordCmd, Integer.parseInt(cmd))){
            System.out.println("Correct!");
            int position = Integer.parseInt(cmd) - 1;
            Words.get(position).setHiddenName(wordCmd);

            if (Words.get(position).isHorizontal()) {
                crossword.placeWord.Horizontally(Words.get(position).getRow(),
                                                   Words.get(position).getCol(),
                                                   Words.get(position),
                                                   Words.get(position).getSpaceNeeded(),
                                                   false,
                                                   Words.get(position).getIndex());
                crossword.revealLetters(extraWord, Words);
            }
            else {
                crossword.placeWord.Vertically(Words.get(position).getRow(),
                                                 Words.get(position).getCol(),
                                                 Words.get(position),
                                                 Words.get(position).getSpaceNeeded(),
                                                 false,
                                                 Words.get(position).getIndex());
                crossword.revealLetters(extraWord, Words);
            }
            int check = Words.size();
            for (Word word : Words) {
                if (!word.getHiddenName().contains("*")) {
                    check--;
                }
            }
            if (check == 0 && !extraWord.getHiddenName().contains("*")) {
                System.out.println("Game Over!");
                System.exit(0);
            }

        }
        else {
            System.out.println("Incorrect!");
        }
    }

    public static void main(String[] args) {
        Word item1 = new Word("GODHEAD", "Angel room", "Tear modifier", "Binding of Isaac: Rebirth", "God tears", 4, false);
        Word item2 = new Word("CHAOS", "Shop, Secret Room", "Passive", "Binding of Isaac: Afterbirth", "!!!", 3, false);
        Word item3 = new Word("SERAPHIM", "Item Room, Angel room", "Familiar", "Binding of Isaac: Afterbirth", "Sworn friend", 3, false);
        Word item4 = new Word("SOL", "Planetarium", "Passive", "The Binding of Isaac: Repentance", "Radiant victory", 1, false);
        Word item5 = new Word("SOL_2", "Planetarium", "Passive", "The Binding of Isaac: Repentance", "Radiant victory", 1, true);

        Word extraWord = new Word("SHADE", "Devil room, Curse Room", "Familiar", "Binding of Isaac: Repentance", "It follows", 0, false);

        List<Word> wordsObject = new ArrayList<>(Arrays.asList(item1, item2, item3, item4, item5));

        Grid grid = new Grid (20);

        CrosswordGenerator crossword = new CrosswordGenerator(grid, grid.getSize(), wordsObject);
        crossword.generateCrossword();
        extraWord.revealName();
        crossword.placeWord.putExtraWord(extraWord, 0);

        Scanner scanner = new Scanner(System.in);
        String cmd = "";

        while (!cmd.equals("q")) {
            grid.printGrid();
            System.out.println("\n");
            System.out.println("""
                    Choose a word (1 - 5)
                    c - show correct answers
                    q - quit
                    """);

            cmd = scanner.nextLine();

            switch (cmd) {
                case "1", "2", "3", "4", "5":
                    readClientInput(cmd, scanner, crossword, wordsObject, extraWord);
                break;
                case "c":
                    for(int i = 0; i < wordsObject.size(); i++){
                        System.out.println((i+1) + ". " + wordsObject.get(i).getHiddenName().toUpperCase() +
                                           "\n Item Pool - " + wordsObject.get(i).getItemPool() +
                                           "\n Type - " + wordsObject.get(i).getType() +
                                           "\n DLC - " + wordsObject.get(i).getDLC() +
                                           "\n Quote - " + wordsObject.get(i).getQuote() +
                                           "\n Quality - " + wordsObject.get(i).getQuality() +
                                           "\n Is Active - " + wordsObject.get(i).isActive() +
                                           "\n Character Count - " + wordsObject.get(i).getName().length() +
                                           "\n is Horizontal - " + wordsObject.get(i).isHorizontal()
                        );
                    }
                    System.out.println("Extra Word - " + extraWord.getHiddenName());
                    System.out.println("\n");
                break;
                default:
                break;
            }
        }

    }

}
