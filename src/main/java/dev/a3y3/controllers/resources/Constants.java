package dev.a3y3.controllers.resources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
public class Constants {

    private final int PRIORITY_HIGH = 1;
    private final int PRIORITY_MEDIUM = 8;
    private final int PRIORITY_LOW = 13;

    public final Map<String, Integer> thresholds = new HashMap<>();
    public final String[] columnNames = new String[]{"id", "first_name", "last_name",
            "company", "email", "address1", "address2", "zip", "city", "state_long",
            "state", "phone"};

    public final Map<String, Integer> priorities = new HashMap<>();
    public int PRIORITY_SUM;
    public final int THRESHOLD = 50;

    public Constants() {
        thresholds.put("first_name", 2);
        thresholds.put("last_name", 2);
        thresholds.put("company", 3);
        thresholds.put("email", 2);
        thresholds.put("address1", 3);
        thresholds.put("address2", 2);
        thresholds.put("zip", 1);
        thresholds.put("city", 2);
        thresholds.put("state_long", 2);
        thresholds.put("state", 1);
        thresholds.put("phone", 2);

        priorities.put("first_name", PRIORITY_HIGH);
        priorities.put("last_name", PRIORITY_HIGH);
        priorities.put("email", PRIORITY_HIGH);
        priorities.put("address1", PRIORITY_HIGH);
        priorities.put("phone", PRIORITY_HIGH);

        priorities.put("company", PRIORITY_MEDIUM);
        priorities.put("address2", PRIORITY_MEDIUM);

        priorities.put("zip", PRIORITY_LOW);
        priorities.put("city", PRIORITY_LOW);
        priorities.put("state_long", PRIORITY_LOW);
        priorities.put("state", PRIORITY_LOW);

        for (int p : priorities.values()) {
            PRIORITY_SUM += p;
        }
    }
}
