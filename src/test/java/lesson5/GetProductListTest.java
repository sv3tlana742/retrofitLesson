package lesson5;

import lesson5.dto.GetCategoryResponse;
import lesson5.dto.Product;
import lesson5.service.ProductService;
import lesson5.util.RetrofitUtils;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import static lesson5.AssertMethod.*;

public class GetProductListTest extends BaseTest {

    @SneakyThrows
    @Test
    void getProductPositiveTest(){
        Response<ResponseBody> response = productService.getProduct().execute();
        positiveAssert(response);
    }
}
