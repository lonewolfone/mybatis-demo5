## 一、MyBatis的核心API

- Resources   Resources类，顾名思义就是资源，用于读取资源文件
- SqlSessionFactory
- SqlSession

## 二、使用mybatis完成crud

- 删除

  **StudentMapper.xml中**

  ```xml
  <!--sql语句-->
      <!--方法  参数类型：整型-->
      <delete id="deleteStuById" parameterType="_int">
          delete from stu where id =${_parameter}
      </delete>
  ```

  **IStudentDaoImpl中**

  ```java
   @Override
      public void deleteStuById(int id) {
          //执行sql
          sqlSession.delete("deleteStuById",id);
          //提交
          sqlSession.commit();
          //关闭sqlsession
          mybatisUtils.realeaseSource(sqlSession);
  
      }
  ```

  **studentTest中**

  ```java
   @Test
      public void deleteStuById(){
          studentMapper.deleteStuById(20);
      }
  ```

  遇到的问题

  ```java
  org.apache.ibatis.reflection.ReflectionException: There is no getter for property named '_id' in 'class java.lang.Integer'
  ```

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\26.png)

  或者改为：

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\27.png)

- 修改/更新

  **StudentMapper.xml中**

  ```java
  <!--sql语句-->
      <!--方法  参数类型：整型-->
      <update id="updateStudentById" parameterType="Student">
          update stu set sname = #{sname} where id = #{id}
      </update>
  ```

- 查询

  - 当当
  - 当当
  - 当当
  - 当当
  - 当当

- 当当

- 当当