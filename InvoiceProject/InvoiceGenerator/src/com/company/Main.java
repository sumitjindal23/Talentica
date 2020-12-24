package com.company;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        FileInputStream stream = null;
        String filePath = args[0];
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] items = {"chips", "crocin", "chocolates", "teddy bear", "paracetamol", "vegetable", "fruits", "toy"};
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String strLine;
        StringBuilder invoiceRecords = new StringBuilder();
        String invoiceHeader = CSVUtility
                .writeCsvHeader(new String[]{"NAME,QTY,UNIT_COST,VAT,ADDITIONAL TAX,COST"});
        Double total = 0.0;
        while ((strLine = reader.readLine()) != null) {

            String qty = strLine.substring(0, strLine.indexOf(" "));
            String productName = strLine.substring(strLine.indexOf(" ") + 1, strLine.indexOf("@") - 1);
            boolean isVat = stringContainsItemFromList(productName.toLowerCase(), items);
            boolean isImport = productName.toLowerCase().contains("imported");
            String price = strLine.substring(strLine.indexOf("@") + 2);
            String vat = "0%";
            String addTax = "0%";
            Double grossPrice = Double.parseDouble(price) * Integer.parseInt(qty);
            Double cost =grossPrice;
            if (isVat && isImport) {
                cost = getCostAfterTax(cost, 12.5);
                cost = getCostAfterTax(cost, 2.4);
                vat = "12.5%";
                addTax = "2.4%";
            } else if (isVat && !isImport) {
                cost = getCostAfterTax(cost, 12.5);
                vat = "12.5%";

            } else if (!isVat && isImport) {
                cost = getCostAfterTax(cost, 2.4);
                addTax = "2.4%";
            }
            total = total + cost;

            invoiceRecords.append(CSVUtility.writeCsvRecords(
                    new String[]{CSVUtility.enclosewithQuote(productName), qty, price, vat, addTax, cost.toString()}));
        }

        invoiceRecords.append("Total: " + Math.round(total * 100.0) / 100.0);
        System.out.println(invoiceHeader + invoiceRecords);

    }

    private static double getCostAfterTax(Double cost, double tax) {
        return Math.round((cost + cost * (tax) / 100) * 100.0) / 100.0;
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return !(Arrays.stream(items).anyMatch(inputStr::contains));
    }
}
