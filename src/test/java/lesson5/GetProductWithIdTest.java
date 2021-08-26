package lesson5;

import lesson5.dto.Product;
import lesson5.service.ProductService;
import lesson5.util.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import static lesson5.AssertMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProductWithIdTest extends BaseTest {

    @SneakyThrows
    @Test
    void getProductIdTest(){
        Response<Product> response = productService.getIdProduct(NumericField.ID_PRODUCT.getIntMeaning()).execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(NumericField.ID_PRODUCT.getIntMeaning()));
        assertThat(response.body().getTitle(), equalTo("Asteria"));
        assertThat(response.body().getPrice(), equalTo(1000000000));
        assertThat(response.body().getCategoryTitle(),equalTo(CategoryType.FURNITURE.getTitle()));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"ZERO_FRONT_ID_PRODUCT", "SPACE_FRONT_ID_PRODUCT"})
    void getProductStrPositiveIdTest(NumericField numericField){
        Response<Product> response = productService.getStrProduct(numericField.getStrMeaning()).execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(NumericField.ID_PRODUCT.getIntMeaning()));
        assertThat(response.body().getTitle(), equalTo("Asteria"));
        assertThat(response.body().getPrice(), equalTo(1000000000));
        assertThat(response.body().getCategoryTitle(),equalTo(CategoryType.FURNITURE.getTitle()));
    }

    @SneakyThrows
    @Test
    void getProductEmptyIdTest(){
        Response<ResponseBody> response = productService.getEmptyIdProduct(NumericField.EMPTY.getStrMeaning()).execute();
        positiveAssert(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"STRING_MEANING", "CHAR_MEANING"})
    void getProductStrNegativeId400Test(NumericField numericField){
        Response<Product> response = productService.getStrProduct(numericField.getStrMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"SPACE_MEANING", "SPACE_BETWEEN_NUMBER"})
    void getProductStrNegativeId404Test(NumericField numericField){
        Response<Product> response = productService.getStrProduct(numericField.getStrMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"MAX_INT", "NEGATIVE_NUMBER", "ZERO"})
    void getProductIntNegativeIdTest(NumericField numericField){
        Response<Product> response = productService.getIdProduct(numericField.getIntMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @Test
    void getProductBoolTest(){
        Response<Product> response = productService.getBoolProduct(NumericField.BOOL_MEANING.isBoolMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getProductDoubleIdTest(){
        Response<Product> response = productService.getDoubleProduct(NumericField.DOUBLE_MEANING.getDoubleMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getProductLongIdTest(){
        Response<Product> response = productService.getLongProduct(NumericField.LONG_MEANING.getLongMeaning()).execute();
        negativeAssert404(response);
    }
}
