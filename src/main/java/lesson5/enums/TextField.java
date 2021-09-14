package lesson5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public enum TextField {

    RUSSIAN_TITLE("Продукты"),
    EMPTY(""),
    SPACE_FRONT_TITLE(" Food"),
    SPACE_TITLE(" "),
    CHAR_TITLE("/=+&*%$#@"),
    BIG_TITLE("Per cu iracundia splendide. Mandamus abhorreant " +
            "deseruisse mea at, mea elit deserunt persequeris at, in putant fuisset " +
            "honestatis qui. Odio contentiones sed cu, usu commodo prompta prodesset id. " +
            "Nisl omittam complectitur pro an, quem omnes munere id vix. Ceteros assentior omittantur cum ad."),
    INT_TITLE(2),
    ZERO(0),
    SPACE_BETWEEN_NUMBER(1 + " " + 2),
    NULL_ID(null),
    BOOL_ID(true),
    NULL(null);

    @Getter
    private int intTitle;
    @Getter
    private boolean boolTitle;
    @Getter
    private String strTitle;

    TextField(int title) {
        this.intTitle = title;
    }
    TextField(boolean title) {
        this.boolTitle = title;
    }
    TextField(String title) {
        this.strTitle = title;
    }
}
