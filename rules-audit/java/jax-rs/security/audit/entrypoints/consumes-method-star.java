import javax.ws.rs.Path;
import javax.ws.rs.Consumes;

@Path("widgets")
public class WidgetsResource {
    @GET
    String getList() {
        return "blah";
    }

    // ruleid: consumes-method-star
    @Consumes({"*/*"})
    @GET @Path("{id}")
    String getWidget(@PathParam("id") String id) {
        return "arghhh!";
    }

    @GET @Path(something)
    String getSomething(@PathParam("id") String id) {
        return "something";
    }
}
