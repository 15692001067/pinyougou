package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.Student;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class StudentMapperTest {

    private StudentMapper studentMapper;

    @Before
    public void setUp() throws Exception {

        //mybatis总配置文件路径
        String resouces = "mybatis-config.xml";

        //利用Resources读取mybatis总配置文件的输入流
        InputStream inputStream = Resources.getResourceAsStream(resouces);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        studentMapper = sqlSession.getMapper(StudentMapper.class);

    }

    @Test
    public void queryStudents() {
        List<Student> list = studentMapper.queryStudents();
        for (Student student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void queryStudentsInPage() {
        //设置分页；页号，页大小；只对紧接着执行的查询语句生效
        PageHelper.startPage(2, 2);

        List<Student> list = studentMapper.queryStudents();
        //创建分页信息对象
        PageInfo<Student> pageInfo = new PageInfo<>(list);

        System.out.println("总记录数：" + pageInfo.getTotal() + "；总页数：" + pageInfo.getPages() + "；当前页号：" +
                pageInfo.getPageNum() +"；页大小：" + pageInfo.getPageSize());

        for (Student student : pageInfo.getList()) {
            System.out.println(student);
        }
    }
}