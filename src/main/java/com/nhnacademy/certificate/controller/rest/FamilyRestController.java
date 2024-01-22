package com.nhnacademy.certificate.controller.rest;

import com.nhnacademy.certificate.domain.request.FamilyCreateRequest;
import com.nhnacademy.certificate.domain.request.FamilyUpdateRequest;
import com.nhnacademy.certificate.entity.FamilyRelationship;
import com.nhnacademy.certificate.exception.FamilyRelationshipAllreadyExistsException;
import com.nhnacademy.certificate.service.FamilyRelationshipService;
import com.nhnacademy.certificate.service.ResidentService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class FamilyRestController {
    private final ResidentService residentService;
    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRestController(ResidentService residentService, FamilyRelationshipService familyRelationshipService) {
        this.residentService = residentService;
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<?> create(@RequestBody @Valid FamilyCreateRequest request, @PathVariable("serialNumber")Integer residentSerialNumber,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        FamilyRelationship.Pk pk = new FamilyRelationship.Pk(request.getFamilySerialNumber(), residentSerialNumber);
        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(pk);

        if (familyRelationshipService.exists(pk)) {
            throw new FamilyRelationshipAllreadyExistsException();
        }

        familyRelationship.setResident(residentService.getResidentById(residentSerialNumber));
        familyRelationship.setFamilyRelationshipCode(request.getRelationShip());
        familyRelationshipService.createFamilyRelationship(familyRelationship);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<?> create(@RequestBody @Valid FamilyUpdateRequest request, @PathVariable("serialNumber")Integer residentSerialNumber, @PathVariable Integer familySerialNumber,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());

            return ResponseEntity.badRequest().body(errors);
        }
        FamilyRelationship family = familyRelationshipService.getFamily(
                new FamilyRelationship.Pk(familySerialNumber, residentSerialNumber));
        family.setFamilyRelationshipCode(request.getRelationShip());
        familyRelationshipService.updateFamilyRelationship(family);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<?> create(@PathVariable("serialNumber")Integer residentSerialNumber, @PathVariable Integer familySerialNumber) {
        FamilyRelationship family = familyRelationshipService.getFamily(
                new FamilyRelationship.Pk(familySerialNumber, residentSerialNumber));
        familyRelationshipService.deleteFamily(family);

        return ResponseEntity.ok().build();
    }
}
