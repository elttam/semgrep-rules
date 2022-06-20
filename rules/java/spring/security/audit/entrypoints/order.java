// ruleid: rest-Order
@Component
@Order(1)
public class Excellent implements Rating {

    @Override
    public int getRating() {
        return 1;
    }
}

// ruleid: rest-Order
@Component
@Order(2)
public class Good implements Rating {

    @Override
    public int getRating() {
        return 2;
    }
}

// ruleid: rest-Order
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class Average implements Rating {

    @Override
    public int getRating() {
        return 3;
    }
}
