package lesson5;

import com.github.javafaker.Faker;
import lesson5.dto.Product;
import lesson5.enums.CategoryType;
import lesson5.enums.NumericField;
import lesson5.enums.TextField;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import static lesson5.AssertMethod.positiveCreateAssert;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProductPositiveTest extends BaseTest {
    Product product;
    Faker faker = new Faker();
    int id;

    @BeforeEach
    void setUp(){
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice((int) (Math.random() * 10000));
    }

    @Test
    @SneakyThrows
    void createProductInFoodCategoryTest() {
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = TextField.class, names = {"RUSSIAN_TITLE", "EMPTY", "SPACE_FRONT_TITLE", "SPACE_TITLE",
            "CHAR_TITLE", "INT_TITLE", "ZERO", "SPACE_BETWEEN_NUMBER", "NULL"})
    void createProductTitleTest(TextField textField) {
        Response<Product> response = productService
                .createProduct(new Product()
                .withTitle(textField.getStrTitle())
                .withCategoryTitle(CategoryType.FOOD.getTitle())
                .withPrice(1))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(textField.getStrTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
    }

    @SneakyThrows
    @Test
    void createProductWithoutTitleTest() {
        Response<Product> response = productService
                .createProduct(new Product()
                        .withCategoryTitle(CategoryType.FOOD.getTitle())
                        .withPrice(1))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"PRICE", "ZERO_FRONT_PRICE", "MAX_INT", "NEGATIVE_NUMBER",
            "ZERO", "SPACE_FRONT_PRICE", "SPACE_BETWEEN_NUMBER", "EMPTY", "SPACE_MEANING", "STRING_MEANING", "CHAR_MEANING"})
    void createProductPriceTest(NumericField numericField) {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(CategoryType.FOOD.getTitle())
                        .withPrice(numericField.getIntMeaning()))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(numericField.getIntMeaning()));
    }

    @SneakyThrows
    @Test
    void createProductWithoutPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(CategoryType.FOOD.getTitle()))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
    }

    @SneakyThrows
    @Test
    void createProductBoolPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(CategoryType.FOOD.getTitle())
                        .withPrice(NumericField.BOOL_MEANING.getIntMeaning()))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.BOOL_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @Test
    void createProductDoublePriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(CategoryType.FOOD.getTitle())
                        .withPrice(NumericField.DOUBLE_MEANING.getIntMeaning()))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.DOUBLE_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @Test
    void createProductLongPriceTest() {
        Response<Product> response = productService
                .createProduct(product = new Product()
                        .withTitle(faker.food().ingredient())
                        .withCategoryTitle(CategoryType.FOOD.getTitle())
                        .withPrice(NumericField.LONG_MEANING.getIntMeaning()))
                .execute();
        id = response.body().getId();
        positiveCreateAssert(response);
        assertThat(response.body().getTitle(), equalTo(product.getTitle()));
        assertThat(response.body().getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle()));
        assertThat(response.body().getPrice(), equalTo(NumericField.LONG_MEANING.getIntMeaning()));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productService.deleteIntIdProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
