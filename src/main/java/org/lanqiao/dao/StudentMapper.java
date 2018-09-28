package org.lanqiao.dao;

import org.lanqiao.pojo.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    //插入一条数据
    public void addStu(Student student);
    //查询所有数据
    public List<Student> findStu();
    //查询所有数据，返回一个Map<>集
    public Map<String,Student> findStuMap();
    //查询一条数据
    public  Student findOneStuById(int id);
    //查询 姓名，进行模糊查询
    public List<Student> findStuByName(String sname);
    //删除一条数据
    public void deleteStuById(int id);
    //更新数据
    public void updateStuById(Student student);
}
