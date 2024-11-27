package com.userimran.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.userimran.dto.OrderDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static ByteArrayInputStream generatePdf(List<OrderDto> orderDto) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             Document document = new Document()) {
            PdfWriter.getInstance(document, out);
            document.open();

            // Adding Table Header
            Table table = new Table(8);
            table.setWidth(100);
            table.setPadding(3);

            Font headFont = new Font(Font.HELVETICA, 12, Font.BOLD);

            table.addCell(new Phrase("Order ID", headFont));
            table.addCell(new Phrase("Order Date", headFont));
            table.addCell(new Phrase("Payment Type", headFont));
            table.addCell(new Phrase("Price", headFont));
            table.addCell(new Phrase("Product ID", headFont));
            table.addCell(new Phrase("Quantity", headFont));
            table.addCell(new Phrase("Status", headFont));
            table.addCell(new Phrase("User ID", headFont));

            // Adding rows
            for (OrderDto order : orderDto) {
                table.addCell(String.valueOf(order.getOrderId()));
                table.addCell(String.valueOf(order.getOrderDate()));
                table.addCell(String.valueOf(order.getPaymentType()));
                table.addCell(String.valueOf(order.getPrice()));
                table.addCell(String.valueOf(order.getProductId()));
                table.addCell(String.valueOf(order.getQuantity()));
                table.addCell(order.getOrderStatus().name());
                table.addCell(String.valueOf(order.getUserId()));
            }
            document.add(table);
            document.close();
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
