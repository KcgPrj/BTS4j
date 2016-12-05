package net.orekyuu.bts4j;

class BugReportJsonFormatter {

    static String format(BugReport bugReport, String productToken) {
        if (productToken == null) {
            throw new NullPointerException("productToken is null.");
        }

        StringBuilder builder = new StringBuilder("{");
        writeString("productToken", productToken, builder);
        if (bugReport.getAssignUserId() != -1) {
            writeNumber("assignUserId", bugReport.getAssignUserId(), builder);
        }
        writeString("title", bugReport.getTitle() == null ? "" : bugReport.getTitle(), builder);
        writeString("description", bugReport.getDescription() == null ? "" : bugReport.getDescription(), builder);
        writeString("version", bugReport.getVersion() == null ? "" : bugReport.getVersion(), builder);
        writeString("stacktrace", bugReport.getStacktrace() == null ? "" : bugReport.getStacktrace(), builder);
        writeString("log", bugReport.getLog() == null ? "" : bugReport.getLog(), builder);
        writeString("runTimeInfo", bugReport.getRuntimeInfo() == null ? "" : bugReport.getRuntimeInfo(), builder);
        builder.deleteCharAt(builder.length() - 1);// 最後の,を削除する
        builder.append("}");
        return builder.toString();
    }

    private static void writeString(String key, String value, StringBuilder builder) {
        builder.append("\"").append(key).append("\":").append("\"").append(escape(value)).append("\",");
    }

    private static void writeNumber(String key, int value, StringBuilder builder) {
        builder.append("\"").append(key).append("\":").append(value).append(",");
    }

    private static String escape(String str) {
        return str.replaceAll("\n", "\\\n").replaceAll("\r", "").replaceAll("\"", "\\\"").replaceAll("\\\\", "\\\\");
    }
}
