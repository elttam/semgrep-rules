import { PrismaClient } from "@prisma/client";
const prisma = new PrismaClient();

// Express-ish
export async function bad_express(req: any) {
  // ruleid: prisma-ormleak-taint-where
  const out = await prisma.user.findMany({ where: req.query });
  if (out.length) return { ok: true };
  return { ok: false };
}

export async function bad_express_auth_bypass(req: any) {
    // ruleid: prisma-orm-operator-injection
    let user = await prisma.user.findFirstOrThrow({
        where: {
            resetToken: req.body.resetToken as string
        }
    })
    const resetToken = req.body.resetToken as string
    // ruleid: prisma-orm-operator-injection
    user = await prisma.user.findFirstOrThrow({where: {resetToken}})
}

// Next / Fetch handler style
export async function bad_fetch(request: Request) {
  const body = await request.json();
  // ruleid: prisma-ormleak-taint-projection
  const out = await prisma.user.findMany({ select: body.select });
  return Response.json(out);
}

// Lambda/APIGW style
export async function bad_lambda(event: any) {
  const args = JSON.parse(event.body);
  // ruleid: prisma-ormleak-taint-where
  const out = await prisma.post.findMany({ where: args.where });
  return { statusCode: 200, body: JSON.stringify(out) };
}

export async function bad_order(req: any) {
  // ruleid: prisma-ormleak-taint-orderby
  const out = await prisma.post.findMany({ orderBy: req.query.orderBy });
  return out;
}
