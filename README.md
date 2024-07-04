# concert-reservation
## Description

- 콘서트 예약 서비스
- 대기열 시스템을 사용하고, 예약 서비스는 작업가능한 유저만 수행할 수 있도록 대기열 시스템을 이용해 이용자수를 제한합니다.
- 사용자는 좌석예약 시에 미리 충전한 잔액을 이용합니다.
- 좌석 예약 요청시에, 결제가 이루어지지 않더라도 일정 시간동안 다른 유저가 해당 좌석에 접근할 수 없습니다.
- 다음의 기능을 제공합니다.
  - 유저 서비스 (인증 기능, 대기열 기능)
  - 에약 서비스 (날짜 조회 기능, 좌석 조회 기능, 좌석 예약 기능)
  - 결제 서비스 (잔액 조회 기능, 잔액 충전 기능, 결제 기능)
- 다수 인스턴스 및 동시성 이슈를 고려한 기능을 제공합니다. 

Milestone

![gantt (1)](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/655850c9-aee2-4ec6-b0f3-468eff2ddbee)

Architecture Sequence Diagram

![concert reservation diagram-2024-07-01-082617](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/127cd371-fa4e-4d64-8b60-88d927d04959)

User Queue Token API Usecase Sequence Diagram

![유저 대기열 토큰 API Sequence Diagram-2024-07-04-173311](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/9398ed22-268a-49ac-9096-7cb2042df34f)

Payment API Usecase Sequence Diagram

![결제 API Sequence Diagram-2024-07-04-172428](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/e0d6c55f-7071-4853-8972-255a18bc5933)

ERD

![Concert ERD](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/108b0bed-6633-4a4c-8c07-b433522cd8ab)

