import spark.kotlin.*
import spark.Spark.*

fun main(args: Array<String>) {
    val http: Http = ignite()

    // ruleid: spark-route-handler
    http.get("/hello") {
        "Hello Spark Kotlin!"
    }

    // ruleid: spark-route-handler
    http.post("/goodbye") {
        "Goodbye Spark Kotlin!"
    }

    // ruleid: spark-route-handler
    get("/static", { req, res ->
        "test"
        res.status(200)
    })

    path("/auth") {

        // ruleid: spark-route-handler
        post("/login", { req, res ->
            user.findByUsername(req.params("username"))
        })
    }
}
