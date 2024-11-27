package com.userimran.utils;

import com.userimran.dto.OrderDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static ByteArrayInputStream generateExcel(List<OrderDto> orderDto) throws IOException {

        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {

            Sheet sheet = workbook.createSheet("Orders");

            // Header row
            String[] headers = {"Order ID", "Order Date", "Payment Type", "Price", "Product ID", "Quantity", " Status", "User ID"};
            XSSFCellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor((short) 12);
            Row headRow = sheet.createRow(0);

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            CellStyle dateCellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            // create a date formatter

            // Data row
            int rowNum = 1;
            for (OrderDto order : orderDto) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(order.getOrderId());
                Cell dateCell = row.createCell(1);
                dateCell.setCellValue(order.getOrderDate());
                dateCell.setCellStyle(dateCellStyle);
                row.createCell(2).setCellValue(order.getPaymentType());
                row.createCell(3).setCellValue(order.getPrice());
                row.createCell(4).setCellValue(order.getProductId());
                row.createCell(5).setCellValue(order.getQuantity());
                row.createCell(6).setCellValue(order.getOrderStatus().name());
                row.createCell(7).setCellValue(order.getUserId());
            }
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}
