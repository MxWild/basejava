package com.urise.webapp.util;

import com.urise.webapp.model.Organization;

public class HtmlUtil {
    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getDateStart()) + " - " + DateUtil.format(position.getDateEnd());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }
}
