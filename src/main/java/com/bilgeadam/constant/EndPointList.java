package com.bilgeadam.constant;

public class EndPointList {
    public static final String USER = "/user";
    public static final String MOVIE = "/movie";
    public static final String COMMENT = "/comment";
    public static final String GENRE = "/genre";

    /*
    GENERAL END POINTS
     */
    public static final String SAVE = "/save";
    public static final String DELETE = "/delete";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_BY_ID = "/find-by-id";
    public static final String FIND_BY_NAME_CONTAINS = "/find-by-name-contains";

    /*
    USER END POINTS
     */
    public static final String UPDATE_DTO = "/update-dto";
    public static final String UPDATE_MAPPER = "/update-mapper";
    public static final String REGISTER_MAPPER = "/register-mapper";
    public static final String REGISTER_DTO = "/register-dto";
    public static final String REGISTER = "/register";
    public static final String LOGIN_MAPPER = "/login-mapper";
    public static final String LOGIN_DTO = "/login-dto";
    public static final String LOGIN = "/login";
    public static final String CUSTOM_LOGIN = "/custom-login";
    public static final String FIND_BY_ORDER_BY_NAME = "/find-by-order-by-name";
    public static final String FIND_BY_NAME = "/find-by-name";
    public static final String EXISTS_NAME = "/exits-name";
    public static final String FIND_BY_EMAIL = "/find-by-email";
    public static final String FIND_PASSWORD_GREATER_THAN_JPQL = "/find-password-greater-than-jpql";
    public static final String FIND_PASSWORD_GREATER_THAN_NATIVE = "/find-password-greater-than-native";
    public static final String FIND_EMAIL_ENDS_WITH = "/find-email-ends-with";

    /*
    MOVIE END POINTS
     */
    public static final String FIND_BY_RATING_GREATER_THAN = "/find-by-rating-greater-than";
    public static final String FIND_BY_PREMIERED_BEFORE = "/find-by-premiered-before";
    public static final String COUNT_IDENTICAL_RATING = "/count-identical-rating";
    public static final String COUNT_IDENTICAL_RATING_GROUP_BY_RATING = "/count-rating-group-by-rating";
    public static final String FIND_BY_RATING_IN = "/find-by-rating-in";

    /*
    COMMENT END POINTS
     */
    public static final String FIND_BY_MOVIE_DATE_BETWEEN = "/find-by-movie-date-between";
    public static final String FIND_BY_USER_ID_AND_DATE = "/find-by-user-id-and-date";
}
