package com.example.employbee;
import android.content.*;
import android.content.res.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class EditTasks {
    private static Cell cell;
    private static Sheet sheet;

    // must call before using other methods (OnCreate)
    public static void getSheet(Context context, String fileName) throws IOException{
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);
    }

    // fileName is "SampleSheet.xls" hypothetically speaking
    public static ArrayList<Task> getTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Row row: sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();

            String task = new String();
            int shift, pos;
            boolean done;

            task = cellIterator.next().getStringCellValue();
            shift = (int) cellIterator.next().getNumericCellValue();
            pos = (int) cellIterator.next().getNumericCellValue();
            done = (int) cellIterator.next().getNumericCellValue() == 1;

            tasks.add(new Task(task, shift, pos, done));
        }

        return tasks;
    }

    // adds a new task
    public static void addTask(Task task, Context context, String fileName) throws IOException {
        addTaskHelper(task);

        File file = new File(context.getExternalFilesDir(null), fileName);
        FileOutputStream outputStream = new FileOutputStream(file);
        Workbook workbook = new HSSFWorkbook(new FileInputStream(file));
        workbook.write(outputStream);

    }

    private static void addTaskHelper(Task task) {
        int lastRow = sheet.getLastRowNum();
        Row row = sheet.createRow(lastRow + 1);

        row.createCell(0).setCellValue(task.getTask());
        row.createCell(1).setCellValue(task.getShift());
        row.createCell(2).setCellValue(task.getPos());
        row.createCell(3).setCellValue(task.getDone());
    }
}
