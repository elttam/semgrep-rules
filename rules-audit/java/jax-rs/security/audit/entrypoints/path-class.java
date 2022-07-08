import javax.ws.rs.Path;
import jakarta.ws.rs.Path;

// ruleid: path-class
@Path("foo" + "bar")
public class WidgetsString {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }

    @GET @Path(FOOBAR + "{id}")
    String getFoobar(@PathParam("id") String id) {
        return "blargh!";
    }
}

// ruleid: path-class
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

// ruleid: path-class
@Path(value = "tricky")
public class WidgetsValueString{
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }
}

// ruleid: path-class
@javax.ws.rs.Path(WIDGETS)
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
// ruleid: path-class
@javax.ws.rs.Path(value = WIDGETS)
public class WidgetsValueExpressiong {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }
}

// ruleid: path-class
@Path(WIDGETS)
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

// ruleid: path-class
@Path(value = WIDGETS)
public class WidgetsValueExpressiong {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }
}
