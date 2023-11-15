package org.reflectme.requestservice.dto;

import java.sql.Timestamp;
import java.util.List;

public record TenderResponse(
        String name,
        String email,
        String phone,
        String law,
        String comment,
        Timestamp creationTimestamp,
        List<Long> files
) {}
