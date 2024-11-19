import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    // Method to read the grid from a file and return it as a 2D array
    public static String[][] readGridFromFile(String filename) throws IOException {
        List<String[]> gridList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                gridList.add(line.trim().split("\\s+"));
            }
        }
        return gridList.toArray(new String[0][0]);
    }

    // Method to search for words in the grid
    public static List<FoundWord> findWordsInGrid(String[][] grid, String[] wordsToFind) {
        List<FoundWord> foundWords = new ArrayList<>();
        int numRows = grid.length;
        int numCols = grid[0].length;

        // Search horizontally and horizontally reversed
        for (int row = 0; row < numRows; row++) {
            StringBuilder rowString = new StringBuilder();
            for (String cell : grid[row]) rowString.append(cell);

            String reversedRow = rowString.reverse().toString();

            for (String word : wordsToFind) {
                // Left to right
                int index = rowString.indexOf(word);
                if (index != -1) {
                    foundWords.add(new FoundWord(word, row, index, row, index + word.length() - 1));
                }
                // Right to left
                int reverseIndex = reversedRow.indexOf(word);
                if (reverseIndex != -1) {
                    foundWords.add(new FoundWord(word, row, numCols - 1 - reverseIndex, row, numCols - 1 - (reverseIndex + word.length() - 1)));
                }
            }
        }

        // Search vertically
        for (int col = 0; col < numCols; col++) {
            StringBuilder colString = new StringBuilder();
            for (int row = 0; row < numRows; row++) colString.append(grid[row][col]);

            for (String word : wordsToFind) {
                int index = colString.indexOf(word);
                if (index != -1) {
                    foundWords.add(new FoundWord(word, index, col, index + word.length() - 1, col));
                }
            }
        }

        // Search diagonally (left-to-right and right-to-left)
        for (String word : wordsToFind) {
            int wordLen = word.length();
            // Left-to-right diagonals
            for (int row = 0; row <= numRows - wordLen; row++) {
                for (int col = 0; col <= numCols - wordLen; col++) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 0; i < wordLen; i++) diagonal.append(grid[row + i][col + i]);
                    if (diagonal.toString().equals(word)) {
                        foundWords.add(new FoundWord(word, row, col, row + wordLen - 1, col + wordLen - 1));
                    }
                }
            }
            // Right-to-left diagonals
            for (int row = 0; row <= numRows - wordLen; row++) {
                for (int col = wordLen - 1; col < numCols; col++) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 0; i < wordLen; i++) diagonal.append(grid[row + i][col - i]);
                    if (diagonal.toString().equals(word)) {
                        foundWords.add(new FoundWord(word, row, col, row + wordLen - 1, col - wordLen + 1));
                    }
                }
            }
        }

        return foundWords;
    }

    // Main method
    public static void main(String[] args) {
        String filename = "TRIAL1.txt"; // Your file name
        String[] wordsToFind = {"HELLO", "GOOD", "BYE"};

        try {
            // Read the grid
            String[][] grid = readGridFromFile(filename);

            // Print the grid (optional)
            System.out.println("5x5 Grid:");
            for (String[] row : grid) {
                System.out.println(String.join(" ", row));
            }

            // Find and print words in the grid
            List<FoundWord> foundWords = findWordsInGrid(grid, wordsToFind);

            if (!foundWords.isEmpty()) {
                System.out.println("\nWords found in the grid:");
                for (FoundWord entry : foundWords) {
                    System.out.printf("%s  %d:%d  %d:%d%n", entry.word, entry.startRow, entry.startCol, entry.endRow, entry.endCol);
                }
            } else {
                System.out.println("\nNo words found.");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Helper class to store found words and their positions
    static class FoundWord {
        String word;
        int startRow, startCol, endRow, endCol;

        FoundWord(String word, int startRow, int startCol, int endRow, int endCol) {
            this.word = word;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
        }
    }
}
