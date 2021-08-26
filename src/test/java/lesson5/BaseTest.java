package lesson5;

import lesson5.service.ProductService;
import lesson5.util.RetrofitUtils;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    static ProductService productService;

    @BeforeAll
    static void beforeAll(){
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }
}
