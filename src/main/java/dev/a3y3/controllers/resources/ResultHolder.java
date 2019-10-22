package dev.a3y3.controllers.resources;

/**
 * Holds a Result object containing two possible duplicates. Used to display the result
 * and group the duplicates together.
 *
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
public class ResultHolder {
    public String duplicate1;
    public String duplicate2;

    /**
     * Constructs a ResultHolder object by:
     * 1. Appending a "" to each individual result
     * 2. Appending a , to each individual result.
     *
     * @param duplicate1 the duplicate entry number 1.
     * @param duplicate2 the duplicate entry number 2.
     */
    public ResultHolder(String[] duplicate1, String[] duplicate2) {
        StringBuilder result1 = new StringBuilder();
        for (String d1 : duplicate1) {
            result1.append("\"").append(d1).append("\", ");
        }

        StringBuilder result2 = new StringBuilder();
        for (String d2 : duplicate2) {
            result2.append("\"").append(d2).append("\", ");
        }
        this.duplicate1 = result1.toString();
        this.duplicate2 = result2.toString();
    }
}
