import javax.ws.rs.Path;

// ruleid: jax-rs.path-class
@Path("widgets")
public class WidgetsString {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}
// ruleid: jax-rs.path-class
@Path(value = "tricky")
public class WidgetsExpression {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}

// ruleid: jax-rs.path-class
@javax.ws.rs.Path("widgets")
public class WidgetsStringFq {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}
// ruleid: jax-rs.path-class
@javax.ws.rs.Path(value = "tricky")
public class WidgetsExpressiongFq {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}
