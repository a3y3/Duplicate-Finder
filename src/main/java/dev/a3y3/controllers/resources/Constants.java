package dev.a3y3.controllers.resources;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds constants that can be used by other classes. This class exists separately to
 * avoid clutter in the controller classes.
 *
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
public class Constants {

    public final String[] columnNames = new String[]{"id", "first_name", "last_name",
            "company", "email", "address1", "address2", "zip", "city", "state_long",
            "state", "phone"};

    public final Map<String, Integer> priorities = new HashMap<>();
    public int PRIORITY_SUM;
    public final double THRESHOLD = 2.0;

    public Constants() {
        int PRIORITY_HIGH = 1;
        priorities.put("first_name", PRIORITY_HIGH);
        priorities.put("last_name", PRIORITY_HIGH);
        priorities.put("email", PRIORITY_HIGH);
        priorities.put("address1", PRIORITY_HIGH);
        priorities.put("phone", PRIORITY_HIGH);

        int PRIORITY_MEDIUM = 8;
        priorities.put("company", PRIORITY_MEDIUM);
        priorities.put("address2", PRIORITY_MEDIUM);

        int PRIORITY_LOW = 13;
        priorities.put("zip", PRIORITY_LOW);
        priorities.put("city", PRIORITY_LOW);
        priorities.put("state_long", PRIORITY_LOW);
        priorities.put("state", PRIORITY_LOW);

        for (int p : priorities.values()) {
            PRIORITY_SUM += p;
        }
    }
}
