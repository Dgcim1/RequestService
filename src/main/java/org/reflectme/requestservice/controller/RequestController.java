package org.reflectme.requestservice.controller;

import lombok.AllArgsConstructor;
import org.reflectme.requestservice.entity.Request;
import org.reflectme.requestservice.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
@AllArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<Request> saveBook(
            @RequestBody Request request
    ){
        return ResponseEntity.ok(
                requestService.saveRequest(
                        Request.builder()
                                .email(request.getEmail())
                                .name(request.getName())
                                .comment(request.getComment())
                                .build()
                )
        );
    }

}
