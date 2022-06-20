import javax.ws.rs.Path;
import jakarta.ws.rs.Path;

@Path(WIDGETS)
public class WidgetsString {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}

@Path("widgets")
public class WidgetsString {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}

@Path(value = "tricky")
public class WidgetsExpression {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}

@Path(value = QWERTY)
public class WidgetsExpression {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}

@javax.ws.rs.Path("widgets")
public class WidgetsStringFq {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}

@javax.ws.rs.Path(value = "tricky")
public class WidgetsExpressiongFq {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: path-class-method
    @GET @Path(FOOBAR)
    String getWidget1(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + BAR)
    String getWidget2(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path(FOO + "{id}")
    String getWidget3(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: path-class-method
    @GET @Path("{id}")
    String getWidget4(@PathParam("id") String id) {
        return "arghhh!";
    }
}
