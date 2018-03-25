package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.Student2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentMapper2Test {

    private StudentMapper2 studentMapper2;

    @Before
    public void setUp() throws Exception {
        //mybatis总配置文件路径
        String resouces = "mybatis-config.xml";

        //利用Resources读取mybatis总配置文件的输入流
        InputStream inputStream = Resources.getResourceAsStream(resouces);

        //创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        MapperHelper mapperHelper = new MapperHelper();

        // 注册自己项目中使用的通用Mapper接口，这里没有默认值，必须手动注册
        mapperHelper.registerMapper(Mapper.class);
        //配置完成后，执行下面的操作
        mapperHelper.processConfiguration(sqlSession.getConfiguration());

        studentMapper2 = sqlSession.getMapper(StudentMapper2.class);

    }

    @Test
    public void insertSelective() {
        Student2 stu = new Student2();
        stu.setAccount("itcast");
        stu.setCreateTime(new Date());
        stu.setGender(1);
        stu.setName("传智播客");
        stu.setAge(12);
        stu.setBirthday(new Date());
        studentMapper2.insertSelective(stu);
    }

    @Test
    public void selectAll() {
        List<Student2> list = studentMapper2.selectAll();
        for (Student2 student : list) {
            System.out.println(student);
        }
    }

    @Test
    public void selectByPrimaryKey() {
        Student2 student2 = studentMapper2.selectByPrimaryKey(7L);
        System.out.println(student2);
    }

    @Test
    public void selectOne() {
        Student2 param = new Student2();
        param.setAccount("lilei");
        Student2 student2 = studentMapper2.selectOne(param);
        System.out.println(student2);

    }

    @Test
    public void updateByPrimaryKeySelective() {
        Student2 stu = new Student2();
        stu.setId(7L);
        stu.setAccount("itcast");
        stu.setName("黑马");
        stu.setAge(15);
        studentMapper2.updateByPrimaryKeySelective(stu);
    }

    @Test
    public void deleteByPrimaryKey() {
        studentMapper2.deleteByPrimaryKey(7L);
    }

    @Test
    public void selectByExample() {
    }
}