package org.lanqiao.dao.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.lanqiao.dao.StudentMapper;
import org.lanqiao.pojo.Student;
import org.lanqiao.utils.mybatisUtils;

import java.util.List;
import java.util.Map;

public class IStudentMapperImpl implements StudentMapper {
    //公共部分
    Logger logger = Logger.getLogger(IStudentMapperImpl.class);
    //根据工具类：Utils包下的mybatisUtils类的方法，获取sqlSession对象
    SqlSession sqlSession= mybatisUtils.getSqlSeesion("mybatis-config.xml");

    //插入一条数据
    public void addStu(Student student) {
        //执行sql
        sqlSession.insert("addStudent",student);
        // MySql不自动提交事务，此处为手动提交事务
        sqlSession.commit();
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
    }

    //查询所有数据
    public List<Student> findStu(){
        logger.debug("这是dao的调试日志");
        logger.info("这是info级别的信息");
        //执行sql
        List<Student> allStudent = sqlSession.selectList("findAllStudent");
        //返回一个结果集，无需提交
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
        return allStudent;
    }

    //查询所有，返回一个Map集
    @Override
    public Map<String, Student> findStuMap() {
        //执行sql
        Map<String,Student> studentMap= sqlSession.selectMap("findAllStudent","sname");//第一个参数：执行的Sql语句，第二额参数：Map中的key值
        //返回一个结果集，无需提交
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
        return studentMap;
    }
    //查询一条数据
    @Override
    public Student findOneStuById(int id) {
        //执行sql
        Student student= sqlSession.selectOne("findOneById",id);
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
        return student;
    }
    //根据姓名，模糊查询
    @Override
    public List<Student> findStuByName(String sname) {
        //执行sql
        List<Student> studentList= sqlSession.selectList("findStudentByName",sname);
        return studentList;
    }

    //删除一条数据
    @Override
    public void deleteStuById(int id) {
        //执行sql
        sqlSession.delete("deleteStuById",id);
        //提交
        sqlSession.commit();
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);

    }

    @Override
    public void updateStuById(Student student) {
        //执行sql
        sqlSession.update("updateStudentById",student);
        //提交
        sqlSession.commit();
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
    }
}
