import javax.ws.rs.Path;

@Path("widgets")
public class WidgetsResource {
    @GET
    String getList() {
        return "blah";
    }

    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }

    // ruleid: jax-rs.path-expression
    @GET @Path(something)
    String getSomething(@PathParam("id") String id) {
        return "something";
    }

    // todoruleid: jax-rs.path-expression
    @GET @Path(value = "{id}")
    String getValueString(@PathParam("id") String id) {
        return "something";
    }
    //
    // todoruleid: jax-rs.path-expression
    @GET @Path(value = something)
    String getValueExpr(@PathParam("id") String id) {
        return "something";
    }
}
