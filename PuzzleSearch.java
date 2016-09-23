// Programmed by Hugo Felkel
// CSCI 310 Assignment 3
// Dr. McCauley, College of Charleston

// Change to args[0] and test


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.lang.System.exit;

public class PuzzleSearch
{
    // Variable declaration
    private int rows;
    private int columns;
    private int baseLength = 0;
    private char [ ][ ] puzzleBoard;
    private BufferedReader fileIn = null;
    private BufferedReader puzzleReader;
    private List words;
    private List puzzle;
    private List foundList = new ArrayList();

    // Constructor
    public PuzzleSearch(String fileName ) throws IOException
    {
        puzzleReader = open( fileName );
        readWordsFromFile( );
        createPuzzle( );
    }
    // Open the file provided in the terminal args
    private BufferedReader open(String fileName )
    {

        do
        {
            try
            {
                if( fileName == null )
                    exit( 0 );
                // Store text file within the same directory as the java file
                fileIn  = new BufferedReader( new FileReader("./" + fileName));
            }
            catch( IOException e )
            { System.err.println( "Cannot open " + fileName );
                exit( 0 );
            }
        } while( fileIn == null );

        System.out.println( "Opened " + fileName );
        return fileIn;
    }

    // Read the word from the textFile provided one by one
    private void readWordsFromFile( ) throws IOException {
    String line;
    List puzzleLines = new ArrayList();
    words = new ArrayList();
    puzzle = new ArrayList();

    while ((line = puzzleReader.readLine()) != null) {
        puzzleLines.add(line);
    }

    for (int i = 0; i < puzzleLines.size(); i++) {
        if (puzzleLines.get(i).toString().contains(" ") || puzzleLines.get(i).toString().matches("\\d") ) {
            puzzle.add(puzzleLines.get(i).toString());
        } else {
            words.add(puzzleLines.get(i).toString());
        }
    }

}

    // Create the puzzle board based on the input received from the text file
    private void createPuzzle( ) throws IOException
    {
        String word;
        ArrayList newPuzzle = new ArrayList();
        columns = Integer.parseInt(puzzle.get(0).toString());
        rows = Integer.parseInt(puzzle.get(0).toString());
        puzzle.remove(0);

        if(puzzle.get(0).toString().matches("\\d"))
            throw new RuntimeException("File is not in the correct form. Must be a square matrix; stated through only a single number");
        // Remove whitespace from puzzle arrayList
        for(int i = 0; i < puzzle.size(); i++){
            word = puzzle.get(i).toString();
            // Remove whitespace
            word = word.replaceAll("\\s+","");
            newPuzzle.add(word);
        }

        puzzleBoard = new char[ rows ][ columns ];
        Iterator itr = newPuzzle.iterator( );
        for( int r = 0; r < rows; r++ )
        {
            String theLine = (String) itr.next( );
            if(theLine.length() != rows){
                throw new RuntimeException("A cell is empty within your puzzle");
            }
            puzzleBoard[ r ] = theLine.toCharArray( );
        }

    }

    // Flagging the row boundaries
    private int changeRow(int row, int rowDelta)
    {
        row = row + rowDelta;
        if(row < 0)
            row = rows - 1;
        if(row >= rows)
            row = 0;
        return row;
    }

    // Flagging the column boundaries
    private int changeCol(int col, int colDelta)
    {
        col = col + colDelta;
        if(col < 0) {
            col = columns - 1;
        }
        if(col >= columns) {
            col = 0;
        }
        return col;
    }

    // Finding the path of the selected word, if it can be found
    private boolean findPath(int row, int col, int rowDelta, int colDelta, String word, int foundLength) {

        String result;
        int newRow = changeRow(row, rowDelta);
        int newCol = changeCol(col, colDelta);

        // Provides the range of the word found instead of actually coordinates.
        if (foundLength == word.length()) {
            result = word + " found at " +
                    row + " " + col + " to " +
                    newRow + " " + newCol;
            foundList.add(result);
            return true;
        }
        // Incrementing found word length to compare against actual word length
        if (puzzleBoard[row][col] == word.charAt(foundLength)) {
            findPath(newRow, newCol, rowDelta, colDelta, word, foundLength + 1);
            return true;
        }
        return false;
    }

    // Solve the puzzle by looking for the provided words
    private void solve() {
        for (int loc = 0; loc < words.size(); loc++) {
            boolean foundWord = false;
            String word = words.get(loc).toString();

            for (int row = 0; row < rows; row++) {
                if (foundWord == true)
                    break;
                for (int col = 0; col < columns; col++) {
                    if (puzzleBoard[row][col] == word.charAt(0)) {
                        for (int rowAdjacent = -1; rowAdjacent < rows; rowAdjacent++) {
                            for (int colAdjacent = -1; colAdjacent < columns; colAdjacent++) {
                                if (findPath(row, col,rowAdjacent, colAdjacent, word, baseLength) == true)
                                    foundWord = true;
                            }
                        }
                    }
                }
            }
            if (foundWord == false) {
                System.out.println(word + " was not found");
            }
        }
    }

    // Print the founds word during the solve method on the assembled board
    private void printFoundList( ){
        String word1, word2, wordChange1, wordChange2;
        // remove duplications
        for(int i = 0; i < foundList.size()-1; i++){
            word1 = foundList.get(i).toString();
            word2 = foundList.get(i+1).toString();
            wordChange1 = word1.substring(0,rows);
            wordChange2 = word2.substring(0,rows);

            if(wordChange1.equals(wordChange2))
                foundList.remove(i);
            if(i >= foundList.size())
                break;
        }
        // print items to screen
        for(int i = 0; i < foundList.size(); i++){
            System.out.println(foundList.get(i).toString());
        }
    }
    public static void main( String [ ] args )
    {
        String filename = args[0];
        //String filename = "input3.txt";
        PuzzleSearch puzzleSearch;
        try
        {
            puzzleSearch = new PuzzleSearch(filename );
        }
        catch( IOException e )
        {
            System.out.println( "IO Error: " );
            e.printStackTrace( );
            return;
        }
        puzzleSearch.solve();
        puzzleSearch.printFoundList();
    }
}