package dev.a3y3.controllers;

import com.opencsv.CSVReader;
import dev.a3y3.controllers.resources.Constants;
import dev.a3y3.controllers.resources.ResultHolder;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller for index. Has methods that process data inside a CSV file and return it
 * for displaying it.
 *
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
@Controller
public class BasicController {
    private final Constants constants = new Constants();
    private LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    private Metaphone metaphone = new Metaphone();

    @GetMapping("/")
    public String index(@RequestParam(name = "fileName", required = false, defaultValue
            = "normal.csv") String fileName, Model model) throws IOException {
        List<ResultHolder> results = new ArrayList<>();
        List<String[]> nonDuplicates = new ArrayList<>();
        model.addAttribute("fileName", fileName);
        model.addAttribute("results", results);
        model.addAttribute("nonDuplicates", nonDuplicates);

        List<String[]> fileRows = getFileRows(fileName);
        fillResults(results, nonDuplicates, fileRows);
        return "index";
    }

    /**
     * Given an input file name, generates a list of all the rows in it. This is
     * helpful for avoiding constant I/O calls, and should be faster than using loops
     * on the file directly.
     *
     * @param fileName the name of the file.
     * @return a {@code List<String[]>} containing the rows from the file.
     * @throws IOException by {@code reader.readAll()}.
     */
    private List<String[]> getFileRows(String fileName) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(fileName));
        return reader.readAll();
    }

    /**
     * Fills the {@code results} and {@code nonDuplicates} list by calling the appropriate functions.
     *
     * @param results       the results list that is to be displayed
     * @param nonDuplicates the list that is to be filled with rows that are verified
     *                      to not be duplicates.
     * @param rows          the rows as read from the CSV file.
     */
    private void fillResults(List<ResultHolder> results, List<String[]> nonDuplicates,
                             List<String[]> rows) {
        Set<String[]> duplicateSet = new HashSet<>();
        for (int i = 1; i < rows.size() - 1; i++) {
            String[] row1 = rows.get(i);
            for (int j = i + 1; j < rows.size(); j++) {
                String[] row2 = rows.get(j);
                if (isPossibleDuplicate(row1, row2)) {
                    duplicateSet.add(row1);
                    duplicateSet.add(row2);
                    results.add(new ResultHolder(row1, row2));
                }
            }
            if (!duplicateSet.contains(row1)) {
                nonDuplicates.add(row1);
            }
        }
    }

    /**
     * Checks if rows row1 and row2 are a possible duplicate of each other.
     * The function checks for all the columns in {@code row1} with the matching column
     * of {@code row2}. It uses a weighted average of all columns and determines that the
     * row is a possible duplicate iff:
     * <p>
     * 1. The weighted average is less than a threshold, specified in Constants.THRESHOLD
     * [REMOVED]: 2. They are Metaphones of each other:
     * {@see boolean isMetaphoneEqual(String str1, String str2)}
     *
     * @param row1 the String representation (comma separated) of a row.
     * @param row2 the String representation (comma separated) of a row.
     * @return true if {@code row1} is a possible duplicate of {@code row2}.
     */
    private boolean isPossibleDuplicate(String[] row1, String[] row2) {
        //now, rowsOfFirst[Constants.OFFSET_xxx] will refer to the appropriate column.

        //Assume for now that both the lengths are the same.
        double weightedAverage = 0;
        for (int i = 1; i < row1.length; i++) {
            String column = constants.columnNames[i];

            //Variables value1 and value2 denote the values of the respective columns.
            String value1 = row1[i];
            String value2 = row2[i];

            int distance = getLevenshteinDistance(value1, value2);
            int priority = constants.priorities.get(column);
            weightedAverage += priority * distance;
        }
        weightedAverage /= constants.PRIORITY_SUM;
        return weightedAverage <= constants.THRESHOLD;
    }

    /**
     * @return the Levenshtein Distance between {@code str1} and {@code str2}.
     */
    private int getLevenshteinDistance(String str1, String str2) {
        return levenshteinDistance.apply(str1, str2);
    }

    /**
     * @return true if {@code str1} is metaphonically equal to {@code str2}.
     */
    private boolean isMetaphoneEqual(String str1, String str2) {
        str1 = metaphone.encode(str1);
        str2 = metaphone.encode(str2);
        return metaphone.isMetaphoneEqual(str1, str2);
    }
}
