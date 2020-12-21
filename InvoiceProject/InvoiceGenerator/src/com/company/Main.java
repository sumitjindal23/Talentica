package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        FileInputStream stream = null;
        String filePath  = args[0];
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String strLine;
        StringBuilder invoiceRecords = new StringBuilder();
        String invoiceHeader = CSVUtility
                .writeCsvHeader(new String[] { "NAME,QTY,UNIT_COST,VAT,ADDITIONAL TAX,COST" });
        Double total = 0.0;
        while((strLine = reader.readLine())!=null){

            String qty = strLine.substring(0,strLine.indexOf(" "));
            String productName = strLine.substring(strLine.indexOf(" ")+1,strLine.indexOf("@")-1);
            String price = strLine.substring(strLine.indexOf("@")+2);
            Double grossPrice = Double.parseDouble(price)*Integer.parseInt(qty);
            Double cost = Math.round((grossPrice + grossPrice*(12.5+2.4)/100) * 100.0) / 100.0;
            total = total + cost;
            
            invoiceRecords.append(CSVUtility.writeCsvRecords(
                    new String[] { CSVUtility.enclosewithQuote(productName), qty, price ,"12.5%","2.4%",cost.toString() }));
        }

        invoiceRecords.append("Total: "+Math.round(total * 100.0) / 100.0);
        System.out.println(invoiceHeader+invoiceRecords);

    }
}
