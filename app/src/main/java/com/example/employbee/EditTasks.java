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

    // fileName is "SampleSheet.xls" hypothetically speaking
    public static ArrayList<Task> getTasks(Context context, String fileName) throws IOException {
        File file = new File(context.getExternalFilesDir(null), fileName);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = new HSSFWorkbook(inputStream);
        sheet = workbook.getSheetAt(0);

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
}
