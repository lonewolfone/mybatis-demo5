package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.dao.Impl.IStudentMapperImpl;
import org.lanqiao.dao.StudentMapper;
import org.lanqiao.pojo.Student;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class studentTest {

    StudentMapper studentMapper = new IStudentMapperImpl();
    //添加
    @Test
    public void  addStudent() throws IOException {
        Student student1= new Student("艾小羊",29,"男");
        studentMapper.addStu(student1);
    }
    //查询所有
    @Test
    public void findAllStudent(){
        List<Student> allStudentList =studentMapper.findStu();
        for (Student student:allStudentList){
            System.out.println(student);
        }
    }
    //查询所有，返回一个Map集
    @Test
    public void findAllStuMap(){
      Map<String,Student> studentMap= studentMapper.findStuMap();
      Set<String> stringSet = studentMap.keySet();
      for (String setstr : stringSet){
          Student student3 =studentMap.get(setstr);
          System.out.println(student3);
      }
    }
    //查询一条数据
    @Test
    public void findOneStuMap(){
        Student student6 = studentMapper.findOneStuById(15);
        System.out.println(student6);
    }
    //模糊查询
    @Test
    public void findStuByName(){
        List<Student> studentList = studentMapper.findStuByName("三");
        for (Student stu:studentList){
            System.out.println(stu);
        }
    }
    //删除
    @Test
    public void deleteStuById(){
        studentMapper.deleteStuById(20);
    }
    //更新
    @Test
    public void updateStuById(){
        Student student2 = new Student(15,"艾小羊",24,"男");
        studentMapper.updateStuById(student2);
    }


}
