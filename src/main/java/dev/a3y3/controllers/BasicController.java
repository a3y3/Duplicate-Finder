package dev.a3y3.controllers;

import dev.a3y3.Constants;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Value;
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



    @GetMapping("/")
    public String index(@RequestParam(name = "fileName", required = false, defaultValue
            = "normal.csv") String fileName, Model model) throws IOException {
        List<String> results = new ArrayList<>();
        model.addAttribute("fileName", fileName);
        model.addAttribute("results", results);

        List<String> fileRows = getFileRows(fileName);
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

    private int getLevenshteinDistance(String a, String b){
        return 0;
    }

    private boolean isMetaphoneEqual(String str1, String str2){
        return false;
    }
}
