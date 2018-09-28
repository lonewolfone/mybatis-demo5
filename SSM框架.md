# SSM框架

SSM，即SpringMVC、Spring与MyBatis三大框架。三者在三层架构中所处的位置不同，功能不同，各司其职。

- SpringMVC:作为View层的实现者，完成用户的请求接受功能。SpringMVC的Controller作为整个应用的控制器，完成用户的响应及对用户请求的转发。
- MyBatis:作为Dao层的实现者，完成对数据库的增删改查功能。
- Spring:以整个官家的大管家的身份出现。整个应用中所有的Bean的生命周期行为，均由Spring管理。及整个应用中所有对象的创建、初始化、销毁、及对对象健关联关系的维护，均由Spring进行管理。

![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\01.png)

​      **View层**						 **Service层**					 **Dao层**

框架的必要性：

- 消除重复

- 花繁为简

- 减少依赖，消除耦合

------

## 一、JavaEE开发采用常见三层架构

将整个应用划分为：

1、表现层(View层)

主要对用户的请求接受，以及数据的返回，为客户端提供应用程序的访问。负责处理与客户的交互操作(Struts2/SpringMVC)。

2、业务层(Dao/Service层)

主要是针对具体问题的操作，即对数据层的操作，对数据业务逻辑处理。负责复杂的业务逻辑计算和判断。(Spring)

3、持久层(Controller层)

主要是对原始数据(数据库或文本文件等存放数据的形式)的操作层，而不是指原始数据，是对数据的操作，而不是数据库，具体为业务逻辑层或表示层提供数据服务。负责将数据进行持久化操作。(hibernate/Mybatis)

*****区分层次的目的：高内聚低耦合，单一责任原则(低耦合：过多功能放一个类中完成，减少类与类间相互依赖）。

------

## 二、MyBatis：持久层框架

### 1、ORM(包括MyBatis框架）：对象关系映射

​	 应用开发中，需要将Java对象存储到数据表中，即需要将Java对象转换为数据库，处理数据间的管理关系。

​	 为解决面向对象与关系数据库存在的互不匹配的现象的技术。通过使用描述对象和数据库之间映射的元数据，将Java程序中的对象自动持久化到关系数据库中。避免直接使用SQL语句对关系型数据库中的数据进行操作。

#### 	A.ORM主要解决对象 -- 关系的映射：

​	   面向对象			面向关系

| 类   | 表     |
| :--- | ------ |
| 对象 | 表的行 |
| 属性 | 表的列 |

#### 	B.ORM的实现思想：

​	   将关系数据库中表中的记录映射成为对象，以对象的形式展现，将数据库的操作转为对对象的操作。

​	    ORM目的：以面向对象的思想来实现对数据库的操作。

​	    ORM:采用元数据来描述：对象 -- 关系映射细节：

​			元数据(即对数据的描述)：通常采用XML格式，存放在专门的对象 -- 关系映射文件中。

#### 	 C.目前流行的ORM框架：

​		1.JPA

​		2.Hibernate

​		3.MyBatis:本是apache的一个开源项目iBatis，提供的持久层框架包括：SQL Maps 和 Dao,允许开发人							    员直接编写SQL等。

### 2、什么是MyBatis?

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。

### 3、MyBatis的入门

#### a.MyBatis的下载

[https://github.com/mybatis]: https://github.com/mybatis

#### b.新建一个项目

- ##### 新建一个Maven

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\02.png)

  ------

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\03.png)

