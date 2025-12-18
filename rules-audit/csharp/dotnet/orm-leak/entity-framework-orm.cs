using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

public class UsersController : Controller
{
    private readonly AppDbContext _db;

    public UsersController(AppDbContext db)
    {
        _db = db;
    }

    public IActionResult BadEfProperty()
    {
        var field = Request.Query["field"];

        // ruleid: ef-ormleak-taint-ef-property
        var q = _db.Users.Where(u => EF.Property<string>(u, field) != null);

        if (q.Any())
            return Ok("hit");

        return NotFound();
    }

    public IActionResult BadInclude()
    {
        var path = Request.Query["include"];

        // ruleid: ef-ormleak-taint-dynamic-include
        var q = _db.Users.Include(path);

        return Ok(q.ToList());
    }

    public IActionResult BadOrder()
    {
        var order = Request.Query["sort"];

        // ruleid: ef-ormleak-dynamic-orderby
        var q = _db.Users.OrderBy(order);

        if (q.Any())
            return Ok();

        return BadRequest();
    }

    public IActionResult GoodFixedWhere()
    {
        var email = Request.Query["email"];

        // ok: ef-ormleak-taint-ef-property
        var q = _db.Users.Where(u => u.Email == email);

        return Ok(q.ToList());
    }

    public IActionResult GoodWhitelistInclude()
    {
        var mode = Request.Query["mode"];
        IQueryable<User> q = _db.Users;

        if (mode == "profile")
            q = q.Include("Profile");

        // ok: ef-ormleak-taint-dynamic-include
        return Ok(q.ToList());
    }
}
