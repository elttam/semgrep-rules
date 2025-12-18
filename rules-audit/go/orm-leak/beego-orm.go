package tests

import (
	"github.com/beego/beego/v2/client/orm"
	web "github.com/beego/beego/v2/server/web"
)

type User struct {
	Id   int
	Name string
}

type MainController struct {
	web.Controller
}

func (c *MainController) BadDynamicExpr() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	// attacker controls expression string (field path/operator)
	expr := c.GetString("expr")

	// ruleid: beego-ormleak-taint-dynamic-expr
	_, _ = qs.Filter(expr, "x").All(&[]User{})

	// ruleid: beego-ormleak-oracle-count
	_, _ = qs.Filter(expr, "x").Count()
}

func (c *MainController) BadFilterRaw() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	raw := c.GetString("raw")

	// ruleid: beego-ormleak-filterraw
	qs.FilterRaw(raw)
}

func (c *MainController) BadOrderBy() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	order := c.GetString("order")

	// ruleid: beego-ormleak-taint-dynamic-expr
	qs = qs.OrderBy(order)

	var out []User
	_, _ = qs.All(&out)

	if len(out) > 0 {
		c.Ctx.WriteString("hit")
	}
}

func (c *MainController) BadConditionExpr() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	expr := c.GetString("cond")

	cond := orm.NewCondition()

	// ruleid: beego-ormleak-taint-dynamic-expr
	cond = cond.And(expr, "x")

	qs = qs.SetCond(cond)

	_, _ = qs.Count()
}

func (c *MainController) GoodFixedExpr() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	name := c.GetString("name")

	// ok: beego-ormleak-taint-dynamic-expr
	qs = qs.Filter("name", name)

	_, _ = qs.Count()
}

func (c *MainController) GoodWhitelistExpr() {
	o := orm.NewOrm()
	qs := o.QueryTable("user")

	// user chooses a "mode", but expr is mapped/whitelisted
	mode := c.GetString("mode")
	value := c.GetString("v")

	expr := "name"
	if mode == "contains" {
		expr = "name__contains"
	}

	// ok: beego-ormleak-dynamic-expr-review (expr is controlled by whitelist)
	qs = qs.Filter(expr, value)

	_, _ = qs.Count()
}
