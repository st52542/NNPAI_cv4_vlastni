package cz.vlastni.eshop.service;

import cz.vlastni.eshop.entity.Doprava;

import java.util.Map;

public interface INakupService {
    void add(Long id);


    void remove(Long id);

    Map<Doprava,Integer> getKosik();

    void checkout();
}
