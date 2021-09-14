package lesson5;

import lesson5.dto.GetCategoryResponse;
import lesson5.enums.CategoryType;
import lesson5.enums.NumericField;
import lesson5.service.CategoryService;
import lesson5.util.RetrofitUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import retrofit2.Response;

import static lesson5.AssertMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetCategoryTest {
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll(){
        categoryService = RetrofitUtils.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"ZERO_FRONT_ID_CATEGORY", "ID_CATEGORY"})
    void getCategoryIntIdPositiveTest(NumericField numericField){
        Response<GetCategoryResponse> response = categoryService.getCategory(numericField.getIntMeaning()).execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(CategoryType.FOOD.getId()));
        assertThat(response.body().getTitle(), equalTo(CategoryType.FOOD.getTitle()));
        response.body().getProducts().forEach(product ->
            assertThat(product.getCategoryTitle(), equalTo(CategoryType.FOOD.getTitle())));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"SPACE_FRONT_ID_CATEGORY"})
    void getCategoryIntIdTest(NumericField numericField){
        Response<GetCategoryResponse> response = categoryService.getCategory(numericField.getStrMeaning()).execute();
        positiveAssert(response);
        assertThat(response.body().getId(), equalTo(CategoryType.ELECTRONICS.getId()));
        assertThat(response.body().getTitle(), equalTo(CategoryType.ELECTRONICS.getTitle()));
        response.body().getProducts().forEach(product ->
                assertThat(product.getCategoryTitle(), equalTo(CategoryType.ELECTRONICS.getTitle())));
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"MAX_INT", "NEGATIVE_NUMBER", "ZERO"})
    void getCategoryIntIdNegativeTest(NumericField numericField){
        Response<GetCategoryResponse> response = categoryService.getCategory(numericField.getIntMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"EMPTY", "SPACE_BETWEEN_NUMBER"})
    void getCategoryStrIdTest(NumericField numericField){
        Response<GetCategoryResponse> response = categoryService.getCategory(numericField.getStrMeaning()).execute();
        negativeAssert404(response);
    }

    @SneakyThrows
    @ParameterizedTest
    @EnumSource(value = NumericField.class, names = {"STRING_MEANING", "NAME_CATEGORY", "CHAR_MEANING"/*, "SPACE_MEANING"*/})
    void getCategoryStringTest(NumericField numericField){
        Response<GetCategoryResponse> response = categoryService.getCategory(numericField.getStrMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryDoubleIdTest(){
        Response<GetCategoryResponse> response = categoryService.getCategory(NumericField.DOUBLE_MEANING.getDoubleMeaning()).execute();
       negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryBooleanIdTest(){
        Response<GetCategoryResponse> response = categoryService.getCategory(NumericField.BOOL_MEANING.isBoolMeaning()).execute();
        negativeAssert400(response);
    }

    @SneakyThrows
    @Test
    void getCategoryBoolIDTest(){
        Response<GetCategoryResponse> response = categoryService.getCategory(NumericField.LONG_MEANING.getLongMeaning()).execute();
        negativeAssert404(response);
    }
}