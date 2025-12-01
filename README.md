## 프로젝트 개요
Board - 여러 사용자들이 소통할 수 있는 게시판서비스

## 배포 주소
- https://www.js-board.online/

## 사용 기술

MIRO UML 제작
https://miro.com/app/board/uXjVJj6NxZ0=/
<img width="1364" height="1186" alt="image" src="https://github.com/user-attachments/assets/33e1679c-5142-4a34-a354-a892eb4118e2" />

Figma UI/UX 시안제작
https://www.figma.com/design/TMkxqLfVHCFe8fWEOnPLUQ/JavaBoard?t=ibFJRQLBiVOwBEn6-0
<img width="1499" height="586" alt="image" src="https://github.com/user-attachments/assets/ef66dbb3-dd69-4584-87d2-b29c14b6d124" />

Godaddy domain js-board.online 구매+DNS 설정

Backend
- Java 17
- Spring Boot
- Spring Security
- JWT
- postman
- Spring Data JPA
- MySQL (AWS RDS)
- Caddy (Reverse Proxy + HTTPS)
- EC2 Amazon Linux 2023
- systemd 서비스 등록 (JAR 운영)

Frontend
- React (Vite)
- React Router
- Axios
- Custom CSS
- Vercel (배포)

## 주요 기능
- 회원가입 / 로그인
- JWT
- 게시글 CRUD
- 댓글
- 조회수
- 권한 체크 (작성자만 글/댓글 수정/삭제 가능)

## 문제와 해결과정

새로고침(F5) 하면 Vercel에서 404 NOT_FOUND 페이지 출력\화면이 아예 안 뜸 -> 프론트 루트에 vercel.json 생성:
<img width="475" height="390" alt="image" src="https://github.com/user-attachments/assets/6fabdbde-baa0-41fb-b833-ec6f5740855b" />

CORS 에러 - 프론트와 백엔드 도메인이 달라서 발생 -> Spring Security + CORS에서 허용 도메인 등록:
"https://js-board.online",
"https://www.js-board.online" CORS에 등록

EC2 메모리 부족현상으로 서버가 멈추는 문제 발생
JVM Heap 메모리 제한 설정
Spring Boot 실행 시: java -Xms128m -Xmx256m -jar app.jar로 해결

EC2 배포 시 Java 버전/환경 불일치 문제 -> 모두 같은 버전으로 다운로드 통일

## 느낀점

이번 프로젝트를 통해 가장 크게 배운 것은 백엔드와 프론트엔드가 어떻게 연결되고 협업되는지에 대한 전체 흐름입니다.
API 설계, 응답 형식 정의, CORS 처리, JWT 인증 흐름 등 모든 과정이 서로 긴밀하게 맞물려야 실제 서비스가 정상적으로 동작한다는 것을 배웠습니다.
백엔드와 프론트엔드가 서로 맞물리게하는 배포 과정에서 도메인/포트, HTTPS, 라우팅, CORS 같은 부분들이
백엔드 단독으로는 해결되지 않고 프론트와 함께 조율해야 한다는 것도 배웠습니다.
또한 백엔드에서는
Controller → Service → Repository → Entity → DTO로 이어지는 실무형 Java 백엔드 구조를 직접 구현하며,
각 계층이 어떤 책임을 가지는지 명확히 이해할 수 있었습니다.
Spring Security와 JWT를 통해 인증 흐름을 설계하면서
실무 구조에서 인증/인가가 어떤 방식으로 흘러가는지도 경험했고
이 과정에서 React가 단순한 화면만드는 라이브러리가아니라 백엔드 데이터와 끊임없이 통신하며 동적으로 화면을 그려주는 프론트엔드 엔진이라는 것을 느꼇습니다.










  
