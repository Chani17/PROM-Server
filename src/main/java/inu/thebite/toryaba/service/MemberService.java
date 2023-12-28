package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.model.user.AddTherapistRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;

public interface MemberService {
    void login(LoginUserRequest loginUserRequest);

    Member findById(String id);
}
