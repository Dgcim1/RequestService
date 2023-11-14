package org.reflectme.requestservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.reflectme.requestservice.dto.TenderDTO;
import org.reflectme.requestservice.service.TenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/tender")
@RequiredArgsConstructor
public class TenderController {
    private final TenderService tenderService;
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveTender(@RequestBody TenderDTO body) {
        tenderService.save(body);
    }

    @PostMapping(path = "/file", consumes = "multipart/form-data")
    public ResponseEntity<Object> saveTenderFile(@RequestPart MultipartFile body) throws SQLException, IOException {
        return ResponseEntity.ok(new Object() {
            @JsonProperty("id")
            public long id = tenderService.saveFile(body);
        });
    }
}
