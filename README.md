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
<details>
  <summary><b>회원<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Request Header</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>원장님 회원가입</td>
        <td>POST /members/join</td>
        <td></td>
        <td>- id: String<br> - password: String<br> - name: String<br> - email: String<br> - phone: String</td>
        <td>- result: boolean</td>
      </tr>
      <tr>
        <td>선생님 회원가입</td>
        <td>POST /members/therapist/join</td>
        <td></td>
        <td>- id: String<br> - password: String<br> - name: String<br> - email: String<br> - phone: String<br> - centerId: Long</td>
        <td>- result: boolean</td>
      </tr>
      <tr>
        <td>로그인</td>
        <td>POST /members/login</td>
        <td></td>
        <td>- id: String<br> - password: String</td>
        <td>- name: String<br> - token: String</td>
      </tr>
      <tr>
        <td>token 유효성 검증</td>
        <td>POST /valid/token</td>
        <td>- headers: Map<String, String></td>
        <td></td>
        <td>- name: String<br> - result: boolean</td>
      </tr>
      <tr>
        <td>ID 찾기</td>
        <td>POST /members/find/id</td>
        <td></td>
        <td>- name: String<br> - phone: String<br> - email: String</td>
        <td>- id: String</td>
      </tr>
      <tr>
        <td>비밀번호 찾기</td>
        <td>POST /members/find/password</td>
        <td></td>
        <td>- id: String<br> - name: String<br> - phone: String</td>
        <td>- password: String</td>
      </tr>
      <tr>
        <td>비밀번호 변경</td>
        <td>POST /members/password</td>
        <td></td>
        <td>- beforePassword: String<br> - afterPassword: String<br></td>
        <td>- result: boolean</td>
      </tr>
      <tr>
        <td>프로필 수정</td>
        <td>PATCH /edit/profile</td>
        <td></td>
        <td>- name: String<br> - fote: String<br> - qulification: List<String></td>
        <td>- name: String<br> - forte: String<br> - qualification: List<String><br> - centerName: String</td>
      </tr>
      <tr>
        <td>프로필 조회</td>
        <td>GET /profile</td>
        <td></td>
        <td></td>
        <td>- name: String<br> - forte: String<br> - qualification: List<String><br> - centerName: String</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>센터<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>센터 추가</td>
        <td>POST /center</td>
        <td></td>
        <td>- name: String</td>
        <td>- centerId: Long<br> - centerName: String<br> - director: Director</td>
      </tr>
      <tr>
        <td>센터 수정</td>
        <td>PATCH /center/{centerId}</td>
        <td>- centerId: Long</td>
        <td>- name: String</td>
        <td>- centerId: Long<br> - centerName: String<br> - director: Director</td>
      </tr>
      <tr>
        <td>센터 목록 조회</td>
        <td>GET /centers</td>
        <td></td>
        <td></td>
        <td>- centerList: List<Center></td>
      </tr>
      <tr>
        <td>센터 삭제</td>
        <td>DELETE /center/{centerId}</td>
        <td>- cneterId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>반<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>반 추가</td>
        <td>POST /{centerId}/classes</td>
        <td>- centerId: Long</td>
        <td>- name: String</td>
        <td>- classId: Long<br> - className: String<br> - center: Center</td>
      </tr>
      <tr>
        <td>반 수정</td>
        <td>PATCH /classes/{classId}</td>
        <td>- classId: Long</td>
        <td>- name: String</td>
        <td>- classId: Long<br> - className: String<br> - center: Center</td>
      </tr>
      <tr>
        <td>반 목록 조회</td>
        <td>GET /{centerId}/classes</td>
        <td>- centerId: Long</td>
        <td></td>
        <td>- classList: List<Class></td>
      </tr>
      <tr>
        <td>반 삭제</td>
        <td>DELETE /classes/{classId}</td>
        <td>- classId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>학생<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>학생 추가</td>
        <td>POST /{classId}/students</td>
        <td>- classId: Long</td>
        <td>- name: String<br> - birth: String<br> - etc: String<br> - parentName: String<br> - startDate: String</td>
        <td>- studentId: Long<br> - studentName: String<br> - birth: String<br> - etc: String<br> - parentName: Stirng<br> - startDate: String<br> - endDate: String<br> - registerDate: String<br> - class: Class</td>
      </tr>
      <tr>
        <td>학생 정보 수정</td>
        <td>PATCH /students/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- name: String<br> - birth: String<br> - etc: String<br> - parentName: String<br> - startDate: String<br> - endDate: String<br> - registerDate: String</td>
        <td>- studentId: Long<br> - studentName: String<br> - birth: String<br> - etc: String<br> - parentName: Stirng<br> - startDate: String<br> - endDate: String<br> - registerDate: String<br> - class: Class</td>
      </tr>
      <tr>
        <td>시작 날짜 변경</td>
        <td>PATCH /students/{studentId}/startDate</td>
        <td>- studentId: Long</td>
        <td>- date: String</td>
        <td>- studentId: Long<br> - studentName: String<br> - birth: String<br> - etc: String<br> - parentName: Stirng<br> - startDate: String<br> - endDate: String<br> - registerDate: String<br> - class: Class</td>
      </tr>
      <tr>
        <td>종료 날짜 변경</td>
        <td>PATCH /students/{studentId}/endDate</td>
        <td>- studentId: Long</td>
        <td>- date: String</td>
        <td>- studentId: Long<br> - studentName: String<br> - birth: String<br> - etc: String<br> - parentName: Stirng<br> - startDate: String<br> - endDate: String<br> - registerDate: String<br> - class: Class</td>
      </tr>
      <tr>
        <td>학생 목록 조회</td>
        <td>GET /{classId}/students</td>
        <td>- classId: Long</td>
        <td></td>
        <td>- studentList: List<Student></td>
      </tr>
          <tr>
        <td>학생 삭제</td>
        <td>GET /students/{studentId}</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>발달 영역(Domain)<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>발달 영역 추가</td>
        <td>POST /domains/{centerId}</td>
        <td>- centerId: Long</td>
        <td>- name: String<br> - type: String<br> - contents: String</td>
        <td></td>
      </tr>
      <tr>
        <td>발달 영역 정보 수정</td>
        <td>PATCH /domains/{domainId}</td>
        <td>- domainId: Long</td>
        <td>- name: String</td>
        <td>- id: Long<br> - templateNum: int<br> - name: String<br> - registerDate: String<br> - centerId: Long</td>
      </tr>
      <tr>
        <td>발달 영역 리스트 가져오기</td>
        <td>GET /domains/{domainId}</td>
        <td>- domainId: Long</td>
        <td></td>
        <td>- domainList<Domain></td>
      </tr>
      <tr>
        <td>발달 영역 삭제</td>
        <td>DELETE /domains/{domainId}</td>
        <td>- domainId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>장기 목표(LTO)<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>LTO 추가</td>
        <td>POST /{domainId}/ltos/{studentId}</td>
        <td>- domainId: Long<br> - studentId: Long</td>
        <td>- name: String<br> - contents: String<br> - developType: List<String></td>
        <td>- ltoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - developType: List<String><br> - achieveDate: String<br> - registerDate: String<br> - delYN: String<br> - domainId: Long<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>LTO 상태 수정(stop, in progress)</td>
        <td>PATCH /ltos/{ltoId}/status</td>
        <td>- ltoId: Long</td>
        <td>- status: String</td>
        <td>- ltoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - developType: List<String><br> - achieveDate: String<br> - registerDate: String<br> - delYN: String<br> - domainId: Long<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>LTO 상태 수정(hit)</td>
        <td>PATCH /ltos/{ltoId}/hit/status</td>
        <td>- ltoId: Long</td>
        <td>- status: String</td>
        <td>- ltoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - developType: List<String><br> - achieveDate: String<br> - registerDate: String<br> - delYN: String<br> - domainId: Long<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>LTO 내용 수정</td>
        <td>PATCH /ltos/{ltoId}</td>
        <td>- ltoId: Long</td>
        <td>- name: String<br> - contents: String<br> - developType: List<String></td>
        <td>- ltoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - developType: List<String><br> - achieveDate: String<br> - registerDate: String<br> - delYN: String<br> - domainId: Long<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>LTO 발달 유형 종류 수정</td>
        <td>PATCH /ltos/{ltoId}/develop/add</td>
        <td>- ltoId: Long</td>
        <td>- content: String</td>
        <td>- developType: List<String></td>
      </tr>
      <tr>
        <td>LTO 발달 유형 종류 삭제</td>
        <td>PATCH /ltos/{ltoId}/develop/remove</td>
        <td>- ltoId: Long</td>
        <td>- content: String</td>
        <td>- developType: List<String></td>
      </tr>
      <tr>
        <td>LTO 목록 가져오기</td>
        <td>GET /{domainId}/{studentId}/ltos</td>
        <td>- domainId: Long<br> - studentId: Long</td>
        <td></td>
        <td>- ltoList: List<LtoResponse></td>
      </tr>
      <tr>
        <td>LTO 그래프 가져오기</td>
        <td>GET /ltos/{ltoId}/graphs</td>
        <td>- ltoId: Long</td>
        <td></td>
        <td>- response: List<LtoGraphResponse></td>
      </tr>
      <tr>
        <td>LTO 삭제하기</td>
        <td>DELETE /ltos/{ltoId}</td>
        <td>- ltoId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>단기 목표(STO)<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>STO 추가</td>
        <td>POST /{ltoId}/stos</td>
        <td>- ltoId: Long</td>
        <td>- name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - registrant: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>STO 상태 변경(stop, in progress)</td>
        <td>PATCH /stos/{stoId}/status</td>
        <td>- stoId: Long</td>
        <td>- status: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>STO 상태 변경(hit)</td>
        <td>PATCH /stos/{stoId}/hit/status</td>
        <td>- stoId: Long</td>
        <td>- status: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>STO 내용 변경</td>
        <td>PATCH /stos/{stoId}</td>
        <td>- stoId: Long</td>
        <td>- name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br></td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>준거 도달(hit) 회차 업데이트</td>
        <td>PATCH /stos/{stoId}/hit/round</td>
        <td>- stoId: Long</td>
        <td>- plusRate: Float<br> - minusRate: Float<br> - status: String<br> - registrant: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>회차 업데이트</td>
        <td>PATCH /stos/{stoId}/round</td>
        <td>- stoId: Long</td>
        <td>- plusRate: Float<br> - minusRate: Float<br> - status: String<br> - registrant: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>스트레스 상태 선택</td>
        <td>PATCH /stos/{stoId}/stress</td>
        <td>- stoId: Long</td>
        <td>- content: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>집중도 선택</td>
        <td>PATCH /stos/{stoId}/concentration</td>
        <td>- stoId: Long</td>
        <td>content: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>특이사항 입력</td>
        <td>PATCH /stos/{stoId}/significant</td>
        <td>- stoId: Long</td>
        <td>- content: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>돌발 행동 선택</td>
        <td>PATCH /stos/{stoId}/selection/lc</td>
        <td>- stoId: Long</td>
        <td>- content: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>돌발 행동 선텍 취소</td>
        <td>PATCH /stos/{stoId}/removal/lc</td>
        <td>- stoId: Long</td>
        <td>- content: String</td>
        <td>- stoId: Long<br> - templateNum: int<br> - status: String<br> - name: String<br> - contents: String<br> - count: int<br> - goal: int<br> - goalType: String<br> - goalAmount: int<br> - achievementOrNot: String<br> - urgeContent: String<br> - enforceContent: String<br> - memo: String<br> - round: int<br> - hitGoalDate: String<br> - registerDate: String<br> - delYN: String<br> - pointList: List<Point><br> - stressStatus: String<br> - concentration: String<br> - significant: String<br> - looseCannonList: List<String><br> - ltoId: Long</td>
      </tr>
      <tr>
        <td>학생 별 STO 목록 가져오기</td>
        <td>GET /{studentId}/stos</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- stoList: List<StoResponse></td>
      </tr>
      <tr>
        <td>LTO 별 STO 목록 가져오기</td>
        <td>GET /ltos/{ltoId}/stos</td>
        <td>- ltoId: Long</td>
        <td></td>
        <td>- stoList: List<StoResponse></td>
      </tr>
      <tr>
        <td>STO 삭제</td>
        <td>DELETE /stos/{stoId}</td>
        <td>- stoId: Long</td>
        <td></td>
        <td>- result: boolean</td>
      </tr>  
    </table>
