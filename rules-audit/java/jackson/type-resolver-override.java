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
        om.activateDefaultTyping(null);
        om.enableDefaultTyping();

        // ruleid: type-resolver-override
        TypeResolverBuilder tr = new TypeResolverBuilder<TypeResolverBuilder<T>>() {
            // ...
        };

        // ruleid: type-resolver-override
        TypeResolverBuilder trb;

        return data;
    }

}

@JsonTypeInfo(use=Id.CLASS)
public class Foo extends Bar implements Serializable {

    @JsonTypeInfo(use = Id.CLASS)
    private Object blaa;

    @JsonTypeInfo(use = Id.CLASS)
    protected Serializable y;

    @JsonTypeInfo(use = Id.CLASS)
    public Comparable z;
}

public class Bar implements Comparable {
    // ...
}
