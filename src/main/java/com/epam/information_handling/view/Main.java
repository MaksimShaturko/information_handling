package com.epam.information_handling.view;

import com.epam.information_handling.entity.TextElement;
import com.epam.information_handling.entity.Text;
import com.epam.information_handling.logic.Variant14;
import com.epam.information_handling.logic.Variant3;
import com.epam.information_handling.logic.Variant8;
import com.epam.information_handling.parser.ParserText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Logger logger =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException {
        logger.info("The application has been started");

        StringBuilder textBuilder = new StringBuilder();

        try {
            File inputFile = new File("src/main/resources/InputText.txt");
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                textBuilder = textBuilder.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.error("The main file InputText.txt was not found. App will" +
                    " be stopped.");
            throw e;
        }

        String InputText = new String(textBuilder);
        ParserText parserText = new ParserText();
        List<TextElement> listOfTextBlocksObjects = parserText.parse(InputText);
        Text wholeText =
                new Text(listOfTextBlocksObjects);

        logger.debug("Start building the output text");
        String outputText = wholeText.getValue().toString();
        logger.debug("The output text has been built");

        try {
            File outputFile = new File("src/main/resources/OutputText.txt");
            PrintWriter pr = new PrintWriter(outputFile);
            pr.println(outputText);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "output text will be output to the console");
            System.out.println(outputText);
        }

        Variant3 variant3 = new Variant3();
        String resultOfVariant3 = variant3.findUniqueWord(wholeText);

        try {
            File variant3File = new File("src/main/resources/Variant3.txt");
            PrintWriter pr = new PrintWriter(variant3File);
            pr.println(resultOfVariant3);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant3 will be output to the console");
            System.out.println(resultOfVariant3);
        }

        Variant8 variant8 = new Variant8();
        List<String> listOfSortedWords =
                variant8.sortVowelByConsonant(wholeText);
        try {
            File variant8File = new File("src/main/resources/Variant8.txt");
            PrintWriter pr = new PrintWriter(variant8File);
            pr.println(listOfSortedWords);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant8 will be output to the console");
            System.out.println(listOfSortedWords);
        }

        Variant14 variant14 = new Variant14();
        StringBuilder largestPalindrome = variant14.findPalindrome(wholeText);
        try {
            File variant14File = new File("src/main/resources/Variant14.txt");
            PrintWriter pr = new PrintWriter(variant14File);
            pr.println(largestPalindrome);
            pr.close();
        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant14 will be output to the console");
            System.out.println(largestPalindrome);
        }

        logger.info("The app has been successfully finished");
    }
}