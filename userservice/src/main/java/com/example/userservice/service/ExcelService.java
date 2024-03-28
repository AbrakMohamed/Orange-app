package com.example.userservice.service;

import com.example.userservice.entity.CellEntity;
import com.example.userservice.entity.Champ;
import com.example.userservice.repository.CellRepository;
import com.example.userservice.repository.ChampRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    ChampRepository champRepository;
    @Autowired
    CellRepository cellRepo;
    public void generateExcel(HttpServletResponse response) throws Exception {

        List<Champ> rows = champRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Champ");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Type");
        row.createCell(1).setCellValue("Titre");
        row.createCell(2).setCellValue("Question");

        int dataChampIndex = 1;

        for (Champ Champ : rows) {
            HSSFRow dataChamp = sheet.createRow(dataChampIndex);
            dataChamp.createCell(0).setCellValue(Champ.getTypeChamp());
            dataChamp.createCell(1).setCellValue(Champ.getNomChamp());
            dataChamp.createCell(2).setCellValue(Champ.getQuestion());
            dataChampIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }


    public static void readDataFromExcel(String fileName) throws IOException {

        // Passing File as input to Workbook
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook book = new XSSFWorkbook(fis);

        // Getting sheet at 0th index
        XSSFSheet sheet = book.getSheetAt(0);

        // Getting Champs from sheet
        Iterator<Row> rowIterator = sheet.rowIterator();

        int rowIndex = 0;

        while (rowIterator.hasNext()) {
            XSSFRow row = (XSSFRow) rowIterator.next();

            if (isRowEmpty(row)) {
                continue; // Skip empty row
            }
            // skipping header row
            if (rowIndex == 0) {
                // incrementing rowIndex
                rowIndex++;
                continue;
            }

            // Getting cell iterator of a row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                // Getting cell of row
                Cell cell = cellIterator.next();

                // getting cell value
                String cellVal = cell.getStringCellValue();

                System.out.print(cellVal + "\t");
            }
            System.out.println();
        }
        book.close();
        fis.close();
    }
    // Helper method to check if a row is empty
    private static boolean isRowEmpty(org.apache.poi.ss.usermodel.Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() != CellType.BLANK) {
                return false; // Row is not empty
            }
        }
        return true; // Row is empty
    }
    public void writeDataFromExcelToDatabase(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName); XSSFWorkbook book = new XSSFWorkbook(fis)) {

            Champ champ = new Champ();
            champRepository.save(champ);

            List<CellEntity> cellEntities =new ArrayList<>();

            XSSFSheet sheet = book.getSheetAt(0);

            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.rowIterator();

            int rowIndex = 0;

            while (rowIterator.hasNext()) {
                XSSFRow row = (XSSFRow) rowIterator.next();

                if (isRowEmpty(row)) {
                    continue; // Skip empty row
                }
                //For each row, iterate through all the columns
                if (rowIndex == 0) {
                    // incrementing rowIndex
                    rowIndex++;// skipping header row
                    continue;
                }

                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();


                int cellIndex = 0;
                    //Add the CellEntity to the list
                    while (cellIterator.hasNext()) {
                        // Getting cell of row
                        Cell cell = cellIterator.next();
                        // Check if cell is empty
                        if (cell.getCellType() == CellType.BLANK) {
                            continue; // Skip empty cell
                        }

                        switch (cellIndex) {
                            case 0 -> {
                                champ= new Champ();
                                champ.setTypeChamp(cell.getStringCellValue());
                            }
                            case 1 -> {
                                champ.setNomChamp(cell.getStringCellValue());
                            }
                            case 2 -> {
                                champ.setQuestion(cell.getStringCellValue());
                                champRepository.save(champ);
                            }

                        }

                        cellIndex++;//Increments the cellIndex to move to the next cell in the row.
                    }

                }




        System.out.println();
        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }

/* 2nd try
    public static boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Champ> getChampDataFromExcel(InputStream inputStream){
        List<Champ> champs= new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("champs");
            int rowIndex =0;
            for (Champ row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Champ champ = new Champ();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> champ.setTypeChamp(cell.getStringCellValue());
                        case 1 -> champ.setNomChamp(cell.getStringCellValue());
                        case 2 -> champ.setQuestion(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                champs.add(champ);
            }
        }catch (IOException e) {
            e.getStackTrace();
        }
        return champs;
    }*/



  /* 1st try
   public void writeExcel(String fileName, List<Formulaire> formulaires) {
        try (Workbook workbook = new XSSFWorkbook()) { // Create new workbook
            for (Formulaire formulaire : formulaires) {
                Sheet sheet = workbook.createSheet(formulaire.getNomForm()); // Create a sheet for each formulaire
                List<Champ> champs = formulaire.getListChamps();
                for (int i = 0; i < champs.size(); i++) {
                    Champ row = sheet.createChamp(i); // Create a row for each champ
                    Champ champ = champs.get(i);
                    Cell cell = row.createCell(0); // Create a cell

                    if (champ.getListchoixChamp()!= null && !champ.getListchoixChamp().isEmpty()) {
                        // Handle select_one or select_multiple cell
                        DataValidationHelper validationHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
                        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(i, i, 0, 0);
                        DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(
                                champ.getListchoixChamp().toArray(new String[0]));
                        DataValidation dataValidation = validationHelper.createValidation(constraint, cellRangeAddressList);
                        sheet.addValidationData(dataValidation);
                    } else {
                        // Handle regular cell
                        cell.setCellValue(champ.getQuestion());
                    }
                }
            }

            // Write the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
