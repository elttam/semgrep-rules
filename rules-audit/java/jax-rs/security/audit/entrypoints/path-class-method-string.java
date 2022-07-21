import javax.ws.rs.Path;

@Path("widgets")
public class WidgetsString {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-method-string
    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}
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

@javax.ws.rs.Path("widgets")
public class WidgetsStringFq {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-method-string
    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
  }
}
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
