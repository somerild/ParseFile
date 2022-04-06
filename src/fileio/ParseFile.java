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
            if ( args.length < 3 ) throw new IllegalArgumentException( "Three arguments are required. " +
                    "source file path, number of records, destination file path." );

            //set arg inputs
            sourcePath = args[ 0 ];
            numberOfRecords = Integer.parseInt( args[ 1 ] );
            destinationPath = args[ 2 ];

            //create file objects with source and destination inputs
            File fIn = new File( sourcePath );
            File fOut = new File( destinationPath );

            //source file check
            if ( ! fIn.exists( ) )
            {
                System.out.println("Source file not found." );
                System.exit( 1 );
            }
            if(!fIn.canRead())
            {
                System.out.println("Cannot read source file." );
                System.exit( 1 );
            }

            try(BufferedReader input = new BufferedReader( new FileReader( fIn ) ) )
            {
                String record;
                String state;
                String population;
                String childPopulation;
                String childPovertyPopulation;

                try(PrintWriter output = new PrintWriter( new FileWriter( fOut, true ) ))
                {
                    //destination file check
                    if(!fOut.canWrite())
                    {
                        System.out.println("Cannot write to destination file." );
                        System.exit( 1 );
                    }
                    //print file header
                    output.printf("%5s %10s %16s %24s%n", "State", "Population", "Child Population", "Child Poverty Population");

                    //loop through records
                    for(int i = 0; i < numberOfRecords; i++)
                    {
                        record = input.readLine();
                        state = record.substring(0,2).trim();
                        population = record.substring( 82, 90 ).trim();
                        childPopulation = record.substring( 91, 99 ).trim();
                        childPovertyPopulation = record.substring( 100, 108 ).trim();

                        //print record to file
                        output.printf("%5s %10s %16s %24s%n", state, population, childPopulation, childPovertyPopulation);
                    }
                }
            }
            catch(IOException e1)
            {
                e1.printStackTrace();
                System.exit( 1 );
            }
        }
        catch ( IllegalArgumentException e0 )
        {
            e0.printStackTrace();
            System.exit( 1 );
        }
    }
}
