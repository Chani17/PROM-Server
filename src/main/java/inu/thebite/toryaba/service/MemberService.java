package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.*;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface MemberService {
    Member login(LoginUserRequest loginUserRequest);

    Member findById(String id);

    FindMemberIdResponse findMemberId(FindMemberIdRequest findMemberIdRequest);

    String findMemberPassword(FindMemberPasswordRequest findMemberPasswordRequest);

    boolean updatePassword(User user, UpdatePasswordRequest updatePasswordRequest);

    void approveAuth(String id);

    List<Therapist> getOutstandingAuthorization(Long centerId);
}
