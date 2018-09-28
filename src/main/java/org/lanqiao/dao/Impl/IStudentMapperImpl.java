package org.lanqiao.dao.Impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.lanqiao.dao.StudentMapper;
import org.lanqiao.pojo.Student;
import org.lanqiao.utils.mybatisUtils;

import java.util.List;

public class IStudentMapperImpl implements StudentMapper {
    //插入一条数据
    public void addStu(Student student) {
        //根据工具类：Utils包下的mybatisUtils类的方法，获取sqlSession对象
        SqlSession sqlSession= mybatisUtils.getSqlSeesion("mybatis-config.xml");
        //执行sql
        sqlSession.insert("addStudent",student);
        // MySql不自动提交事务，此处为手动提交事务
        sqlSession.commit();
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
    }

    //查询所有数据
    public List<Student> findStu(){
        Logger logger = Logger.getLogger(IStudentMapperImpl.class);
        logger.debug("这是dao的调试日志");
        logger.info("这是info级别的信息");
        //根据工具类：Utils包下的mybatisUtils类的方法，获取sqlSession对象
        SqlSession sqlSession= mybatisUtils.getSqlSeesion("mybatis-config.xml");
        //执行sql
        List<Student> allStudent = sqlSession.selectList("findAllStudent");
        //返回一个结果集，无需提交
        //关闭sqlsession
        mybatisUtils.realeaseSource(sqlSession);
        return allStudent;
    }
}
