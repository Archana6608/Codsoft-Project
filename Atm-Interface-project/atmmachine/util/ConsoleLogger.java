package atmmachine.util;

public class ConsoleLogger {
    private final String[] telemetryLogs = {"[EMPTY]", "[EMPTY]", "[EMPTY]", "[EMPTY]", "[EMPTY]"};
    private int logSequenceCounter = 0;
    private static final String RESET = "\u001B[0m";

    public void writeSystemLog(String operationalTag, String descriptor, String systemOutcome) {
        String logStatusColor = systemOutcome.equals("REJECTED") || systemOutcome.equals("DENIED") ? "\u001B[31m" : "\u001B[32m";
        String pipelineString = String.format("│ TRACE: [%-15s] | %-20s | EVENT: %s%-9s%s │", operationalTag, descriptor, logStatusColor, systemOutcome, RESET);
        telemetryLogs[logSequenceCounter % 5] = pipelineString;
        logSequenceCounter++;
    }

    public String fetchTelemetryRecord(int index) {
        return telemetryLogs[index];
    }
}