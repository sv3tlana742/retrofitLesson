package lesson5;

import com.github.javafaker.Faker;
import lesson5.db.dao.CategoriesMapper;
import lesson5.db.model.CategoriesExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Main {
    private static String resource = "mybatisConfig.xml";
    public static void main (String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();
        CategoriesMapper categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);

        long categoriesCount = categoriesMapper.countByExample(new CategoriesExample());
        System.out.println(categoriesCount);
    }

    private static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        return sqlSessionFactory.openSession(true);
    }
}
