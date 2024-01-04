package inu.thebite.toryaba.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationTokenResponse {

    private String name;

    private boolean result;
}
