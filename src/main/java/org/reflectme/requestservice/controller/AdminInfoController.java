package org.reflectme.requestservice.controller;

import lombok.AllArgsConstructor;
import org.reflectme.requestservice.entity.Request;
import org.reflectme.requestservice.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminInfoController {
    private final RequestService requestService;
    @GetMapping("/active")
    public ResponseEntity<List<Request>> getActiveRequests() {
        return ResponseEntity.ok(
                requestService.getActiveRequests()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<Request>> getAllRequests() {
        return ResponseEntity.ok(
                requestService.getAllRequests()
        );
    }

    @PutMapping("/request/close/{id}")
    public ResponseEntity<Request> closeRequest(
            @PathVariable Long id
    ) {
        var request = requestService.getRequest(id);
        request.setActive(false);
        return ResponseEntity.ok(
                requestService.saveRequest(
                        requestService.getRequest(id)
                )
        );
    }

}
