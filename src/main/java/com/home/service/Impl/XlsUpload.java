package com.home.service.Impl;

import com.home.entity.Request;
import com.home.entity.Session;
import com.home.entity.User;
import com.home.service.UserService;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Nazar on 27.06.2017.
 */
@Service
public class XlsUpload {
    private static final Logger LOGGER = Logger.getLogger(XlsUpload.class);

    private UserService userService;
    private byte[] bytes;

    @Autowired
    public XlsUpload(UserService userService) {
        this.userService = userService;
    }

    public byte[] writeReportToXLS() {

        List<User> users = userService.getOrderedUsers();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook
                .createSheet("users activity report");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("User name");
        cell = row.createCell(2);
        cell.setCellValue("User group");
        cell = row.createCell(3);
        cell.setCellValue("Session date opened");
        cell = row.createCell(4);
        cell.setCellValue("Session date closed");
        cell = row.createCell(5);
        cell.setCellValue("Request URL");
        cell = row.createCell(6);
        cell.setCellValue("Request method");
        cell = row.createCell(6);
        cell.setCellValue("Request params");

        int i = 2;

        for (User user : users) {
            if (row.getCell(1) != null) {
                row = spreadsheet.createRow(i);
            }
            cell = row.createCell(1);
            cell.setCellValue(user.getName());
            cell = row.createCell(2);
            cell.setCellValue(user.getGroup().getName());

            for (Session session : user.sessions) {
                if (row.getCell(1) == null && row.getCell(2) == null) {
                    cell = row.createCell(1);
                    cell.setCellValue(user.getName());
                    cell = row.createCell(2);
                    cell.setCellValue(user.getGroup().getName());
                }
                cell = row.createCell(3);
                cell.setCellValue(session.getDateOpened());
                cell = row.createCell(4);
                cell.setCellValue(session.getDateClosed());

                for (Request request : session.requests) {
                    if (row.getCell(1) == null && row.getCell(2) == null
                            && row.getCell(3) == null && row.getCell(4) == null) {
                        cell = row.createCell(1);
                        cell.setCellValue(user.getName());
                        cell = row.createCell(2);
                        cell.setCellValue(user.getGroup().getName());
                        cell = row.createCell(3);
                        cell.setCellValue(session.getDateOpened());
                        cell = row.createCell(4);
                        cell.setCellValue(session.getDateClosed());
                    }

                    cell = row.createCell(5);
                    cell.setCellValue(request.getUrl());
                    cell = row.createCell(6);
                    cell.setCellValue(request.getMethod());
                    cell = row.createCell(7);
                    cell.setCellValue(request.getParams());

                    i++;
                    row = spreadsheet.createRow(i);
                }
            }
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            workbook.write(bos);

            bytes = bos.toByteArray();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return bytes;
    }

}
