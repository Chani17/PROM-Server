package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;

public interface PrincipalService {
    void joinPrincipalUser(AddUserRequest addUserRequest);

}
