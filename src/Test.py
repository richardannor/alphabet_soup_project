# Function to read the grid from a file and return it as a 2D list (list of lists)
def read_grid_from_file(filename):
    grid = []
    with open(filename, 'r') as file:
        for line in file:
            grid.append(line.strip().split())
    return grid

# Function to search for words in horizontal, vertical, and diagonal directions
def find_words_in_grid(grid, words_to_find):
    found_words = []

    # Search horizontally (from left to right)
    for row_idx, row in enumerate(grid):
        for word in words_to_find:
            word_len = len(word)
            for start_col in range(5 - word_len + 1):
                # Slice the row to check for the word
                if ''.join(row[start_col:start_col+word_len]) == word:
                    found_words.append({
                        'word': word,
                        'start': (row_idx, start_col),
                        'end': (row_idx, start_col + word_len - 1)
                    })
    
     # Search horizontally (from right to left)
            reversed_row = row[::-1]  # Reverse the row
            for start_col in range(4 - word_len + 1):
                # Slice the reversed row to check for the word (right to left)
                if ''.join(reversed_row[start_col:start_col + word_len]) == word:
                    found_words.append({
                        'word': word,
                        'start': (row_idx, 3 - start_col),
                        'end': (row_idx, 3 - (start_col + word_len - 1))
                    })
    
    
    
    
    # Search vertically (down columns)
    for col_idx in range(5):
        for word in words_to_find:
            word_len = len(word)
            for start_row in range(5 - word_len + 1):
                # Check for the word vertically by joining the column's characters
                if ''.join(grid[start_row + i][col_idx] for i in range(word_len)) == word:
                    found_words.append({
                        'word': word,
                        'start': (start_row, col_idx),
                        'end': (start_row + word_len - 1, col_idx)
                    })
    
    
    # Search diagonally (left to right)
    for start_row in range(5):
        for start_col in range(5):
            for word in words_to_find:
                word_len = len(word)
                # Check diagonal left-to-right direction
                if start_row + word_len <= 5 and start_col + word_len <= 5:
                    if ''.join(grid[start_row + i][start_col + i] for i in range(word_len)) == word:
                        found_words.append({
                            'word': word,
                            'start': (start_row, start_col),
                            'end': (start_row + word_len - 1, start_col + word_len - 1)
                        })

    # Search diagonally (right to left)
    for start_row in range(5):
        for start_col in range(4, -1, -1):
            for word in words_to_find:
                word_len = len(word)
                # Check diagonal right-to-left direction
                if start_row + word_len <= 5 and start_col - word_len >= -1:
                    if ''.join(grid[start_row + i][start_col - i] for i in range(word_len)) == word:
                        found_words.append({
                            'word': word,
                            'start': (start_row, start_col),
                            'end': (start_row + word_len - 1, start_col - word_len + 1)
                        })

    return found_words

# Main code
if __name__ == "__main__":
    filename = "TRIAL1.txt"  # Name of your file containing the grid
    words_to_find = ["HELLO", "GOOD", "BYE"]

    # Read the grid from the file
    grid = read_grid_from_file(filename)

    # Print the grid (optional)
    print("5X5")
    for row in grid:
        print(' '.join(row))
    
    items = ['BYE', 'GOOD', 'HELLO']

    # Print all items, each on a new line
    print(*items, sep='\n')
    
    # Find and print the words found in the grid along with their indices
    found_words = find_words_in_grid(grid, words_to_find)

    if found_words:
        print("\nWords found in the grid:")
        for entry in found_words:
            word = entry['word']
            start_row, start_col = entry['start']
            end_row, end_col = entry['end']
            print(f"{word}  {start_row}:{start_col}  {end_row}:{end_col}")
    else:
        print("\nNo words found.")

