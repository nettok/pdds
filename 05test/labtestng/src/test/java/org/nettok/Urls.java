package org.nettok;

import java.util.regex.Pattern;

public final class Urls {

    public static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php";
    public static final String PATTERN_TEMPLATE = "^https?://.*%s$";

    public static final String LOGIN_PATH = "/auth/login";
    public static final Pattern LOGIN_PATTERN = pattern(LOGIN_PATH);

    public static final String INDEX_PATH = "/dashboard/index";
    public static final Pattern INDEX_PATTERN = pattern(INDEX_PATH);

    public static final String ADMIN_PATH = "/admin/viewAdminModule";
    public static final Pattern ADMIN_PATTERN = pattern(ADMIN_PATH);

    public static final String ADMIN_USER_MANAGEMENT_PATH = "/admin/viewSystemUsers";
    public static final Pattern ADMIN_USER_MANAGEMENT_PATTERN = pattern(ADMIN_USER_MANAGEMENT_PATH);

    public static final String ADMIN_SAVE_USER_PATH = "/admin/saveSystemUser";
    public static final Pattern ADMIN_SAVE_USER_PATTERN = pattern(ADMIN_SAVE_USER_PATH);


    private Urls() {
        // Utility class
    }

    public static Pattern pattern(final String path) {
        return Pattern.compile(PATTERN_TEMPLATE.formatted(path));
    }

    public static String getLoginUrl() {
        return BASE_URL + LOGIN_PATH;
    }

    public static String getIndexUrl() {
        return BASE_URL + INDEX_PATH;
    }

    public static String getAdminUrl() {
        return BASE_URL + ADMIN_PATH;
    }

    public static String getAdminUserManagementUrl() {
        return BASE_URL + ADMIN_USER_MANAGEMENT_PATH;
    }

    public static String getAdminSaveUserUrl() {
        return BASE_URL + ADMIN_SAVE_USER_PATH;
    }
}
