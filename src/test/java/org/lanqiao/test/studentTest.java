package org.lanqiao.test;

import org.junit.Test;
import org.lanqiao.dao.Impl.IStudentMapperImpl;
import org.lanqiao.dao.StudentMapper;
import org.lanqiao.pojo.Student;

import java.io.IOException;
import java.util.List;

public class studentTest {
    @Test
    public void  addStudent() throws IOException {
        StudentMapper studentMapper = new IStudentMapperImpl();
        Student student1= new Student("艾小羊",29,"男");
        studentMapper.addStu(student1);
    }

    @Test
    public void findAllStudent(){
        StudentMapper studentMapper = new IStudentMapperImpl();
        List<Student> allStudentList =studentMapper.findStu();
        for (Student student:allStudentList){
            System.out.println(student);
        }
    }
}
