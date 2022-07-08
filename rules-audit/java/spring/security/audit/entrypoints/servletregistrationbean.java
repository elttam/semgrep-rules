public class Klass {

    public ServletRegistrationBean exampleServletBean() {
        // ruleid: rest-servletregistrationbean
        ServletRegistrationBean bean = new ServletRegistrationBean(
          new CustomServlet(), "/exampleServlet/*");
        bean.setLoadOnStartup(1);
        return bean;
    }

}
