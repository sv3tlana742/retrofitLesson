package lesson5;

import com.github.javafaker.Faker;
import lesson5.dto.Product;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import static lesson5.AssertMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest extends BaseTest {
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
    @SneakyThrows
    void createProductForDelete() {
        Response<Product> response = productService.createProduct(product).execute();
        id = response.body().getId();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void deleteProduct() {
        createProductForDelete();
        Response<ResponseBody> response = productService.deleteIntIdProduct(id).execute();
        positiveAssert(response);
    }

    @SneakyThrows
    @Test
    void deleteProductRepeat() {
        createProductForDelete();
        productService.deleteIntIdProduct(id).execute();
        Response<ResponseBody> response = productService.deleteIntIdProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"ZERO_FRONT_ID", "ID_CATEGORY", "SPACE_FRONT_ID",
            "MAX_INT", "NEGATIVE_NUMBER", "ZERO","SPACE_BETWEEN_NUMBER"})
    void deleteProductIntTest(NumericField numericField) {
        Response<ResponseBody> response = productService.deleteIntIdProduct(numericField.getIntMeaning()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names ={"STRING_MEANING", "NAME_CATEGORY", "EMPTY", "CHAR_MEANING"})
    void deleteProductStringTest(NumericField numericField) {
        Response<ResponseBody> response = productService.deleteStringIdProduct(numericField.getStrMeaning()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @Test
    void deleteProductDoubleTest(){
        Response<ResponseBody> response = productService.deleteDoubleIdProduct(NumericField.DOUBLE_MEANING.getDoubleMeaning()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @Test
    void deleteProductBooleanTest(){
        Response<ResponseBody> response = productService.deleteBoolIdProduct(NumericField.BOOL_MEANING.isBoolMeaning()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }

    @SneakyThrows
    @Test
    void deleteProductBoolTest(){
        Response<ResponseBody> response = productService.deleteLongIdProduct(NumericField.LONG_MEANING.getLongMeaning()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(false));
    }
}
