package com.example.demo.commune;

import com.example.demo.common.ResponseBean;
import com.example.demo.commune.entity.Commune;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@Tag(name = "001 - commune")
@RequestMapping("/testCommunne")
public class CommuneController {
    @Autowired
    private CommuneServiceImpl service;

    @GetMapping("/find-all")
    @Operation(summary = "[Hiển thị tất cả--t]")
    public ResponseEntity<?> findAll(Model model) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setMessage("SUCCESS");
        resBean.setData(service.findAll());
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }


//    @DeleteMapping("/delete/{id}")
//    @Operation(summary = "[Xóa]")
//    public ResponseEntity<?> delete(@PathVariable("id") String id) {
//        ResponseBean resBean = new ResponseBean();
//        resBean.setCode(HttpStatus.OK.toString());
//        service.delete(id);
//        List<Commune> listCommune = service.findAll();
//        resBean.setData(listCommune);
//        return new ResponseEntity<>(resBean, HttpStatus.OK);
//    }

//    @GetMapping("/findById/{id}")
//    @Operation(summary = "[Lấy ra 1 xã]")
//    public ResponseEntity<Object> findById(@PathVariable("id") String id) {
//        ResponseBean resBean = new ResponseBean();
//        resBean.setCode(HttpStatus.OK.toString());
//        resBean.setData(service.findById(id));
//        return new ResponseEntity<>(resBean, HttpStatus.OK);
//    }

    @GetMapping("/findByName")
    @Operation(summary = "[Tìm kiếm xã]")
    public ResponseEntity<?> findByName(@RequestParam(name = "name") String name) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setData(service.findByName(name));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "[Thêm xã mới]")
    public ResponseEntity<Object> add(Model model, @RequestBody @Valid Commune entity) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setData(service.save(entity));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "[Sửa]")
    public ResponseEntity<Object> update(Model model, @RequestParam("id") String id, @RequestBody @Valid Commune entity) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setData(service.save(entity));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @GetMapping("/export/excel")
    @Operation(summary = "[Xuất excel]")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Commune> listCommune = service.findAll();

        ExportCommune excelExporter = new ExportCommune(listCommune);

        excelExporter.export(response);
    }
}
