import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Reads a .dat file and reverses it.
 * @version CSE 332, 15wi
 * Includes extra comments written for learning purposes.
 */
public class Reverse {
    public static void main(String[]args) {
    	
    	// 1. Sanity check on command-line arguments
        if (args.length != 4) {
            System.err.println("Incorrect number of arguments");
            System.err.println("Usage: ");
            System.err.println("java Reverse <list or array> <double or generic> <input file> <output file>");
            System.exit(1);
        }

        
        // 2. Processes command-line arguments
        boolean useList = false; // properly assigned below
        boolean generic = false; // properly assigned below
        String stackType = args[0];
        String contentType = args[1];
        
        if (stackType.equals("list")) {
            useList = true;
        } else if (stackType.equals("array")) {
            useList = false;
        } else {
            System.err.println("Saw " + stackType + " instead of list or array as first argument!");
            System.exit(1);
        }
        if (contentType.equals("double")) {
            generic = false;
        } else if (contentType.equals("generic")) {
            generic = true;
        } else {
            System.err.println("Saw " + contentType + " instead of double or generic as second argument!");
            System.exit(1);
        }
        
        
        // 3. Reads input file and writes its reverses into output file
        try {
        	
            // Set up the input file to be read, and the output file to be written
            BufferedReader fileIn  = new BufferedReader(new FileReader(args[2]));
            PrintWriter    fileOut = new PrintWriter(new BufferedWriter(new FileWriter(args[3])));

            // Read the first line of the .dat file. We want to store the
            // "sample rate" in a variable, but we can ignore the rest 
            // of the line. We step through the first line one token (word)
            // at a time using the StringTokenizer.  The fourth token
            // is the one we want (the sample rate).
            String oneLine = fileIn.readLine();
            StringTokenizer str = new StringTokenizer(oneLine);
            str.nextToken();  // Read in semicolon
            str.nextToken();  // Read in "Sample"
            str.nextToken();  // Read in "Rate"
            int sampleRate = Integer.parseInt(str.nextToken()); // Read in sample rate

            // Read in the file and place values from the second column 
            // in the stack. The first column values are thrown away. 
            // We stop reading if we reach the end of the file.
            DStack         s  = null; // changed when not running generic code
            GStack<Double> gs = null; // changed when running generic code
            if (generic) {
            	if (useList) {
            		gs = new GListStack<Double>();
            	} else {
            		gs = new GArrayStack<Double>();
            	}
            } else {
            	if (useList) {
            		s = new ListStack();
            	} else {
            		s = new ArrayStack();
            	}
            }
            double data;

            int count = 0;
            while ((oneLine = fileIn.readLine()) != null) {
                if (oneLine.charAt(0) == ';') { continue; }
                str = new StringTokenizer(oneLine);
                str.nextToken();                              // Read in time step value from first column
                data = Double.parseDouble(str.nextToken());   // Read in data value from second column
                if (generic) {
                	gs.push(data);
                } else {
                	s.push(data);
                }
                count++;
            }
            System.out.println(count+" samples in file");


            // Now we are ready to output the data values to output file.
            // But first, we need to output the header line
            // "; Sample Rate <sample rate>"
            fileOut.println("; Sample Rate " + sampleRate);

            
            // Since the first column consists of numbers which start
            // at 0 and increase by 1/sampleRate every time slice, we'll
            // just use numSteps to recalculate these numbers.
            int numSteps = 0;

            
            // Finally, we print the values in reverse order (by popping
            // them off the stack). The first column consists of numbers
            // which start at 0 and increase by 1/sampleRate per row, so
            // we'll use numSteps/sampleRate to recalculate the appropriate
            // values. Uniform spacing will be accomplished by printing a tab.
            if (generic) {
            	while (!gs.isEmpty()) {
            		fileOut.println((double) numSteps / sampleRate + "\t" + gs.peek());
            		gs.pop();
            		numSteps++;
            	}
            } else {
            	while (!s.isEmpty()) {
            		fileOut.println((double) numSteps / sampleRate + "\t" + s.peek());
            		s.pop();
            		numSteps++;
            	}
            }

            // Close the files
            fileIn.close();
            fileOut.close();

        } catch(IOException ioe) {
            System.err.println("Error opening/reading/writing input or output file.");
            System.exit(1);
        } catch(NumberFormatException nfe) {
            System.err.println(nfe.toString());
            System.err.println("Error in file format");
            System.exit(1);
        }
    }
}
