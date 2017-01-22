import net.orekyuu.bts4j.BugReport;
import net.orekyuu.bts4j.BugReportBuilder;
import net.orekyuu.bts4j.BugReportService;
import net.orekyuu.bts4j.BugReportServiceBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                String token = "d8684659-6bb1-46c0-93f8-cf55fa2c40d0";
                BugReportService service = new BugReportServiceBuilder("http://localhost:18080", token).build();

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                pw.flush();
                String stacktrace = sw.toString();

                try {
                    BugReport report = new BugReportBuilder()
                            .title(e.getClass().getName())
                            .description("")
                            .log("")
                            .assignUserId(1)
                            .stacktrace(stacktrace)
                            .runtimeInfo(System.getProperty("os.name"))
                            .version("1.0.0")
                            .build();
                    service.sendReport(report);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        });

        throw new RuntimeException("aaaa");
    }
}