</details>
<details>
  <summary><b>돌발 행동<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>돌발 행동 추가</td>
        <td>POST /lc</td>
        <td></td>
        <td>- name: String</td>
        <td></td>
      </tr>
      <tr>
        <td>돌발 행동 목록 가져오기</td>
        <td>GET /lc</td>
        <td></td>
        <td></td>
        <td></td>
      </tr>
    </table>
</details>
<details>
  <summary><b>Point<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>point 저장</td>
        <td>POST /stos/{stoId}/points</td>
        <td>- stoId: Long</td>
        <td>- result: String<br> - registrant: String</td>
        <td></td>
      </tr>
      <tr>
        <td>point 수정</td>
        <td>PATCH /stos/{stoId}/points</td>
        <td>- stoId: Long</td>
        <td>- points: List<String><br> - registrant: String</td>
        <td></td>
      </tr>
      <tr>
        <td>point 목록 가져오기</td>
        <td>GET /stos/{stoId}/points</td>
        <td>- stoId: Long</td>
        <td></td>
        <td>- pointList: List<String></td>
      </tr>
      <tr>
        <td>point 삭제</td>
        <td>DELETE /stos/{stoId}/points</td>
        <td>- stoId: Long</td>
        <td></td>
        <td></td>
      </tr>
    </table>
