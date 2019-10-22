package dev.a3y3.controllers;

import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
@Controller
public class BasicController {

    @GetMapping("/")
    public String index(@RequestParam(name = "fileName", required = false, defaultValue
            = "normal.csv") String fileName, Model model) {
        model.addAttribute("fileName", fileName);
        return "index";
    }
}
