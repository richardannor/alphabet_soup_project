
import java.io.*;
import java.util.*;

public class Main{

    // Directions for 8 possible moves (row_increment, col_increment)
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Vertical and horizontal
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  
    };

    public static void main(String[] args) {
        String filename = "input.text";// file which content input

        try {
            WordSearch puzzle = loadWordSearch(filename);
            Map<String, String> results = findWords(puzzle);
            results.forEach((word, position) ->
                    System.out.println(word + " found at: " + position)
            );
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Load the word search from the file
    private static WordSearch loadWordSearch(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // First line: Grid dimensions
        String[] dimensions = reader.readLine().trim().split("x");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        // Load the grid
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = reader.readLine().trim().split(" ");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line[j].charAt(0);
            }
        }

        // Load the words to find
        List<String> words = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                words.add(line.trim().replace(" ", ""));
            }
        }

        reader.close();
        return new WordSearch(grid, words);
    }

    // Find all words in the grid
    private static Map<String, String> findWords(WordSearch puzzle) {
        Map<String, String> results = new HashMap<>();
        char[][] grid = puzzle.grid;

        for (String word : puzzle.words) {
            boolean found = false;
            for (int row = 0; row < grid.length && !found; row++) {
                for (int col = 0; col < grid[0].length && !found; col++) {
                    if (grid[row][col] == word.charAt(0)) {
                        String position = searchWord(grid, word, row, col);
                        if (position != null) {
                            results.put(word, position);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                results.put(word, "not found");
            }
        }
        return results;
    }

    // Search for a word starting from a specific position in all directions
    private static String searchWord(char[][] grid, String word, int startRow, int startCol) {
        for (int[] dir : DIRECTIONS) {
            int row = startRow, col = startCol;
            boolean found = true;

            for (char c : word.toCharArray()) {
                if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != c) {
                    found = false;
                    break;
                }
                row += dir[0];
                col += dir[1];
            }

            if (found) {
                int endRow = row - dir[0];
                int endCol = col - dir[1];
                return "(" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ")";
            }
        }
        return null;
    }
}

// Class to hold the word search puzzle data
class WordSearch {
    char[][] grid;
    List<String> words;

    public WordSearch(char[][] grid, List<String> words) {
        this.grid = grid;
        this.words = words;
    }
}



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;

public class Main{

    // Directions for 8 possible moves (row_increment, col_increment)
    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},   // Vertical and horizontal
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}  
    };

    public static void main(String[] args) {
        String filename = "input.text";// file which content input

        try {
            WordSearch puzzle = loadWordSearch(filename);
            Map<String, String> results = findWords(puzzle);
            results.forEach((word, position) ->
                    System.out.println(word + " found at: " + position)
            );
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Load the word search from the file
    private static WordSearch loadWordSearch(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // First line: Grid dimensions
        String[] dimensions = reader.readLine().trim().split("x");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        // Load the grid
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = reader.readLine().trim().split(" ");
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line[j].charAt(0);
            }
        }

        // Load the words to find
        List<String> words = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                words.add(line.trim().replace(" ", ""));
            }
        }

        reader.close();
        return new WordSearch(grid, words);
    }

    // Find all words in the grid
    private static Map<String, String> findWords(WordSearch puzzle) {
        Map<String, String> results = new HashMap<>();
        char[][] grid = puzzle.grid;

        for (String word : puzzle.words) {
            boolean found = false;
            for (int row = 0; row < grid.length && !found; row++) {
                for (int col = 0; col < grid[0].length && !found; col++) {
                    if (grid[row][col] == word.charAt(0)) {
                        String position = searchWord(grid, word, row, col);
                        if (position != null) {
                            results.put(word, position);
                            found = true;
                        }
                    }
                }
            }
            if (!found) {
                results.put(word, "not found");
            }
        }
        return results;
    }

    // Search for a word starting from a specific position in all directions
    private static String searchWord(char[][] grid, String word, int startRow, int startCol) {
        for (int[] dir : DIRECTIONS) {
            int row = startRow, col = startCol;
            boolean found = true;

            for (char c : word.toCharArray()) {
                if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != c) {
                    found = false;
                    break;
                }
                row += dir[0];
                col += dir[1];
            }

            if (found) {
                int endRow = row - dir[0];
                int endCol = col - dir[1];
                return "(" + startRow + ", " + startCol + ") to (" + endRow + ", " + endCol + ")";
            }
        }
        return null;
    }
}

// Class to hold the word search puzzle data
class WordSearch {
    char[][] grid;
    List<String> words;

    public WordSearch(char[][] grid, List<String> words) {
        this.grid = grid;
        this.words = words;
    }
}




