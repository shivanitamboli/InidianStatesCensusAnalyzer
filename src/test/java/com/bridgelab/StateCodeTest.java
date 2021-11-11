package com.bridgelab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StateCodeTest {
    ReadOperations readObj = new ReadOperations();
    String fileName = "StateCode";

    /**
     * Purpose : Given the States Code CSV file
     * Check to ensure the Number of Record matches
     */
    @Test
    public void givenStateCensusCSVFileCorrect_EnsureNumberOfRecordsMatch() {
        String filePathRead = "./src/main/resources/StateCode.csv";
        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(38, count);
        } catch (StateSensorAnalyserException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Given the State Code CSV File if incorrect
     * Return a Custom Exception
     */
    @Test
    public void givenStateCensusCSVFile_WhenFileNameIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/StateCode";

        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(38, count);
        } catch (StateSensorAnalyserException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Given the State Code CSV File when correct but type incorrect
     * Return a Custom Exception
     */
    @Test
    public void givenStateCensusCSVFile_WhenFileTypeIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/StateCode.pdf";

        try {
            int count = readObj.readDataCount(filePathRead, fileName);
            Assertions.assertEquals(38, count);
        } catch (StateSensorAnalyserException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Given the State Code CSV File when correct but delimiter incorrect
     * Return a Custom Exception
     */
    @Test
    public void givenStateCensusCSVFileCorrect_ButDelimiterIncorrectShouldThrowException() {
        String filePathRead = "./src/main/resources/StateCode.csv";
        String delimiter = ".";
        try {
            if (delimiter.equals(","))
                Assertions.assertTrue(readObj.readDelimiter(filePathRead, delimiter));
            else
                Assertions.assertFalse(readObj.readDelimiter(filePathRead, delimiter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Purpose : Given the State Code CSV File when correct but csv header incorrect
     * Returns a Custom Exception
     */
    @Test
    public void givenStateCensusCSVFileCorrect_ButHeaderIncorrectShouldThrowException() {
        List<String> stringName = new ArrayList<>();
        String filePathRead = "./src/main/resources/StateCode.csv";
        stringName.add("SrNo");
        stringName.add("State");
        stringName.add("Name");
        stringName.add("TN");
        stringName.add("StateCode");

        try {
            boolean flag = readObj.readHeader(filePathRead, stringName);
            if (flag)
                Assertions.assertTrue(flag);
            else
                Assertions.assertFalse(flag);
        } catch (IOException | StateSensorAnalyserException e) {
            e.printStackTrace();
        }
    }
}