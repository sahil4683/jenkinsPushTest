package agenttest;

import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sp.agent_engine.vo.User;
import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

/**
 *
 * @author sahil
 */
public class AgentTest {

    private static final Logger logger = LoggerFactory.getLogger(AgentTest.class);
//    private static Transaction transaction = null;

    public static void main(String[] args) {
//       User user = new User();
//       user.setName("sahil");
//       user.setDept("IT");
//       user.setInsertDate(new Date());
//       user.setUpdateDate(new Date());
//        saveSystemInformation(user);
        getSystemInformationByComputerName("sahil");
    }

public static User saveSystemInformation(User user) {
    Transaction transaction = null;
    try (Session session = EDBConnectionManager.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        final Serializable save = session.save(user);
        System.out.println(save);
        transaction.commit();
        if (user.getId() != 0) {
            return user;
        }
    } catch (Exception e) {
        e.printStackTrace();
        if (transaction != null) {
            transaction.rollback();
        }
        logger.error("Error occurred while saving user: {}", e.toString());
    }
    return null;
}


    public static User getSystemInformationByComputerName(String name) {
        
        try (Session session = EDBConnectionManager.getSessionFactory().openSession()) {

            User systemInformationVo = session
                    .createQuery("from User where name=:name", User.class)
                    .setParameter("name", name)
                    .uniqueResult();
            if (systemInformationVo != null) {
                System.out.println("User: = "+systemInformationVo);
//                 System.out.println("User: = "+systemInformationVo);
                return systemInformationVo;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

}
