package org.reflectme.requestservice.dto;

import java.util.List;

public record TenderRequest(
        String name,
        String email,
        String phone,
        String law,
        String comment,
        List<Long> files
) {}
