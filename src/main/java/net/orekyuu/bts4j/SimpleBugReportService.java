package net.orekyuu.bts4j;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

final class SimpleBugReportService implements BugReportService {

    private final String url;
    private final String productToken;
    private static final String REPORT_API_PATH = "/report/create";

    SimpleBugReportService(String url, String productToken) {
        if (url == null) {
            throw new NullPointerException("url is null.");
        }
        if (productToken == null) {
            throw new NullPointerException("productToken is null");
        }

        // /で終わってなかったら末尾に追加する
        if (!url.endsWith("/")) {
            url += "/";
        }
        this.url = url;
        this.productToken = productToken;
    }

    @Override
    public void sendReport(BugReport report) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(this.url + REPORT_API_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            String body = BugReportJsonFormatter.format(report, productToken);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();

        } catch (MalformedURLException e) {
            throw new BugReportException(e);
        } catch (IOException e) {
            throw new BugReportException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
