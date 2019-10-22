package dev.a3y3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Soham Dongargaonkar [sd4324] on 10/21/19
 */
public class Constants {
    public final int OFFSET_ID = 0;
    public final int OFFSET_FNAME = 1;
    public final int OFFSET_LNAME = 2;
    public final int OFFSET_COMPANY = 3;
    public final int OFFSET_EMAIL = 4;
    public final int OFFSET_ADDR1 = 5;
    public final int OFFSET_ADDR2 = 6;
    public final int OFFSET_ZIP = 7;
    public final int OFFSET_CITY = 8;
    public final int OFFSET_STATE_LONG = 9;
    public final int OFFSET_STATE_SHORT = 10;
    public final int OFFSET_PHONE = 11;

    public final Map<String, Integer> thresholds = new HashMap<>();
    public final String[] columnNames = new String[]{"id", "first_name", "last_name",
            "company", "email", "address1", "address2", "zip", "city", "state_long",
            "state", "phone"};

    public Constants() {
        thresholds.put("first_name", 2);
        thresholds.put("last_name", 2);
        thresholds.put("company", 2);
        thresholds.put("email", 2);
        thresholds.put("address1", 2);
        thresholds.put("address2", 2);
        thresholds.put("zip", 2);
        thresholds.put("city", 2);
        thresholds.put("state_long", 2);
        thresholds.put("state", 2);
        thresholds.put("phone", 2);
    }
}
