package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.model.user.LoginUserRequest;

public interface MemberService {
    Member login(LoginUserRequest loginUserRequest);

    Member findById(String id);
}
