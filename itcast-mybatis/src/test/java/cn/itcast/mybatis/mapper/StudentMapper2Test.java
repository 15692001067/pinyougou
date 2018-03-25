package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.Student;
import cn.itcast.mybatis.pojo.Student2;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Config;
import tk.mybatis.mapper.entity.Example;
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

    /**
     * 分页每页2条数据，查询第2页的年龄大于等于14的男性学生并按照年龄降序排序
     */
    @Test
    public void selectByExample() {
        //设置分页
        PageHelper.startPage(2, 2);

        //创建查询对象
        Example example = new Example(Student2.class);

        //创建查询条件对象
        Example.Criteria criteria = example.createCriteria();

        //男性学生
        criteria.andEqualTo("gender", 1);

        //年龄大于等于14
        criteria.andGreaterThanOrEqualTo("age", 14);

        //按照年龄降序排序
        example.orderBy("age").desc();

        //查询
        List<Student2> list = studentMapper2.selectByExample(example);

        PageInfo<Student2> pageInfo = new PageInfo<>(list);

        System.out.println("总记录数：" + pageInfo.getTotal() + "；总页数：" + pageInfo.getPages() + "；当前页号：" +
                pageInfo.getPageNum() +"；页大小：" + pageInfo.getPageSize());

        for (Student2 student : pageInfo.getList()) {
            System.out.println(student);
        }
    }
}