package com.sd64.novastore.controller;

import com.sd64.novastore.request.BillHistoryRequest;
import com.sd64.novastore.service.BillHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill_history")
public class BillHistoryController {
    @Autowired
    private BillHistoryService billHistoryService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(billHistoryService.getAll());
    }

    @GetMapping("/getallpt")
    public ResponseEntity<?> getAllPT(@RequestParam(defaultValue = "0", value = "page") Integer page) {
        return ResponseEntity.ok(billHistoryService.getAllPT(page).getContent());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(billHistoryService.delete(id));
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid BillHistoryRequest billHistoryRequest, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(billHistoryService.add(billHistoryRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid BillHistoryRequest billHistoryRequest, @PathVariable Integer id, BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.ok(billHistoryService.update(billHistoryRequest, id));

    }
}
