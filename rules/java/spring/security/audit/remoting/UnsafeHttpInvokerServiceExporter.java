@Configuration
class Server {

    // ruleid: UnsafeHttpInvokerServiceExporter-func
    @Bean(name = "/account")
    HttpInvokerServiceExporter accountService() {
        // ruleid: UnsafeHttpInvokerServiceExporter-var
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(new AccountServiceImpl());
        exporter.setServiceInterface(AccountService.class);
        return exporter;
    }

    // ruleid: UnsafeHttpInvokerServiceExporter-func
    @Bean(name = "/simple-account")
    SimpleHttpInvokerServiceExporter simpleAccountService() {
        // ruleid: UnsafeHttpInvokerServiceExporter-var
        SimpleHttpInvokerServiceExporter exporter = new SimpleHttpInvokerServiceExporter();
        exporter.setService(new AccountServiceImpl());
        exporter.setServiceInterface(AccountService.class);
        return exporter;
    }


}

class AccountServiceImpl implements AccountService {

    @Override
    public String echo(String data) {
        return data;
    }
}

interface AccountService {
    String echo(String data);
}
