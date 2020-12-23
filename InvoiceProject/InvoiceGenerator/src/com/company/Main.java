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
            Double tax = 0.0;
            if (isVat && isImport) {
                tax = 12.5 + 2.4;
                vat = "12.5%";
                addTax = "2.4%";
            } else if (isVat && !isImport) {
                tax = 12.5;
                vat = "12.5%";
            } else if (!isVat && isImport) {
                tax = 2.4;
                addTax = "2.4%";
            }

            Double cost = Math.round((grossPrice + grossPrice * (tax) / 100) * 100.0) / 100.0;
            total = total + cost;

            invoiceRecords.append(CSVUtility.writeCsvRecords(
                    new String[]{CSVUtility.enclosewithQuote(productName), qty, price, vat, addTax, cost.toString()}));
        }

        invoiceRecords.append("Total: " + Math.round(total * 100.0) / 100.0);
        System.out.println(invoiceHeader + invoiceRecords);

    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return !(Arrays.stream(items).anyMatch(inputStr::contains));
    }
}
