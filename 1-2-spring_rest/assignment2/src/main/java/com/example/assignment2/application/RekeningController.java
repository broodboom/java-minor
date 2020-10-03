package com.example.assignment2.application;

import com.example.assignment2.domain.Rekening;
import com.example.assignment2.infrastructure.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;

@RestController
@RequestMapping("rekening")
public class RekeningController {

    @Autowired
    RekeningService rekeningService;

    @GetMapping("/{id}")
    ResponseEntity<String> getRekening(@PathVariable("id") Integer id) {
        var rekening = rekeningService.getRekening(id);

        if (rekening == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(rekening, HttpStatus.OK);
    }

    @GetMapping("")
    ResponseEntity<Collection<Rekening>> getRekeningen() {
        return new ResponseEntity(rekeningService.getRekeningen(), HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity createRekening(@Valid @RequestBody Rekening rekening, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        rekeningService.createRekening(rekening);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/blokkeer")
    ResponseEntity blokkeerRekening(@PathVariable("id") Integer id) {
        if (rekeningService.getRekening(id) == null) {
            return new ResponseEntity("Rekening bestaat niet", HttpStatus.NOT_FOUND);
        }

        rekeningService.blokkeerRekening(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/deblokkeer")
    ResponseEntity deblokkeerRekening(@PathVariable("id") Integer id) {
        if (rekeningService.getRekening(id) == null) {
            return new ResponseEntity("Rekening bestaat niet", HttpStatus.NOT_FOUND);
        }

        rekeningService.deblokkeerRekening(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity updateRekening(@PathVariable("id") Integer id, @Valid @RequestBody Rekening rekening, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        var r = rekeningService.getRekening(id);

        if (r == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        rekeningService.updateRekening(id, rekening);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteRekening(@PathVariable("id") Integer id) {
        var rekening = rekeningService.getRekening(id);

        if (rekening == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        rekeningService.deleteRekening(id);
        
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{rekeningid}/rekeninghouders/{rekeninghouderid}")
    ResponseEntity koppelRekeninghouder(@PathVariable("rekeningid") Integer rekeningid, @PathVariable("rekeninghouderid") Integer rekeninghouderid) {
        rekeningService.koppelRekeninghouder(rekeningid, rekeninghouderid);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{rekeningid}/rekeninghouders/{rekeninghouderid}")
    ResponseEntity ontkoppelRekeninghouder(@PathVariable("rekeningid") Integer rekeningid, @PathVariable("rekeninghouderid") Integer rekeninghouderid) {
        rekeningService.ontkoppelRekeninghouder(rekeningid, rekeninghouderid);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("test")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("hello world", HttpStatus.I_AM_A_TEAPOT);
    }
}
