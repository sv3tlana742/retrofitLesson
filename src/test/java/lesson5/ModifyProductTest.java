package lesson5;

import lesson5.dto.Product;
import lesson5.enums.NumericField;
import lesson5.enums.TextField;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import static lesson5.AssertMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest extends BaseTest {
    static int price;
    static String title;
    static String category;

    @BeforeEach
    @SneakyThrows
    void getProduct(){
        Response<Product> response = productService.getIdProduct(NumericField.MODIFY_ID.getIntMeaning())
                .execute();
        positiveAssert(response);
        title = response.body().getTitle();
        price = response.body().getPrice();
        category = response.body().getCategoryTitle();
        positiveAssert(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = TextField.class, names = {"RUSSIAN_TITLE", "EMPTY", "SPACE_FRONT_TITLE", "SPACE_TITLE",
            "CHAR_TITLE", "INT_TITLE", "ZERO", "SPACE_BETWEEN_NUMBER", "NULL"})
    void modifyProductTitleTest(TextField textField) {
        Response<Product> response = productService.putProduct(new Product()
                        .withId(NumericField.MODIFY_ID.getIntMeaning())
                        .withTitle(textField.getStrTitle())
                        .withPrice(price)
                        .withCategoryTitle(category))
                .execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(NumericField.MODIFY_ID.getIntMeaning()));
        assertThat(response.body().getTitle(), equalTo(textField.getStrTitle()));
        assertThat(response.body().getPrice(), equalTo(price));
        assertThat(response.body().getCategoryTitle(), equalTo(category));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"PRICE", "ZERO_FRONT_PRICE", "MAX_INT", "NEGATIVE_NUMBER",
            "ZERO", "SPACE_FRONT_PRICE", "SPACE_BETWEEN_NUMBER"})
    void modifyProductPriceTest(NumericField numericField) {
        Response<Product> response = productService.putProduct(new Product()
                        .withId(NumericField.MODIFY_ID_PRICE.getIntMeaning())
                        .withTitle(title)
                        .withPrice(numericField.getIntMeaning())
                        .withCategoryTitle(category))
                .execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(NumericField.MODIFY_ID_PRICE.getIntMeaning()));
        assertThat(response.body().getTitle(), equalTo(title));
        assertThat(response.body().getPrice(), equalTo(numericField.getIntMeaning()));
        assertThat(response.body().getCategoryTitle(), equalTo(category));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = TextField.class, names = {"RUSSIAN_TITLE", "EMPTY", "SPACE_FRONT_TITLE", "SPACE_TITLE"
            ,"CHAR_TITLE", "INT_TITLE", "ZERO", "SPACE_BETWEEN_NUMBER", "NULL_ID", "BOOL_ID"})
    void modifyProductCategoryTest(TextField textField) {
        Response<Product> response = productService.putProduct(new Product()
                        .withId(NumericField.MODIFY_ID_CATEGORY.getIntMeaning())
                        .withTitle(title)
                        .withPrice(price)
                        .withCategoryTitle(textField.getStrTitle()))
                .execute();
        negativeAssert400(response);
    }
}
