package org.reflectme.requestservice.service.impl;

import lombok.AllArgsConstructor;
import org.reflectme.requestservice.dao.RequestRepository;
import org.reflectme.requestservice.entity.Request;
import org.reflectme.requestservice.service.RequestService;
import org.reflectme.requestservice.util.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public Request getRequest(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("There is no request with id = " + id));
    }

    @Override
    public Request updateRequest(Request request) {
        if(!requestRepository.existsById(request.getId())){
            throw new NotFoundException("Book with id = " + request.getId() + " not found");
        }
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getActiveRequests() {
        return requestRepository.findAllByActiveIsTrue();
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
}