</details>
<details>
  <summary><b>오늘의 치료 계획(Todo)<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Request Param</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>Todo 추가</td>
        <td>POST /todos/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- stoId: Long</td>
        <td></td>
        <td>- id: Long<br> - date: LocalDate<br> - stoList: List<Long><br> - teacher: String<br> - student: Student</td>
      </tr>
      <tr>
        <td>Todo 수정</td>
        <td>PATCH /todos/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- stoList: List<Long></td>
        <td></td>
        <td>- id: Long<br> - date: LocalDate<br> - stoList: List<Long><br> - teacher: String<br> - student: Student</td>
      </tr>
      <tr>
        <td>Todo 조회</td>
        <td>GET /todos/{studentId}</td>
        <td>- studentId: Long</td>
        <td></td>
        <td></td>
        <td>- id: Long<br> - date: String<br> - stoList: List<Long><br> - teacher: String<br> - student: Student</td>
      </tr>
      <tr>
        <td>Todo 삭제</td>
        <td>DELETE /todos/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- stoId: Long</td>
        <td></td>
        <td></td>
      </tr>
      <tr>
        <td>최근 중재 목록 with 날짜</td>
        <td>GET /todos/{studentId}/recent</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- startDate: String<br> - endDate: String</td>
        <td>- date: String<br> - sto: List<String><br> - stoStatus: List<String><br> - lto: List<String><br> - teacher: String</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>알림장<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Request Param</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>comment 업데이트</td>
        <td>PATCH /notices/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- comment: String</td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- id: Long<br> - year: String<br> - month: int<br> - date: String<br> - day: String<br> - comment: String<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>년, 월에 대한 알림장 목록 가져오기</td>
        <td>GET /notices/{studentId}/dateList</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int</td>
        <td>- response: List<DateResponse></td>
      </tr>
      <tr>
        <td>해당 날짜에 대한 알림장 가져오기</td>
        <td>GET /notcies/{studentId}</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- id: Long<br> - year: String<br> - month: int<br> - date: String<br> - day: String<br> - comment: String<br> - studentId: Long</td>
      </tr>
      <tr>
        <td>생성된 알림장 날짜 목록 가져오기</td>
        <td>GET /notices/{studentId}/dates</td>
        <td>- studentId: Long</td>
        <td></td>
        <td></td>
        <td>- response: List<NoticesDatesResponse></td>
      </tr>
      <tr>
        <td>월간 보고서 가져오기</td>
        <td>GET /notices/{studentId}/monthly</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int</td>
        <td>- response: List<NoticeResponse></td>
      </tr>
      <tr>
        <td>알림장 자동 멘트 생성</td>
        <td>GET /notcies/{studentId}/auto/comment</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- comment: String</td>
      </tr>
      <tr>
        <td>WebView Report 보여주기</td>
        <td>GET /notices/{studentId}/reports</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- url: String</td>
      </tr>
    </table>
