import javax.ws.rs.Path;
import javax.ws.rs.Consumes;

// ruleid: consumes-class-star
@Consumes({"*/*"})
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

    @GET @Path(something)
    String getSomething(@PathParam("id") String id) {
        return "something";
    }
}
