package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.user.AddDirectorRequest;

public interface TherapistService {

    void joinTherapistUser(Long centerId, AddDirectorRequest addDirectorRequest);

}
