package zergtel.core.io;

import java.io.*;

public class FileOut
{
    /**
    Despite the changes I have made in the effort of cleaner code, there still remains the problem that this FileOut class does not
     have the capability to write arbitrary binary data without mangling it - as such, this will be deprecated as there is no way to
     offload binary writing to a class that I know of (at least a way to do so, that still has a buffer).
     See my http class for an example.
     --Simon
     */




//    /**
//     * The static methods in this class allow text to be written to Or read from a file
//     * @author Shyam Patel
//     * @since January 2016
//     *
//     */
//	/*
//	 * VARIABLE AND METHODS FOR WRITING TO A FILE:
//	 */
//
//    private PrintWriter fileOut;
//    private static int threads = 0;
//
//    /**
//     * Creates a new file in the current folder and places a reference to it in fileOut
//     * @param file represents name of the file
//     */
//
//    public FileOut(String file)
//    {
//        this(file, false);
//    }
//
//    /**
//     * creates a new file in the current folder and places a reference to it in fileOut
//     * @param file represents name of the file
//     * @param append true if you want to add to the existing file
//     */
//
//    public FileOut (String file, boolean append)
//    {
//
//        try
//        {
//            fileOut = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
//            threads++;
//        }
//
//        catch(IOException e)
//        {
//            System.out.println("Cannot create file:  " + file);
//        }
//
//    }
//
//    /**
//     * Text to be added to current file
//     * @param text the characters to be added
//     */
//
//    public void print(String text)
//    {
//        fileOut.print(text);
//    }
//
//    /**
//     * Text to be added to current file
//     * @param text the characters to be added
//     */
//
//    public void println(String text)
//    {
//        fileOut.println(text);
//    }
//
//    /**
//     * closes the file that is being written to
//     * Note: this method must b called when you are finished
//     * in order to save changes
//     */
//
//    public void closeOutputFile()
//    {
//        fileOut.close();
//        threads--;
//    }
}