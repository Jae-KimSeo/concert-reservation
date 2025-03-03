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

![Concert ERD (1)](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/fc546906-83ff-4582-ba85-a51aca42723c)

User Queue Token API Usecase Sequence Diagram

```
유저 대기열 토큰 기능 API
Method : POST
Endpoint : api/v1/users/queueEntity
Request : string UUID
Response : 
	string jwt
	int waitingUserNum
	
	Http Status Code :
	201 : 토큰 생성 완료됨 (대기열토큰)
	202 : 처리 완료, 토큰 생성 x
	400 : Bad Request
	401 : Unauthorized (유저 인증 실패할 경우)

```

![유저 대기열 토큰 API Sequence Diagram-2024-07-04-173311](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/9398ed22-268a-49ac-9096-7cb2042df34f)

Payment API Usecase Sequence Diagram

```
결제 API 
Method : POST 
Endpoint : api/v1/accounts/payments
Request :
	Header :
		Authorization : Bearer <JWT>
	string scheduleId
	string seatId
	long price

Response : 
	string billId
	long price
	string buyerId
	string scheduleId
	string seatId
	timestamp createdAt
	
	Https Status Code :
	201 : 결제 완료됨 (bills 생성)
	400 : Bad Request (적절하지 않은 scheduleId or seatId)
	401 : Unauthorized (유저 검증 실패)
	402 : PaymentRequired (잔액 부족)
	403 : Forbidden (대기열 토큰 검증 실패로 API 접근 불가 혹은 결제 예약 유효 여부 확인 실패)
```

![결제 API Sequence Diagram-2024-07-04-172428](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/e0d6c55f-7071-4853-8972-255a18bc5933)

ERD

![Concert ERD](https://github.com/Jae-KimSeo/concert-reservation/assets/52844717/108b0bed-6633-4a4c-8c07-b433522cd8ab)

## API Specification 
```
예약 가능 날짜 API 
Method : GET
Endpoint : api/v1/reservations/dates/{concertId}
Request :
	Header :
		Authorization : Bearer <JWT>
	
Response :
	string[] scheduleIds
	string[] scheduleDates
	
	Https Status Code :
	200 : 예약 가능 날짜 반환 
	400 : Bad Request (적절하지 않은 concertId)
	401 : Unauthorized (유저 검증 실패)
	403 : Forbidden (대기열 토큰 검증 실패로 API 접근 불가)
```
```
예약 가능 좌석 API 
Method : GET
Endpoint : api/v1/reservations/seats/{scheduleId}
	Header : 
		Authorization : Bearer <JWT>
	
Response :
	string[] seatIds
	string[] seatNos
	
	Https Status Code :
	200 : 예약 가능 좌석 반환 
	400 : Bad Request (적절하지 않은 schduleId)
	401 : Unauthorized (유저 검증 실패)
	403 : Forbidden (대기열 토큰 검증 실패로 API 접근 불가)
```
```
좌석 예약 요청 API 
Method : POST
Endpoint : api/v1/reservations/seats
Request : 
	Header : 
		Authorization : Bearer <JWT>
	string scheduleId
	string seatId
	
Response :
	bool isReserved
	timestamp reservedAt
	timestamp holdDeadline
	string seatNo
	long price
	
	Http Status Code :
	200 : 좌석 예약 완료됨
	400 : Bad Request (적절하지 않은 scheduleId or seatId)
	401 : Unauthorized (유저 검증 실패)
	403 : Forbidden (대기열 토큰 검증 실패로 API 접근 불가)
```
```
잔액 충전/조회 API 
Method : PATCH
Endpoint : api/v1/accounts/balances
	string userId
	long balanceAdjustment

Response :
	long balance

	Https Status Code : 
	200 : 잔액 충전 및 조회 완료
	400 : Bad Request (적절하지 않은 잔액 액수)
	401 : Unauthorized (유저 검증 실패)
```
