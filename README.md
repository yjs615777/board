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
  
Backend
- Java 17
- Spring Boot 3
- Spring Security
- JWT
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
'''
{
  "rewrites": [
    {
      "source": "/api/:path*",
      "destination": "/api/:path*"
    },
    {
      "source": "/(.*)",
      "destination": "/index.html"
    }
  ]
}
'''




  
