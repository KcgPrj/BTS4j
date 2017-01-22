package net.orekyuu.bts4j;

/**
 * バグレポートを表すクラスです
 *
 * @see {@link BugReportBuilder}
 */
public final class BugReport {

    private final String title;
    private final String description;
    private final String version;
    private final String stacktrace;
    private final String log;
    private final String runtimeInfo;

    protected BugReport(String title, String description, String version, String stacktrace, String log, String runtimeInfo) {
        this.title = title;
        this.description = description;
        this.version = version;
        this.stacktrace = stacktrace;
        this.log = log;
        this.runtimeInfo = runtimeInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public String getLog() {
        return log;
    }

    public String getRuntimeInfo() {
        return runtimeInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BugReport{");
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", stacktrace='").append(stacktrace).append('\'');
        sb.append(", log='").append(log).append('\'');
        sb.append(", runtimeInfo='").append(runtimeInfo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
