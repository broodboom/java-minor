package com.example.assignment2.infrastructure;

import com.example.assignment2.domain.Rekening;
import com.example.assignment2.domain.Rekeninghouder;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RekeningRepository {
    private final HashMap<Integer, HashSet<Integer>> RekeningenRekeninghouders = new HashMap<Integer, HashSet<Integer>>();
    private final HashMap<Integer, Rekening> Rekeningen = new HashMap<Integer, Rekening>();
    private final HashMap<Integer, Rekeninghouder> Rekeninghouders = new HashMap<Integer, Rekeninghouder>();

    public Integer getNextRekeningId() {
        return (Rekeningen.keySet().stream().max((a, b) -> a.compareTo(b)).orElse(0) + 1);
    }

    public Integer getNextRekeninghouderId() {
        return (Rekeninghouders.keySet().stream().max((a, b) -> a.compareTo(b)).orElse(0) + 1);
    }

    public void koppel(Rekening rekening, Rekeninghouder rekeninghouder) {
        HashSet rekeninghouders = new HashSet<Integer>();
        if (RekeningenRekeninghouders.containsKey(rekening.getId())) {
            rekeninghouders = RekeningenRekeninghouders.get(rekening.getId());
        }
        rekeninghouders.add(rekeninghouder.getId());
        RekeningenRekeninghouders.put(rekening.getId(), rekeninghouders);
    }

    public void ontkoppel(Rekening rekening, Rekeninghouder rekeninghouder) {
        if (!RekeningenRekeninghouders.containsKey(rekening.getId())) {
            return;
        }

        RekeningenRekeninghouders.get(rekening.getId()).remove(rekeninghouder.getId());
    }

    public Rekening getRekening(Integer key) {
        if (Rekeningen.containsKey(key)) {
            return Rekeningen.get(key);
        }
        return null;
    }

    public Collection<Rekening> getRekeningen() {
        return Rekeningen.values();
    }

    public void addRekening(Integer key, Rekening rekening) {
        Rekeningen.put(key, rekening);
    }

    public void updateRekening(Integer key, Rekening rekening) {
        rekening.setId(key);
        Rekeningen.put(key, rekening);
    }

    public void deleteRekening(Integer key) {
        Rekeningen.remove(key);
    }

    public Rekeninghouder getRekeninghouder(Integer key) {
        if (Rekeninghouders.containsKey(key)) {
            return Rekeninghouders.get(key);
        }
        return null;
    }

    public Collection<Rekeninghouder> getRekeninghouders() {
        return Rekeninghouders.values();
    }

    public void addRekeninghouder(Integer key, Rekeninghouder rekeninghouder) {
        Rekeninghouders.put(key, rekeninghouder);
    }

    public void updateRekeninghouder(Integer key, Rekeninghouder rekeninghouder) {
        rekeninghouder.setId(key);
        Rekeninghouders.put(key, rekeninghouder);
    }

    public void deleteRekeninghouder(Integer key) {
        Rekeninghouders.remove(key);
    }

    public List<Rekening> getRekeningenRekeninghouder(Integer rekeninghouderId) {
        List<Integer> rekeningenIds = RekeningenRekeninghouders.entrySet().stream()
                .filter(e -> e.getValue().contains(rekeninghouderId))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        List<Rekening> rekeningen = new ArrayList<Rekening>();

        rekeningenIds.forEach(e -> {
            rekeningen.add(getRekening(e));
        });

        return rekeningen;
    }
}
