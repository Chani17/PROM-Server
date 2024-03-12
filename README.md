# PROM PROJECT
<figure class="half">
  <a href="link"><img src="https://github.com/Chani17/ToryAba-Server/blob/main/thebite_logo.png" width="300" height="300"></a> 
  <a href="link"><img src="https://github.com/Chani17/ToryAba-Server/blob/main/prom_logo.png" width="400" height="300"></a>
</figure>
<br><br>
예비창업패키지 팀 "THEBITE"에서 개발한 product의 서버 코드입니다.

<br><br>
##  🏠  프로젝트 소개
유아 발달 교육 센터의 선생님들을 위한 솔루션으로, 발달 지연 및 자폐 스펙트럼 아동들의 치료 기록을 위한 SW입니다.

PROM은 수기 기록 체계의 비효율을 효율적으로 개선하고자 하며, 센터 측의 시간과 비용의 절감을 위해 개발하게 되었습니다.

<br><br>
## ⏳ 개발 기간
- 2023.10.04 ~ 2024.02.23 (약 5개월)

<br><br>
## 💡 주요 기능
- 원장님/선생님 회원가입 및 로그인
- 아이디/비밀번호 찾기, 임시 비밀번호 기능
- 센터/반/아이 등록
- 발달영역/LTO(Long Term Object; 장기목표)/STO(Short Term Object; 단기 목표) 등록
- 돌발상황/집중도/스트레스 정도/치료 영역타입 등록 기능
- 오늘 진행할 STO schedule 등록 [Todo]
- Learn Unit(+, -, P)/회차 기록
- Learn Unit 그래프 기능
- 알림장/세부 알림장 자동 생성 및 문구 자동완성 기능
- 알림장 webView로 보여주는 기능

<br><br>
## 🔧 개발 환경
Java 17, Springboot 3.1.4, MySQL 8.0, Docker, GCP

<br><br>
## 📌 Architecture
**현재 구성되어 있는 architecture**
![prom_architecture](https://github.com/Chani17/ToryAba-Server/blob/main/prom_architecture.png)
사용자가 많지 않을 뿐더러, 테스트 기간이라 instance에 외부 고정 ip를 할당하여 구성하였다.
<br><br><br><br>
**추후 많은 사용자를 고려한 architecture**
![prom_architecture_ver2](https://github.com/Chani17/ToryAba-Server/blob/main/prom_architecture_ver2.png)
앞으로 많은 센터들에서 PROM을 사용한다고 가정했을 때의 architecture다.

센터 교육 특성상, 주로 아이들 수업 시간인 9:00 ~ 18:00 사이에 가장 많이 사용할 것으로 예상해 scale-up 보다는 scale-out의 방법이 더 비용적으로 절감을 할 수 있을 것이라고 판단했다.

앞단에 Load Balancer를 배치해 부하를 분산하고, instance의 auto-scaling을 이용해 운영을 고려한 architecture다.


<br><br>
## 🗒️ PROM API Specification


