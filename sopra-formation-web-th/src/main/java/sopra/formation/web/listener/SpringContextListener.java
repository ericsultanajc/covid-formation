package sopra.formation.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class SpringContextListener implements ServletContextListener {

    public SpringContextListener() {
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
    	
    	sce.getServletContext().setAttribute("spring", context);
   }
    
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ClassPathXmlApplicationContext context = (ClassPathXmlApplicationContext) sce.getServletContext().getAttribute("spring");
    	
    	context.close();
    }

}
