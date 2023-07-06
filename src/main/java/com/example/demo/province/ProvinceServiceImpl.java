package com.example.demo.province;

import com.example.demo.province.entity.Province;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ProvinceServiceImpl {
    @Autowired
    private ProvinceRepository repository;

    public List<Province> findAll() {

        return repository.findAll();
    }

    public Province save(Province province) {
        return repository.save(province);
    }

    public Province findById(UUID id) {
        return repository.findById(id).get();
    }

    public void delete(UUID id) {
        repository.delete(findById(id));
    }

    public void exportExcel(HttpServletResponse response) {

    }

//    public void generateExcel(HttpServletResponse response) throws IOException {
//        List<Province> listProvince = repository.findAll();
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("Province info");
//        HSSFRow row = sheet.createRow(0);
//        row.createCell(0).setCellValue("ID");
//        row.createCell(1).setCellValue("Code");
//        row.createCell(2).setCellValue("name");
//        row.createCell(3).setCellValue("founded year");
//        row.createCell(4).setCellValue("acreage");
//
//        int dataRowIndex = 1;
//
//        for (Province province : listProvince
//        ) {
//            HSSFRow dataRow = sheet.createRow(dataRowIndex);
//            dataRow.createCell(0).setCellValue(province.getId());
//            dataRow.createCell(1).setCellValue(province.getCode());
//            dataRow.createCell(2).setCellValue(province.getName());
//            dataRow.createCell(3).setCellValue(province.getFoundedYear());
//            dataRow.createCell(4).setCellValue(province.getAcreage());
//            dataRowIndex++;
//        }
//        ServletOutputStream ops = response.getOutputStream();
//        workbook.write(ops);
//        workbook.close();
//        ops.close();
//
//    }
}
