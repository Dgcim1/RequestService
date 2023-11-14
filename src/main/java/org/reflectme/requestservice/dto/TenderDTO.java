package org.reflectme.requestservice.dto;

import java.util.List;

public record TenderDTO(
        String name,
        String email,
        String phone,
        String law,
        String comment,
        List<Long> files
) {}
