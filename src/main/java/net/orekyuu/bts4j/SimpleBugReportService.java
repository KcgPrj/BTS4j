package net.orekyuu.bts4j;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

final class SimpleBugReportService implements BugReportService {

    private final String url;
    private final String productToken;
    private static final String REPORT_API_PATH = "open/api/report";

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
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            String body = BugReportJsonFormatter.format(report, productToken);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();

            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

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
