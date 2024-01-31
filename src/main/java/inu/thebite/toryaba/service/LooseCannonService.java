package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.sto.LooseCannonRequest;
import inu.thebite.toryaba.model.sto.LooseCannonResponse;

public interface LooseCannonService {

    void addLooseCannon(LooseCannonRequest looseCannonRequest);

    LooseCannonResponse getLooseCannonList();
}
