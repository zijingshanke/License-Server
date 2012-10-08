package com.chinarewards.license.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener; 

public class HSQLDBServletContextListener implements ServletContextListener {
 public void contextInitialized(ServletContextEvent evt) {
        try {
            ServletContext context = evt.getServletContext();
              String dbPath =  context.getRealPath("/") + "/WEB-INF/db/";
              String dbName = "license";
              int port = 9001;
              startServer(dbPath,dbName,port);
        } catch (Exception e) {
            System.err.println("起动出错===="+e);
        }
    }
/**   
　* 启动  Hsqldb  服务的方法。   
　* @param dbPath　数据库路径   
　* @param dbName　数据库名称   
　* @param port　所使用的端口号   
　*/   
private void startServer(String dbPath, String dbName, int port) {    
  org.hsqldb.Server server = new org.hsqldb.Server();//它可是hsqldb.jar里面的类啊。    
 
  server.setDatabaseName(0, dbName);    
  server.setDatabasePath(0, dbPath +"/"+ dbName);    
 
 if (port != -1){    
       server.setPort(port);    
   }    
    server.setSilent(true);    
    server.start();    
    System.out.println("hsqldb 服务启动了...");    
   
}  


/**   
* Listener销毁方法，在Web应用终止的时候执行"shutdown"命令关闭数据库.   
*/  
    public void contextDestroyed(ServletContextEvent evt) {
        Connection conn = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001","sa", "");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("SHUTDOWN;");
        } catch (Exception e) {
            //
        }
    }
} 

