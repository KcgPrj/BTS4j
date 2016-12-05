package net.orekyuu.bts4j;

/**
 * バグレポートを送信するためのサービス
 */
public interface BugReportService {

    /**
     * バグレポートを送信します
     * @param report バグレポート
     */
    void sendReport(BugReport report);
}
