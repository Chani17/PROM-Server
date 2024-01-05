package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.user.AddDirectorRequest;

public interface TherapistService {

    String joinTherapist(Long centerId, AddDirectorRequest addDirectorRequest);

}
