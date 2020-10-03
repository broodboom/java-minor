package com.example.assignment2.infrastructure;

import com.example.assignment2.domain.Rekening;
import com.example.assignment2.domain.Rekeninghouder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RekeningService {
    @Autowired
    RekeningRepository rekeningRepository;

    public Rekening getRekening(Integer rekeningid) {
        return rekeningRepository.getRekening(rekeningid);
    }

    public Collection<Rekening> getRekeningen() {
        return rekeningRepository.getRekeningen();
    }

    public void updateRekening(Integer id, Rekening rekening) {
        rekeningRepository.updateRekening(id, rekening);
    }

    public void createRekening(Rekening rekening) {
        var id = rekeningRepository.getNextRekeningId();

        rekening.setId(id);

        rekeningRepository.addRekening(id, rekening);
    }

    public void deleteRekening(Integer rekeningid) {
        rekeningRepository.deleteRekening(rekeningid);
    }

    public void blokkeerRekening(Integer rekeningid) {
        var rekening = rekeningRepository.getRekening(rekeningid);

        rekening.setGeblokkeerd(true);

        rekeningRepository.updateRekening(rekeningid, rekening);
    }

    public void deblokkeerRekening(Integer rekeningid) {
        var rekening = rekeningRepository.getRekening(rekeningid);

        rekening.setGeblokkeerd(false);

        rekeningRepository.updateRekening(rekeningid, rekening);
    }

    public void koppelRekeninghouder(Integer rekeningId, Integer rekeninghouderId) {
        var rekening = rekeningRepository.getRekening(rekeningId);
        var rekeninghouder = rekeningRepository.getRekeninghouder(rekeninghouderId);

        rekeningRepository.koppel(rekening, rekeninghouder);
    }

    public void ontkoppelRekeninghouder(Integer rekeningId, Integer rekeninghouderId) {
        var rekening = rekeningRepository.getRekening(rekeningId);
        var rekeninghouder = rekeningRepository.getRekeninghouder(rekeninghouderId);

        rekeningRepository.ontkoppel(rekening, rekeninghouder);
    }

    public Rekeninghouder getRekeninghouder(Integer rekeninghouderId) {
        return rekeningRepository.getRekeninghouder(rekeninghouderId);
    }

    public Collection<Rekeninghouder> getRekeninghouders() {
        return rekeningRepository.getRekeninghouders();
    }

    public void updateRekeninghouder(Integer rekeninghouderId, Rekeninghouder rekeninghouder) {
        rekeningRepository.updateRekeninghouder(rekeninghouderId, rekeninghouder);
    }

    public void createRekeninghouder(Rekeninghouder rekeninghouder) {
        var id = rekeningRepository.getNextRekeninghouderId();

        rekeninghouder.setId(id);

        rekeningRepository.addRekeninghouder(id, rekeninghouder);
    }

    public void deleteRekeninghouder(Integer id) {
        rekeningRepository.deleteRekeninghouder(id);
    }

    public List<Rekening> getRekeningenRekeninghouder(Integer rekeninghouderId) {
        return rekeningRepository.getRekeningenRekeninghouder(rekeninghouderId);
    }
}
