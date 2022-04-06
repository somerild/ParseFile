package fileio;

import java.io.*;

public class ParseFile {

    public static void main(String[] args)
    {
        String sourcePath; // data source file path
        String destinationPath; // destination file path
        int numberOfRecords; //number of records in the data source

        try
        {
            if (args.length < 3) throw new RuntimeException( "Three arguments are required. " +
                    "source file path, number of records, destination file path." );
            sourcePath = args[0];
            numberOfRecords = Integer.valueOf( args[1] );
            destinationPath = args[2];

            File fIn = new File( sourcePath );
            File fOut = new File(destinationPath);
            PrintWriter output = new PrintWriter( new FileWriter( fOut, true ) );

            if ( ! fIn.exists( ) )
            {
                System.out.println("Source file does not exist." );
                System.exit( 1 );
            }
            try(BufferedReader input = new BufferedReader( new FileReader( fIn ) ) )
            {
                String record = null;
                String state = null;
                String population = null;
                String childPopulation = null;
                String childPovertyPopulation = null;

                output.printf("%5s %10s %16s %24s%n", "State", "Population", "Child Population", "Child Poverty Population");

                for(int i = 0; i < numberOfRecords; i++)
                {
                    record = input.readLine();
                    state = record.substring(0,2);
                    population = record.substring( 82, 90 );
                    childPopulation = record.substring( 91, 99 );
                    childPovertyPopulation = record.substring( 100, 108 );
                    output.printf("%5s %10s %16s %24s%n", state, population, childPopulation, childPovertyPopulation);
                }
                output.close();
            }
        }
        catch ( Exception e )
        {
            System.out.println(e );
        }
    }
}
