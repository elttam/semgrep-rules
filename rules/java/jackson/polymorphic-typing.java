import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;

import java.io.Serializable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/message")
    public Foo create(@RequestBody Foo data) {
        final ObjectMapper om = new ObjectMapper();
        // ruleid: polymorphic-typing
        om.activateDefaultTyping(null);
        // ruleid: polymorphic-typing
        om.enableDefaultTyping();

        TypeResolverBuilder tr = new TypeResolverBuilder<TypeResolverBuilder<T>>() {
            // ...
        };

        TypeResolverBuilder trb;

        return data;
    }

}

// ruleid: polymorphic-typing
@JsonTypeInfo(use=Id.CLASS)
public class Foo extends Bar implements Serializable {

    // ruleid: polymorphic-typing
    @JsonTypeInfo(use = Id.CLASS)
    private Object blaa;

    // ruleid: polymorphic-typing
    @JsonTypeInfo(use = Id.CLASS)
    protected Serializable y;

    // ruleid: polymorphic-typing
    @JsonTypeInfo(use = Id.CLASS)
    public Comparable z;
}

public class Bar implements Comparable {
    // ...
}
