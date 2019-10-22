package dev.a3y3.controllers;

import dev.a3y3.Constants;
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
        for (int i = 0; i < rows.size() - 1; i ++){
            for (int j = i + 1; j < rows.size(); j++){
                String row1 = rows.get(i);
                String row2 = rows.get(j);

            }
        }
    }

    private boolean isPossibleDuplicate(String str1, String str2){
        return false;
    }

    private int getLevenshteinDistance(String str1, String str2) {
        return 0;
    }

    private boolean isMetaphoneEqual(String str1, String str2) {
        return false;
    }
}
