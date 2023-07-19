/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agenttest;

import com.sp.agent_engine.vo.User;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author sahil
 */
public class AnnotatedEntityFactory {
    
    protected static void setAnnotatedEntityClasses(Configuration configuration) {
        configuration.addAnnotatedClass(User.class);
    }
    
}
