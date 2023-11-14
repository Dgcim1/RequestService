package org.reflectme.requestservice.service;

import lombok.RequiredArgsConstructor;
import org.reflectme.requestservice.dao.TenderFileRepository;
import org.reflectme.requestservice.dao.TenderRepository;
import org.reflectme.requestservice.dto.TenderDTO;
import org.reflectme.requestservice.entity.Tender;
import org.reflectme.requestservice.entity.TenderFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TenderService {
    private final TenderRepository tenderRepository;
    private final TenderFileRepository tenderFileRepository;
    public List<TenderDTO> getActiveRequests() {
        return tenderRepository.findAllByActiveIsTrue()
                .stream()
                .map(this::map)
                .toList();
    }

    public List<TenderDTO> getAllRequests() {
        return tenderRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    public void close(Long tenderId) {
        var tender = tenderRepository.findById(tenderId).orElseThrow();
        tender.setActive(false);
        tenderRepository.save(tender);
    }

    public record FilePair(String name, byte[] content) {}

    @Transactional
    public FilePair getTenderFileByFileId(Long fileId) throws SQLException {
        var tenderFile = tenderFileRepository.findById(fileId).orElseThrow();
        return new FilePair(
                tenderFile.getName(),
                tenderFile.getContent()
        );
    }

    public void save(TenderDTO body) {
        var tender = Tender
                .builder()
                .name(body.name())
                .email(body.email())
                .comment(body.comment())
                .phone(body.phone())
                .law(body.law())
                .active(true)
                .build();
        tenderRepository.save(tender);

        if (body.files() == null)
            return;

        body.files()
                .stream()
                .map(tenderFileRepository::findById)
                .map(Optional::orElseThrow)
                .forEach(file -> {
                    file.setTender(tender);
                    tenderFileRepository.save(file);
                });
    }

    public long saveFile(MultipartFile file) throws IOException, SQLException {
        TenderFile tenderFile = TenderFile
                .builder()
                .name(file.getOriginalFilename())
                .content(file.getBytes())
                .build();
        tenderFileRepository.save(tenderFile);
        return  tenderFile.getId();
    }

    private TenderDTO map(Tender tender) {
        return new TenderDTO(
                tender.getName(),
                tender.getEmail(),
                tender.getPhone(),
                tender.getLaw(),
                tender.getComment(),
                tenderFileRepository.customFindAllByTenderId(tender.getId()).stream().map(TenderFile::getId).toList()
        );
    }
}
