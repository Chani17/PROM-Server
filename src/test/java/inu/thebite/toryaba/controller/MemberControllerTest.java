package inu.thebite.toryaba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inu.thebite.toryaba.config.jwt.JwtFactory;
import inu.thebite.toryaba.entity.Director;
import inu.thebite.toryaba.entity.RefreshToken;
import inu.thebite.toryaba.model.user.CreateAccessTokenRequest;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret_key}")
    private String secretKey;


//    @BeforeEach
//    public void mockMvcSetUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//        memberRepository.deleteAll();
//    }

    @DisplayName("createNewAccessToken: 새로운 Access Token을 발급한다.")
    @Test
    public void createNewAccessToken() throws Exception {
        // given
        final String url = "/token";

        List<String> qualification = List.of("BCBA", "QBA", "KABA");
        Director director = new Director("test2", "test2", "테스트2", "test2@gmail.com", "010-1234-5678");
        Director savedMember = memberRepository.save(director);

        String refreshToken = JwtFactory.builder()
                .claims(Map.of("id", savedMember.getId()))
                .build()
                .createToken(issuer, secretKey);

        refreshTokenRepository.save(new RefreshToken(savedMember.getId(), refreshToken));

        CreateAccessTokenRequest request = new CreateAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accessToken").isNotEmpty());
    }
}