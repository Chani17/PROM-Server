package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.looseCannon.LooseCannonRequest;
import inu.thebite.toryaba.model.looseCannon.LooseCannonResponse;

public interface LooseCannonService {

    void addLooseCannon(LooseCannonRequest looseCannonRequest);

    LooseCannonResponse getLooseCannonList();
}
