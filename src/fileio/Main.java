package fileio;

import java.io.*;

public class Main {

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
            File f = new File( sourcePath );
            if ( ! f.exists( ) )
            {
                System.out.println("Source file does not exist." );
                System.exit( 1 );
            }
            try(BufferedReader input = new BufferedReader( new FileReader( f ) ) )
            {
                String record = null;
                String state = null;
                Float population = 0F;
                Float childPopulation = 0F;
                Float childPovertyPopulation = 0F;

                for(int i = 0; i < numberOfRecords; i++)
                {
                    record = input.readLine();
                    state = record.substring( 0,2 );
                    System.out.println(state );
                    population = Float.parseFloat(record.substring( 82, 90 ));
                    System.out.println(population );
                    childPopulation = Float.parseFloat( record.substring( 91, 99 ) );
                    System.out.println(childPopulation );
                    childPovertyPopulation = Float.parseFloat( record.substring( 100, 108 ) );
                    System.out.println(childPovertyPopulation );

                }
            }
        }
        catch ( Exception e )
        {
            System.out.println(e );
        }
    }
}
