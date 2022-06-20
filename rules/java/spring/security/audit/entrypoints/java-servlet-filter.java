import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;

// ruleid: java.servlet.filter
public class MyFilter implements Filter{
    public void init(FilterConfig arg0) throws ServletException {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        out.print("filter is invoked before");
        // sends request to next resource
        chain.doFilter(req, resp);
        out.print("filter is invoked after");
    }

    public void destroy() {}
}
