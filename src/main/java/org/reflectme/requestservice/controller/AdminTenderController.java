package org.reflectme.requestservice.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.reflectme.requestservice.dto.TenderDTO;
import org.reflectme.requestservice.service.TenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
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
                .header("File-Name", prepareFileName(pair.name()))
                .body(pair.content());
    }

    private static String prepareFileName(String str) {
        str = transliterate(str);
        return new String(str.getBytes(StandardCharsets.US_ASCII));
    }

    private static String transliterate(String message){
        char[] abcCyr =   {'.',' ','а','б','в','г','д','е','ё', 'ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х', 'ц','ч', 'ш' ,'щ'  ,'ъ','ы','ь','э', 'ю','я' ,'А','Б','В','Г','Д','Е','Ё', 'Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х', 'Ц', 'Ч','Ш', 'Щ','Ъ','Ы','Ь','Э','Ю','Я'};
        String[] abcLat = {".","_","a","b","v","g","d","e","e","zh","z","i","y","k","l","m","n","o","p","r","s","t","u","f","h","ts","ch","sh","sch","" ,"i", "","e","ju","ja","A","B","V","G","D","E","E","Zh","Z","I","Y","K","L","M","N","O","P","R","S","T","U","F","H","Ts","Ch","Sh","Sch", "","I", "","E","Ju","Ja"};
        List<Character> chars = Arrays.stream(ArrayUtils.toObject(abcCyr)).toList();
        StringBuilder builder = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            if (chars.contains(message.charAt(i))) {
                for (int x = 0; x < abcCyr.length; x++) {
                    if (message.charAt(i) == abcCyr[x]) {
                        builder.append(abcLat[x]);
                    }
                }
            } else {
                builder.append(message.charAt(i));
            }
        }
        return builder.toString();
    }
}
