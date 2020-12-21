package com.company;

public class CSVUtility {

    /**
     * Add Headers for CSV
     *
     * @param headers
     * @return
     */
    public static String writeCsvHeader(String[] headers) {
        return fillCsvFile(headers);
    }

    /**
     * Add records in CSV
     *
     * @param records
     * @return
     */
    public static String writeCsvRecords(String[] records) {
        return fillCsvFile(records);
    }

    private static String fillCsvFile(String[] headers) {
        StringBuilder csvHeader = new StringBuilder();
        int totalHeaders = headers.length;
        for (int i = 0; i < totalHeaders; i++) {
            // if not last header, append comma after each header
            if (i != totalHeaders - 1) {
                csvHeader.append(headers[i]);
                csvHeader.append(",");
            }
            // if last header, just append the column
            else {
                csvHeader.append(headers[i]);
            }
        }
        csvHeader.append("\n");
        return csvHeader.toString();
    }

    /**
     * If comma is present in input then enclose it with quotes
     *
     * @param input
     * @return
     */
    public static String enclosewithQuote(String input) {
        if (input != null && input.contains(",")) {
            return "\"" + input + "\"";
        }
        return input;
    }


}
