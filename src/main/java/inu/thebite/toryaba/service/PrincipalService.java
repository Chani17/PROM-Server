package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.AddUserRequest;

public interface PrincipalService {
    Principal joinPrincipalUser(AddUserRequest addUserRequest);
}
