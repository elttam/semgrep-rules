/**
 * JAX-RS wrapper for Hibernate CDI bean validation interceptor.
 * Since Jersey already executes validation on JAX-RS resources,
 * Jersey registers this wrapper into CDI container so that JAX-RS
 * components do not get validated twice.
 *
 * @author Jakub Podlesak (jakub.podlesak at oracle.com)
 */
// ruleid: rest-Priority
@MethodValidated
@Interceptor
@Priority(Interceptor.Priority.PLATFORM_AFTER + 800)
public class CdiInterceptorWrapper {

    private final ValidationInterceptor interceptor;

    @Inject
    public CdiInterceptorWrapper(BeanManager beanManager) {
        // get the original interceptor from the bean manager directly
        // to avoid CDI bootstrap issues caused by wrong extension ordering
        final Set<Bean<?>> interceptorBeans = beanManager.getBeans(ValidationInterceptor.class);
        final Bean<?> interceptorBean = beanManager.resolve(interceptorBeans);
        this.interceptor = (ValidationInterceptor) beanManager.getReference(
                interceptorBean, ValidationInterceptor.class, beanManager.createCreationalContext(interceptorBean));
    }

    @Inject
    private CdiInterceptorWrapperExtension extension;

    @AroundInvoke
    public Object validateMethodInvocation(InvocationContext ctx) throws Exception {
        final boolean isJaxRsMethod = extension.jaxRsResourceCache.apply(ctx.getMethod().getDeclaringClass());
        return isJaxRsMethod ? ctx.proceed() : interceptor.validateMethodInvocation(ctx);
    }

    @AroundConstruct
    public void validateConstructorInvocation(InvocationContext ctx) throws Exception {
        final boolean isJaxRsConstructor = extension.jaxRsResourceCache.apply(ctx.getConstructor().getDeclaringClass());
        if (!isJaxRsConstructor) {
            interceptor.validateConstructorInvocation(ctx);
        }
    }
}