- #####引入依赖(在配置文件pom.xml中)：

  [http://www.mybatis.org/mybatis-3/]: http://www.mybatis.org/mybatis-3/

  ```properties
  <dependencies>
          <!--MyBatis依赖-->
          <dependency>
              <groupId>org.mybatis</groupId>
              <artifactId>mybatis</artifactId>
              <version>3.4.4</version>
          </dependency>
          <!--引入mysql依赖-->
          <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>5.1.46</version>
          </dependency>
           <!--测试：Test-->
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>4.12</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  ```

- ##### 创建链接数据源和事务管理的配置文件  mybatis-config,xml(放在resources)

  ```properties
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/world/"/>
                  <property name="username" value="root"/>
                  <property name="password" value="root"/>
              </dataSource>
          </environment>
      </environments>
     <!--注册映射文件(StudentMapper.xml)-->
      <mappers>
          <mapper resource="org/lanqiao/dao/StudentMapper.xml"/>
      </mappers>
  </configuration>
  ```

- ##### 建立数据库

  ![数据库表](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\数据库表.PNG)

- ##### 编写表所对应的实体类

  ```java
  public class Student {
      private int id;
      private String sname;
      private int   sage;
      private String ssex;
      public Student(){
  
      }
  
      public Student(String sname, int sage, String ssex) {
          this.sname = sname;
          this.sage = sage;
          this.ssex = ssex;
      }
  
      public Student(int id, String sname, int sage, String ssex) {
          this.id = id;
          this.sname = sname;
          this.sage = sage;
          this.ssex = ssex;
      }
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getSname() {
          return sname;
      }
  
      public void setSname(String sname) {
          this.sname = sname;
      }
  
      public int getSage() {
          return sage;
      }
  
      public void setSage(int sage) {
          this.sage = sage;
      }
  
      public String getSsex() {
          return ssex;
      }
  
      public void setSsex(String ssex) {
          this.ssex = ssex;
      }
  
      @Override
      public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          Student student = (Student) o;
          return id == student.id &&
                  sage == student.sage &&
                  Objects.equals(sname, student.sname) &&
                  Objects.equals(ssex, student.ssex);
      }
  
      @Override
      public int hashCode() {
          return Objects.hash(id, sname, sage, ssex);
      }
  
      @Override
      public String toString() {
          return "Student{" +
                  "id=" + id +
                  ", sname='" + sname + '\'' +
                  ", sage=" + sage +
                  ", ssex='" + ssex + '\'' +
                  '}';
      }
  }
  ```

- ##### dao接口

  - 新建多个包的接口步骤

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\04.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\05.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\06.png)

    ------

    ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\07.png)

  - **dao层中的方法**

    ```java
    import java.io.IOException;
    
    public interface StudentDao {
    //添加一条数据
        public void addStu(Student student) throws IOException;
    // 查询所有学生
        public List<Student> findStu();    
    }
    ```

  - ##### **建立映射文件（StudentMapper.xml）**

    映射文件一般和接口放在一起

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
            <!DOCTYPE mapper
            PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <!--接口-->
    <mapper namespace="org.lanqiao.dao.IStudentDao">
        <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values (#{sname},#{sage},#{ssex})
        </insert>
        <!--sql语句-->
        <!--方法  结果集：返回类型-->
        <select id="findAllStudent" resultType="org.lanqiao.pojo.Student">
            select * from stu
        </select>
    </mapper>
    ```

  - 注册映射文件(StudentMapper.xml)

    ```xml
    <!--注册映射文件-->
        <mappers>
            <mapper resource="org/lanqiao/dao/StudentMapper.xml"/>
        </mappers>
    ```

  - **dao层的具体实现类(Impl)**

    - 新建包及类的步骤

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\08.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\09.png)

    - 代码

      1.编写中所遇到的问题

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\10.png)

      ------

      ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\11.png)

      2.代码

      ```java
      import org.apache.ibatis.session.SqlSessionFactory;
      import org.apache.ibatis.session.SqlSessionFactoryBuilder;
      import org.lanqiao.dao.IStudent;
      import org.lanqiao.pojo.Student;
      
      import java.io.IOException;
      import java.io.InputStream;
      
      public class IStudentDaoImpl implements StudentDao {
          @Override
          public void addStu(Student student) throws IOException {
                  //0 读取配置文件
                  InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
                  // 1 建立SqlSessionFactory 对象
                  SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
                  //2 获取SqlSession对象
                  SqlSession sqlSession =  sqlSessionFactory.openSession();
                  //3 执行sql
                  sqlSession.insert("addStudent",student);
      			//  MySql不自动提交事务，此处为手动提交事务
                  sqlSession.commit();
                  //4 关闭sqlsession
                  if(sqlSession!=null){
                      sqlSession.close();
                  }
              }
          
           @Override
          public List<Student> findStu() {
              List<Student> allStudent = null;
              //0 读取配置文件
              InputStream is = null;
              try {
                  is = Resources.getResourceAsStream("mybatis-config.xml");
              } catch (IOException e) {
                  e.printStackTrace();
              }
              // 1 建立SqlSessionFactory 对象
              SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
              //2 获取SqlSession对象
              SqlSession sqlSession =  sqlSessionFactory.openSession();
              //3 执行sql
              allStudent = sqlSession.selectList("findAllStudent");
              //返回一个结果集，无需提交
              //4 关闭sqlsession
              if(sqlSession!=null){
                  sqlSession.close();
              }
              return allStudent;
          }
          }
      ```

    - 当当

  - 当当

- ##### 测试

  ```java
  public class studentTest {
      @Test
      public void addStuTest() throws IOException {
          //创建接口对象
          IStudent iStudent = new IStudentDaoImpl();
          //创建实体类对象
          Student student = new Student("张三",22,"男");
          iStudent.addStu(student);
      }
      
       @Test
      public void findAllStudent(){
          StudentDao studentDao = new IStudentDaoImpl();
          List<Student> allStudentList = studentDao.findStu();
          for (Student student:allStudentList){
              System.out.println(student);
          }
      }
  }
  ```

- ##### 解决maven项目中找不到mybatis的映射文件的问题

  ```xml
   <build>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/dao/*Mapper.xml</include>
                  </includes>
              </resource>
          </resources>
      </build>
  ```

- 解决插入数据中文乱码的问题

  ```xml
  <property name="url" value="jdbc:mysql://localhost:3306/world?useUnicode=true&amp;characterEncoding=utf8&amp;useSSL=false&amp;serverTimezone=UTC"/>
  ```

- 将数据库链接属性单独提出一个properties属性文件

  ```properties
  driver=com.mysql.jdbc.Driver
  url=jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
  username=root
  password=root
  ```

- 如何让mybatis读取到properties属性文件

  ```properties
  <!--读取数据库链接的属性文件-->
  <properties resource="jdbc.properties">    </properties>
  ```

- 最终的配置文件代码(mybatis-config.xml)

  ```properties
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!--读取数据库链接的属性文件-->
      <properties resource="jdbc.properties"></properties>
      <environments default="development">
          <environment id="development">
              <!--使用JDBC事务，MySql不自动提交事务-->
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${username}"/>
                  <property name="password" value="${password}"/>
              </dataSource>
          </environment>
      </environments>
      <!--注册映射文件-->
      <mappers>
          <mapper resource="org/lanqiao/dao/StudentMapper.xml"/>
      </mappers>
  </configuration>
  ```

### 4、Mybatis的优势

优点：
MyBatis 是一个支持普通 SQL查询，存储过程和高级映射的优秀持久层框架。
MyBatis 消除了几乎所有的JDBC代码和手工设置参数以及结果集的检索。
MyBatis 使用简单的 XML或注解用于配置和原始映射，将接口和 Java 的POJOs（Plain Old Java Objects，普通的 Java对象）映射成数据库中的记录。

### 5、提取mybatis的工具类

- 在org.lanqiao.bao下增添包

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\22.png)

  ------

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\23.png)

- utils包下的mybatisUtils类

  ```java
  public class mybatisUtils {
      //工具类中存放静态的...
      //方法：获取SqlSession对象   参数：配置文件路径
      public static SqlSession getSqlSession(String mybatisConfigPath){
          //0 读取配置文件
          InputStream is = null;
          try {
              is = Resources.getResourceAsStream(mybatisConfigPath);
          } catch (IOException e) {
              e.printStackTrace();
          }
          // 1 建立SqlSessionFactory 对象
          SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
          //2 获取SqlSession对象
          SqlSession sqlSession =  sqlSessionFactory.openSession();
          //3 返回sqlSession对象
          return sqlSession;
      }
  
      //方法：释放资源  参数：SqlSession对象
      public static void realeaseSource(SqlSession sqlSession){
          //4 关闭sqlsession
          if(sqlSession!=null){
              sqlSession.close();
          }
      }
  }
  
  ```

- dao层实现类：IStudentDaoImpl

  ```java
  public class IStudentDaoImpl implements StudentDao {
  
      @Override
      public void addStu(Student student) throws IOException {
          //根据工具类：Utils包下的mybatisUtils类的方法，获取sqlSession对象
          SqlSession sqlSession= mybatisUtils.getSqlSession("mybatis-config.xml");
          //执行sql
          sqlSession.insert("addStudent",student);
          // MySql不自动提交事务，此处为手动提交事务
          sqlSession.commit();
          //关闭sqlsession
          mybatisUtils.realeaseSource(sqlSession);
      }
  
      @Override
      public List<Student> findStu() {
           //根据工具类：Utils包下的mybatisUtils类的方法，获取sqlSession对象
           SqlSession sqlSession= mybatisUtils.getSqlSession("mybatis-config.xml");
          //执行sql
          List<Student> allStudent = sqlSession.selectList("findAllStudent");
          //返回一个结果集，无需提交
          //关闭sqlsession
          mybatisUtils.realeaseSource(sqlSession);
          return allStudent;
      }
  }
  
  ```

- 当当

###6、主配置文件详解

- ##### idea安装mybatis插件

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\24.png)

- 在配置文件mybatis-config.xml中添加日志

  ```properties
  <!--绑定日志文件-->
      <settings>
          <!--配置mybatis的日志，使用LoG4J日志框架-->
          <setting name="logImpl" value="LOG4J"/>
      </settings>
  ```

- 引入所使用的日志实现框架的依赖 log4j

  ```properties
   <!--添加log4j日志框架-->
          <!-- https://mvnrepository.com/artifact/log4j/log4j -->
          <dependency>
              <groupId>log4j</groupId>
              <artifactId>log4j</artifactId>
              <version>1.2.17</version>
          </dependency>
  ```

- ##### 日志的级别

  为了方便对于日制信息的输出显示，对日志内容进行了分级管理。日志级别又高到低，共分为6个级别：fatal(致命的)、error、warn、info、debug、trace（堆栈）。

  Log4j建议只使用四个级别，优 先级从高到低分别是ERROR、WARN、INFO、DEBUG。

  ##### 日志的输出

  Log4j的日志输出控制文件，主要有三部分组成：  日志信息的输出位置：控制日志信息将要输出的位置，是控制台还是文件等。    日志信息的输出格式：控制日志信息的显示格式，即怎样的字符串形式显示    日志信息的输出级别：控制日志信息的显示内容，即显示那些级别的日志信息。   有了日志输出控制文件，代码中只要设置好日志信息内容及其级别即可，通过控制文件便可控制这些日志信息的输出了。

  ##### 代码中实现日志记录

  ​         在要输出日志的类中创建日志对象Logger，并通过Logger的方法在代码中加入日志输出语句。在Java代码中记性日志输出，需要用到Logger类的静态方法getLogger();

  ##### 日志信息格式中几个符号所代表的含义：

  ![](C:\Users\听音乐的酒\Desktop\笔记\学习笔记\imgs\25.png)

- 添加日志框架的配置文件(所有的配置文件都放在：resource下)

  ```java
  ### 设置###
  log4j.rootLogger = debug,stdout,D,E
  ### 输出信息到控制抬 ###
  log4j.appender.stdout = org.apache.log4j.ConsoleAppender
  log4j.appender.stdout.Target = System.out
  log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
  log4j.appender.stdout.layout.ConversionPattern = [%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n
  
  ### 输出DEBUG 级别以上的日志到=E://logs/error.log ###
  log4j.appender.D = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.D.File = E://logs/log.log
  log4j.appender.D.Append = true
  log4j.appender.D.Threshold = DEBUG 
  log4j.appender.D.layout = org.apache.log4j.PatternLayout
  log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  
  ### 输出ERROR 级别以上的日志到=E://logs/error.log ###
  log4j.appender.E = org.apache.log4j.DailyRollingFileAppender
  log4j.appender.E.File =E://logs/error.log 
  log4j.appender.E.Append = true
  log4j.appender.E.Threshold = ERROR 
  log4j.appender.E.layout = org.apache.log4j.PatternLayout
  log4j.appender.E.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n
  
  log4j.rootLogger = trance,Console
  #Console
  log4j.appender.Console = org.apache.log4j.ConsoleAppender
  log4j.appender.Console.layout = org.apache.log4j.PatternLayout
  log4j.appender.stdout.layout.ConversionPattern = [%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n
  log4j.logger.java.sql.ResultSet = INFO
  log4j.logger.org.apache = INFO
  log4j.logger.java.sql.Connection = DEBUG
  log4j.logger.java.sql.Statement = DEBUG
  log4j.logger.java.sql.PreparedStatement = DEBUG
  
  ```

- 在dao层中添加日志信息

  ```
  Logger logger = Logger.getLogger(IStudentDaoImpl.class);
          logger.debug("这是dao的调试日志");
          logger.info("这是info级别的信息");
  ```

- '#'和符的区别

  - 在sql语句中  如果使用的使用的时# 

    ```properties
    <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values (#{sname},#{sage},#{ssex})
        </insert>
    ```

  - 在mybatis进行处理的时候 ，会将#{}的内容转换为？ 

    ```properties
    <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values (?,?,?)
        </insert>
    ```

  - 如果使用的时$符

    ```properties
    <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values ('${sname}',${sage},'${ssex}')
        </insert>
    ```

  - 转化后的sql语句

    ```properties
    <!--sql语句-->
        <!--方法  参数类型-->
        <insert id="addStudent" parameterType="org.lanqiao.pojo.Student">
            insert  into stu(sname,sage,ssex) values ('旺财',22,'男')
        </insert>
    ```

    直接将#{}的内容进行了替换

  - dd

- 当当

- 当当

  ​    






