package com.bookstoreswing.utils;

import java.io.FileWriter;
import java.io.IOException;
import com.bookstoreswing.model.Order;
import com.bookstoreswing.config.AppConfig;

/**
 * Simple file utility to append orders to a text file
 */
public class FileManager {
    public static void appendOrderToFile(Order o){
        try(FileWriter fw = new FileWriter(AppConfig.ORDERS_FILE, true)){
            fw.write("OrderID: " + o.getOrderId() + "\n");
            fw.write("Customer: " + o.getCustomer().getName() + "\n");
            fw.write("Total: " + o.getTotal() + " DA\n");
            fw.write("Items:\n");
            o.getItems().forEach(it -> {
                try {
                    fw.write("- " + it.getBook().getTitle() + " x" + it.getQuantity() + " = " + it.getTotal() + " DA\n");
                } catch (IOException ex) {}
            });
            fw.write("-------------------------\n");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
