package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;

public interface TherapistService {

    void joinTherapistUser(Long centerId, AddUserRequest addUserRequest);

}
