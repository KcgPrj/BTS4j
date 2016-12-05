package net.orekyuu.bts4j;

public final class BugReportServiceBuilder {

    private final String productToken;
    private final String url;

    public BugReportServiceBuilder(String url, String productToken) {
        this.url = url;
        this.productToken = productToken;
    }

    public BugReportService build() {
        return new SimpleBugReportService(url, productToken);
    }
}
