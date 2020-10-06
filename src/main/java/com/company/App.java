package com.company;

import com.company.fillers.DatabaseFiller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        DatabaseFiller dbFiller = new DatabaseFiller();
        dbFiller.fill();
    }
}
