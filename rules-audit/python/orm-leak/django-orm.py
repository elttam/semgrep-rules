from django.db.models import Q
from django.http import HttpRequest
from myapp.models import Article
from django.contrib.auth import User


def bad_filter_direct(request: HttpRequest):
    # ruleid: django-orm-dynamic-lookup-kwargs
    qs = User.objects.filter(**request.GET)
    return qs.count()

def bad_filter_dict(request: HttpRequest):
    data = request.GET
    # ruleid: django-orm-dynamic-lookup-kwargs
    qs = User.objects.filter(**data)
    return qs.count()

def bad_search(search_key, search_val):
    # ruleid: django-orm-dynamic-lookup-variable-key
    qs = User.objects.filter(is_superuser=False, **{search_key: search_val})
    return qs.count()

def bad_q_builder(request):
    data = request.GET
    # ruleid: django-orm-dynamic-lookup-variable-key
    q = Q(**{k: v for k, v in data.items()})
    qs = Article.objects.filter(q)
    if qs:
        return True
    return False


def bad_bulk_delete(request):
    # ruleid: django-orm-dynamic-lookup-kwargs
    User.objects.filter(**request.GET).delete()


def bad_order_by(request):
    sort = request.GET.get("sort")
    qs = Article.objects.all()
    # ruleid: django-orm-dynamic-order-or-fields
    qs = qs.order_by(sort)
    return list(qs)

def bad_keyword_filter(search_key, search_val):
    # ruleid: django-orm-dynamic-lookup-variable-key
    return User.objects.filter(**{search_key: search_val})


# DjangoFilter example
from django_filters import FilterSet


# ruleid: django-filter-exposes-all-fields
class UserFilter(FilterSet):
    class Meta:
        model = User
        fields = "__all__"

def safe_literal_kwargs():
    # ok: django-orm-dynamic-lookup-variable-key
    # ok: django-orm-dynamic-lookup-kwargs
    return User.objects.filter(**{"id": 1})


def safe_fixed_fields(request):
    username = request.GET.get("username")
    # ok: django-orm-dynamic-lookup-variable-key
    # ok: django-orm-dynamic-lookup-kwargs
    return User.objects.filter(username=username)


def safe_q_literal():
    # ok: django-orm-dynamic-lookup-variable-key
    # ok: django-orm-dynamic-lookup-kwargs
    q = Q(**{"email__icontains": "test"})
    return User.objects.filter(q).count()


def safe_order_literal():
    # ok: django-orm-dynamic-order-or-fields
    return User.objects.all().order_by("id")


def safe_filter_dict(request: HttpRequest):
    data = {"username__contains": request.GET.get("search")}
    # ruleid: django-orm-dynamic-lookup-kwargs
    qs = User.objects.filter(**data)
    return qs.count()
