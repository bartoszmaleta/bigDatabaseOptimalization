package com.company;

import com.company.connector.DatabaseCredentials;
import com.company.connector.JSONService;
import com.company.connector.PostgreSQLJDBC;
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
//        dbFiller.fill();

        JSONService jsonService = new JSONService();
        DatabaseCredentials databaseCredentials = jsonService.readEnvironment();
        PostgreSQLJDBC database = new PostgreSQLJDBC();
        database.connect(databaseCredentials);
    }
}
