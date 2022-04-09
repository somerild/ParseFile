package fileio;

import java.io.*;

public class ParseFile {

    public static void main(String[] args)
    {
        String sourcePath = null; // data source file path
        String destinationPath = null; // destination file path
        int numberOfRecords = 0; //number of records in the data source

        File in = null; //source file
        File out = null; //destination file
        BufferedReader input; //FileReader to read source file
        PrintWriter output; //PrintWriter to write to destination file

        //verify the right number of arguments and set
        try
        {
            sourcePath = args[0];
            numberOfRecords = Integer.parseInt( args[1] );
            if(numberOfRecords == 0) throw new IllegalArgumentException( "Number of records is required." );
            if(numberOfRecords <= 0) throw new IllegalArgumentException( "Invalid number of records." );
            destinationPath = args[2];
        }
        catch (Exception e )
        {
            System.out.println("Missing or invalid parameters.");
            e.printStackTrace();
            System.exit( 1 );
        }

        //create file objects, verify
        try
        {
            in = new File( sourcePath );
            if(!in.exists()) throw new FileNotFoundException( "File does not exist." );
            if(!in.canRead()) throw new IOException( "Cannot read file." );
            out = new File( destinationPath );
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
            System.exit( 1 );
        }

        //read from source and write to destination line by line
        try
        {
            input = new BufferedReader(
                    new FileReader( in ) );
            output = new PrintWriter( new BufferedWriter( new FileWriter( out, true ) ));
            if ( !out.exists( ) ) throw new FileNotFoundException( "File not created." );
            if (!out.canWrite()) throw new IOException( "File is not writable." );

            //destination file header
            output.printf("%-5s\t%-10s\t%-16s\t%-24s%n", "State", "Population", "Child Population", "Child Poverty Population");

            for(int i = 0; i < numberOfRecords; i++)
            {
                String line = input.readLine( );
                String stateCode = line.substring( 0,3 ).trim();
                String population = line.substring( 79, 91).trim();
                String childPopulation = line.substring(91,100).trim();
                String childPovertyPopulation = line.substring( 100,109 ).trim();

                output.printf( "%-5s\t%-10s\t%-16s\t%-24s%n", stateCode, population, childPopulation, childPovertyPopulation);
            }
            input.close();
            output.close();
        }catch(Exception e2)
        {
            e2.printStackTrace();
        }
    }
}
