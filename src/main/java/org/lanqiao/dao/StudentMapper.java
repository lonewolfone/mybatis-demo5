package org.lanqiao.dao;

import org.lanqiao.pojo.Student;

import java.util.List;

public interface StudentMapper {
    //插入一条数据
    public void addStu(Student student);
    //查询所有数据
    public List<Student> findStu();
}
