package com.example.demo.commune;

import com.example.demo.commune.entity.Commune;
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
public class CommuneServiceImpl {
    @Autowired
    private CommuneRepository repository;

    public List<Commune> findAll() {
        return repository.findAll();
    }

    public Commune save(Commune commune) {
        System.out.println("hahsahashjđjhs" + UUID.randomUUID());
        System.out.println("hahsahashjđjhs" + String.valueOf(UUID.randomUUID()));
//        commune.setId(String.valueOf(UUID.randomUUID()));
        return repository.save(commune);
    }

//    public Commune findById(String id) {
//        return repository.findById(id).get();
//    }
//
//    public void delete(String id) {
//        repository.delete(findById(id));
//    }
//
//    public void update(String id, Commune commune) {
//        Commune newCom = repository.findById(id).get();
//        newCom.setName(commune.getName());
//        newCom.setCode(commune.getCode());
//        newCom.setFoundedYear(commune.getFoundedYear());
//        newCom.setAcreage(commune.getAcreage());
//        newCom.setNumberOfPeople(commune.getNumberOfPeople());
//        newCom.setDistrict(commune.getDistrict());
//        newCom.setProvince(commune.getProvince());
//        repository.save(newCom);
//    }

    public List<Commune> findByName(String name) {
        return repository.findAllByName(name);
    }
//
//    public List<District> findByProvince(String idProvince) {
//        return repository.findByProvince(idProvince);
//    }

    public void exportExcel(HttpServletResponse response) {

    }

//    public void generateExcel(HttpServletResponse response) throws IOException {
//        List<Commune> listCommune = repository.findAll();
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("commune info");
//        HSSFRow row = sheet.createRow(0);
//        row.createCell(0).setCellValue("ID");
//        row.createCell(1).setCellValue("Code");
//        row.createCell(2).setCellValue("name");
//        row.createCell(3).setCellValue("founded year");
//        row.createCell(4).setCellValue("acreage");
//
//        int dataRowIndex = 1;
//
//        for (Commune commune : listCommune
//        ) {
//            HSSFRow dataRow = sheet.createRow(dataRowIndex);
//            dataRow.createCell(0).setCellValue(commune.getId());
//            dataRow.createCell(1).setCellValue(commune.getCode());
//            dataRow.createCell(2).setCellValue(commune.getName());
//            dataRow.createCell(3).setCellValue(commune.getFoundedYear());
//            dataRow.createCell(4).setCellValue(commune.getAcreage());
//            dataRowIndex++;
//        }
//        ServletOutputStream ops = response.getOutputStream();
//        workbook.write(ops);
//        workbook.close();
//        ops.close();
//
//    }
}
