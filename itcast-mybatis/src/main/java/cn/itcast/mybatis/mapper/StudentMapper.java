package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.pojo.Student;

import java.util.List;

public interface StudentMapper {

    //查询数据列表
    public List<Student> queryStudents();
}
