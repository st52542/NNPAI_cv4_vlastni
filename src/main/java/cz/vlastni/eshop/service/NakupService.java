package cz.vlastni.eshop.service;

import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.entity.Nakup;
import cz.vlastni.eshop.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class NakupService implements INakupService {
    private Map<Doprava, Integer> kosik;
    private final NakupRepository nakupRepository;
    private final DopravaRepository dopravaRepository;
    private final PlatbaRepository platbaRepository;
    private final UzivatelRepository uzivatelRepository;

    public NakupService(NakupRepository nakupRepository, DopravaRepository dopravaRepository, PlatbaRepository platbaRepository, UzivatelRepository uzivatelRepository) {
        this.nakupRepository = nakupRepository;
        this.dopravaRepository = dopravaRepository;
        this.platbaRepository = platbaRepository;
        this.uzivatelRepository = uzivatelRepository;
        this.kosik = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Doprava doprava = dopravaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (kosik.containsKey(doprava)) {
            kosik.replace(doprava, kosik.get(doprava) + 1);
        } else {
            kosik.put(doprava, 1);
        }
    }

    @Override
    public void remove(Long id) {
        Doprava doprava = dopravaRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if (kosik.containsKey(doprava)) {
            if (kosik.get(doprava) > 1) {
                kosik.replace(doprava, kosik.get(doprava) - 1);
            } else {
                kosik.remove(doprava);
            }
        }
    }

    @Override
    public Map<Doprava, Integer> getKosik() {
        return kosik;
    }

    @Override
    public void checkout() {
        Nakup nakup = new Nakup();
        nakup.setDatumVytvoreni(new Date());
        nakup.setObjednavka((int) ((int)(long)((int) ((new Date().getTime()/1000)))*1000L));
        nakup.setStav(true);
        nakup.setPlatba(platbaRepository.getOne(1L));
        nakup.setUzivatel(uzivatelRepository.getOne(1L));
        nakup.setDoprava(dopravaRepository.getOne(1L));
        nakupRepository.save(nakup);
        kosik.clear();
    }
}
