package org.reflectme.requestservice.service;

import org.reflectme.requestservice.entity.Request;

import java.util.List;

public interface RequestService {
    Request saveRequest(Request request);
    Request getRequest(Long id);
    Request updateRequest(Request request);
    List<Request> getActiveRequests();
    List<Request> getAllRequests();
}
