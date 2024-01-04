package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken findByRefreshToken(String refreshToken);
}
