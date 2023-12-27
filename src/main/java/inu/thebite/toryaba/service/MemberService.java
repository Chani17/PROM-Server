package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.user.LoginUserRequest;

public interface MemberService {

    void login(LoginUserRequest loginUserRequest);
}
