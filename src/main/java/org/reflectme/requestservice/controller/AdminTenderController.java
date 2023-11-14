package org.reflectme.requestservice.controller;

import lombok.AllArgsConstructor;
import org.reflectme.requestservice.dto.TenderDTO;
import org.reflectme.requestservice.service.TenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/admin/tender")
@AllArgsConstructor
public class AdminTenderController {
    private final TenderService tenderService;
    @GetMapping("/active")
    public ResponseEntity<List<TenderDTO>> getActiveRequests() {
        return ResponseEntity.ok(tenderService.getActiveRequests());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TenderDTO>> getAllRequests() {
        return ResponseEntity.ok(tenderService.getAllRequests());
    }

    @PutMapping("/close/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void closeTenderById(@PathVariable Long id) {
        tenderService.close(id);
    }

    @GetMapping(value = "/file/{id}", produces = "multipart/form-data")
    public ResponseEntity<byte[]> getFileById(@PathVariable Long id) throws SQLException {
        TenderService.FilePair pair = tenderService.getTenderFileByFileId(id);
        return ResponseEntity.ok()
                .header("Access-Control-Expose-Headers", "File-Name")
                .header("File-Name", pair.name())
                .body(pair.content());
    }
}
