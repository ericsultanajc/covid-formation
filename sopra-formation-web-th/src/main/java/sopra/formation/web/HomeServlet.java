package sopra.formation.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ServletContextTemplateResolver resolver;

	public HomeServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.resolver = new ServletContextTemplateResolver(getServletContext());
		this.resolver.setTemplateMode(TemplateMode.HTML);
		this.resolver.setPrefix("/WEB-INF/views/");
		this.resolver.setSuffix(".html");
		this.resolver.setCharacterEncoding("UTF-8");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("username", "Eric");
		String[] eleves = {};
		
		request.setAttribute("eleves", eleves);

		TemplateEngine templateEngine = new TemplateEngine();
		WebContext webContext = new WebContext(request, response, getServletContext(), request.getLocale());

		response.setCharacterEncoding(resolver.getCharacterEncoding());
		templateEngine.setTemplateResolver(resolver);

		String result = templateEngine.process("home", webContext);

		response.getWriter().println(result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
