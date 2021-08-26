package lesson5;

import lombok.Getter;

public enum NumericField {

    MAX_INT(2_147_483_647),
    NEGATIVE_NUMBER(-1),
    ZERO(0),
    ZERO_FRONT_ID(01),
    ID_CATEGORY(1),
    EMPTY(""),
    SPACE_FRONT_ID(" " + 2),
    SPACE_MEANING(" "),
    STRING_MEANING("Hello"),
    NAME_CATEGORY(CategoryType.FOOD.getTitle()),
    CHAR_MEANING("/=+&*%$#@"),
    SPACE_BETWEEN_NUMBER(1 + " " + 2),
    BOOL_MEANING(true),
    DOUBLE_MEANING(1.5),
    LONG_MEANING(9_223_372_036_854_775L),
    ID_PRODUCT(9093),
    ZERO_FRONT_ID_PRODUCT("09093"),
    SPACE_FRONT_ID_PRODUCT(" 9093"),
    ZERO_FRONT_PRICE(0100),
    SPACE_FRONT_PRICE(" " + 2),
    PRICE(1000),
    MODIFY_ID(9727),
    MODIFY_ID_PRICE(9774),
    MODIFY_ID_CATEGORY(9085);



    @Getter
    private int intMeaning;
    @Getter
    private long longMeaning;
    @Getter
    private double doubleMeaning;
    @Getter
    private boolean boolMeaning;
    @Getter
    private String strMeaning;

    NumericField(int id) {
        this.intMeaning = id;
    }
    NumericField(long id) {
        this.longMeaning = id;
    }
    NumericField(double id) {
        this.doubleMeaning = id;
    }
    NumericField(boolean id) {
        this.boolMeaning = id;
    }
    NumericField(String id) {
        this.strMeaning = id;
    }
}
