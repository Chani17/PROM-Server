package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.model.user.AddTherapistRequest;

public interface TherapistService {

    String joinTherapist(Long centerId, AddTherapistRequest addDirectorRequest);

}
