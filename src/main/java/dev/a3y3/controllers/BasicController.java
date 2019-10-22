package dev.a3y3.controllers;

import dev.a3y3.Constants;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
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
        List<String> results = new ArrayList<>();
        model.addAttribute("fileName", fileName);
        model.addAttribute("results", results);

        List<String> fileRows = getFileRows(fileName);
        fillResults(results, fileRows);
        return "index";
    }

    /**
     * Given an input file name, generates a list of all the rows in it. This is
     * helpful for avoiding constant I/O calls, and should be faster than using loops
     * on the file directly.
     *
     * @param fileName the name of the file.
     * @return a {@code List<String>} containing the rows from the file.
     * @throws IOException by BufferedReader.
     */
    private List<String> getFileRows(String fileName) throws IOException {
        BufferedReader in =
                new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        List<String> rows = new ArrayList<>();
        String row;
        while ((row = in.readLine()) != null) {
            rows.add(row);
        }
        return rows;
    }

    private void fillResults(List<String> result, List<String> rows) {
        for (int i = 0; i < rows.size() - 1; i++) {
            for (int j = i + 1; j < rows.size(); j++) {
                String row1 = rows.get(i);
                String row2 = rows.get(j);

            }
        }
    }

    /**
     * Checks if rows row1 and row2 are a possible duplicate of each other.
     * The function checks for all the columns in {@code row1} with the matching column
     * of {@code row2}. It determines that the entire row is a possible duplicate iff:
     * <p>
     * 1. The LevenshteinDistance between them is less than or equal to the
     * threshold for the particular column (defined in Constants.java):
     * {@see int getLevenshteinDistance(String str1, String str2)}
     * 2. They are Metaphones of each other:
     * {@see boolean isMetaphoneEqual(String str1, String str2)}
     *
     * @param row1 the String representation (comma separated) of a row.
     * @param row2 the String representation (comma separated) of a row.
     * @return true if {@code row1} is a possible duplicate of {@code row2}.
     */
    private boolean isPossibleDuplicate(String row1, String row2) {
        String[] colsOfFirst = row1.split(",");
        String[] colsOfSecond = row2.split(",");
        //now, rowsOfFirst[Constants.OFFSET_xxx] will refer to the appropriate column.

        //Assume for now that both the lengths are the same.
        for (int i = 1; i < colsOfFirst.length; i++) {
            String column = constants.columnNames[i];
            int threshold = constants.thresholds.get(column);

            //Variables value1 and value2 denote the values of the respective columns.
            String value1 = colsOfFirst[i];
            String value2 = colsOfSecond[i];
            if (isMetaphoneEqual(value1, value2) || getLevenshteinDistance(value1,
                    value2) <= threshold) {
                return true;
            }
        }
        return false;
    }

    private int getLevenshteinDistance(String str1, String str2) {
        return levenshteinDistance.apply(str1, str2);
    }

    private boolean isMetaphoneEqual(String str1, String str2) {
        str1 = metaphone.encode(str1);
        str2 = metaphone.encode(str2);
        return metaphone.isMetaphoneEqual(str1, str2);
    }
}