</details>
<details>
  <summary><b>세부 알림장<br></b></summary>
    <table>
      <tr>
        <th scope="col">기능</th>
        <th scope="col">Endpoint</th>
        <th scope="col">Path Variable</th>
        <th scope="col">Request Payload</th>
        <th scope="col">Request Param</th>
        <th scope="col">Response Data</th>
      </tr>
      <tr>
        <td>세부 알림장 추가</td>
        <td>POST /details/{studentId}</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String<br> - stoId: Long</td>
        <td></td>
      </tr>
      <tr>
        <td>comment 수정</td>
        <td>PATCH /details/{studentId}</td>
        <td>- studentId: Long</td>
        <td>- comment: String</td>
        <td>- year: String<br> - month: int<br> - date: String<br> - stoId: Long</td>
        <td>- id: Long<br> - comment: String<br> - ltoId: Long<br> - stoId: Set<Long><br> - noticeId: Long</td>
      </tr>
      <tr>
        <td>해당 날짜별 세부 알림장 목록 가져오기</td>
        <td>GET /details/{studentId}</td>
        <td>- studentId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- response: List<DetailObjectResponse></td>
      </tr>
      <tr>
        <td>세부 알림장 자동 멘트 생성</td>
        <td>GET /details/{studentId}/{ltoId}/auto/comment</td>
        <td>- studentId: Long<br> - ltoId: Long</td>
        <td></td>
        <td>- year: String<br> - month: int<br> - date: String</td>
        <td>- comment: String</td>
      </tr>
    </table>
</details>
