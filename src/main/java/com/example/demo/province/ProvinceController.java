package com.example.demo.province;

import com.example.demo.common.ResponseBean;
import com.example.demo.province.entity.Province;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@Tag(name = "003 - province")
@RequestMapping("/testProvince")
public class ProvinceController {
    @Autowired
    private ProvinceServiceImpl service;

    @GetMapping("/find-all")
    @Operation(summary = "[Hiển thị tất cả--t]")
    public ResponseEntity<?> findAll(Model model) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
//        resBean.setMessage(Constants.SUCCESS);
        resBean.setData(service.findAll());
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "[Xóa]")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        service.delete(id);
        List<Province> listDistrict = service.findAll();
        resBean.setData(listDistrict);
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    @Operation(summary = "[Lấy ra 1 xã]")
    public ResponseEntity<Object> findById(@PathVariable("id") UUID id) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setData(service.findById(id));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @PostMapping("/add")
    @Operation(summary = "[Thêm xã mới]")
    public ResponseEntity<Object> add(Model model, @RequestBody @Valid Province entity) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        service.save(entity);
        resBean.setData(service.save(entity));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "[Sửa]")
    public ResponseEntity<Object> update(@RequestParam("id") String id, @RequestBody @Valid Province entity) {
        ResponseBean resBean = new ResponseBean();
        resBean.setCode(HttpStatus.OK.toString());
        resBean.setData(service.save(entity));
        return new ResponseEntity<>(resBean, HttpStatus.OK);
    }

    @GetMapping("/excel")
    public void generateExcelReport(HttpServletResponse response) {
        response.setContentType("application/oclet-stream");

        String headerKey = "Conten-Disposition";
        String headerValue = "attachment;filename= commune.xls";
        response.setHeader(headerKey, headerValue);
        service.exportExcel(response);
    }
}
