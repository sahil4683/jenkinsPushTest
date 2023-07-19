package agenttest;


//import com.sp.agent_engine.util.EngineConstant;
//import com.sp.agent_engine.vo.SystemInformationVo;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thread
 */
public class EDBConnectionManager extends AnnotatedEntityFactory {

    private static final Logger logger = LoggerFactory.getLogger(EDBConnectionManager.class);

    private static SessionFactory sessionFactory;

    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_NAME = "hbmtest";
    public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/"+DB_NAME+"?useUnicode=yes&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
    public static final String HBM_DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    public static final String HBM_DDL_AUTO = "update";
    public static final String HBM_SHOW_SQL = "true";
    public static final String HBM_FORMAT_SQL = "true";

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, DB_CONNECTION);
                settings.put(Environment.USER, DB_USER);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, HBM_DIALECT);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, HBM_DDL_AUTO);
                settings.put(Environment.SHOW_SQL, HBM_SHOW_SQL);
                settings.put(Environment.FORMAT_SQL, HBM_FORMAT_SQL);
                settings.put("org.hibernate.SQL_SLOW", "info");
                configuration.setProperties(settings);
                
                /**
                 * Added For Set Entity Class on Configuration Of Hibernate
                 */
                setAnnotatedEntityClasses(configuration);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.printStackTrace();
                logger.error("exception at database connection : {} ", e.toString());
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
    
}
