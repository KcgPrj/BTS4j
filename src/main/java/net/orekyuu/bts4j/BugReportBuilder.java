package net.orekyuu.bts4j;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class BugReportBuilder {
    private String title;
    private String description;
    private String version;
    private String stacktrace;
    private String log;
    private String runtimeInfo;

    public BugReportBuilder() {

    }

    public BugReportBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BugReportBuilder description(String description) {
        this.description = description;
        return this;
    }

    public BugReportBuilder version(String version) {
        this.version = version;
        return this;
    }

    public BugReportBuilder stacktrace(String stacktrace) {
        this.stacktrace = escape(stacktrace);
        return this;
    }

    public BugReportBuilder stacktraceFromThrowable(Throwable throwable) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.flush();
        stacktrace(writer.toString());
        return this;
    }

    public BugReportBuilder log(String log) {
        this.log = log;
        return this;
    }

    public BugReportBuilder runtimeInfo(String runtimeInfo) {
        this.runtimeInfo = runtimeInfo;
        return this;
    }

    public BugReport build() {
        return new BugReport(title, description, version, stacktrace, log, runtimeInfo);
    }

    private String escape(String str) {
        return str.replace("\n", "\\n").replace("\t", "\\t");
    }
}
