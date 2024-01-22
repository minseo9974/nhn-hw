package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.request.ResidentRequest;
import com.nhnacademy.certificate.entity.Resident;
import com.nhnacademy.certificate.exception.ResidentAllreadyExistsException;
import com.nhnacademy.certificate.service.ResidentService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class ResidentRestController {
    private final ResidentService residentService;

    public ResidentRestController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Resident request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        if (residentService.checkResident(request.getResidentSerialNumber())) {
            throw new ResidentAllreadyExistsException();
        }

        residentService.createResident(request);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<?> update(@RequestBody @Valid ResidentRequest request,
                                    @PathVariable("serialNumber") Integer residentSerialNumber,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        Resident resident = residentService.getResidentById(residentSerialNumber);
        resident.setRegistrationBaseAddress(request.getRegistrationBaseAddress());
        residentService.modifyResident(resident);
        return ResponseEntity.ok().build();
    }

}
