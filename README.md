## 프로젝트 개요
Board - 여러 사용자들이 소통할 수 있는 게시판서비스

## 프로젝트 구조
com.example.board
 ├── common
 │    └── ApiResponse
 │
 ├── config
 │    └── SecurityConfig
 │
 ├── controller
 │    ├── CommentController
 │    ├── PostController
 │    └── UserController
 │
 ├── domain
 │    ├── Comment
 │    ├── Post
 │    └── User
 │
 ├── dto
 │    ├── request
 │    │     ├── CommentCreateRequest
 │    │     ├── PostCreateRequest
 │    │     ├── PostUpdateRequest
 │    │     ├── UserCreateRequest
 │    │     └── UserLoginRequest
 │    │
 │    └── response
 │          ├── CommentResponse
 │          ├── LoginResponse
 │          ├── PostDetailResponse
 │          ├── PostResponse
 │          └── UserResponse
 │
 ├── exception
 │    ├── custom
 │    │     ├── PostNotFoundException
 │    │     ├── UnauthorizedException
 │    │     └── UserNotFoundException
 │    └── GlobalExceptionHandler
 │
 ├── repository
 │    ├── CommentRepository
 │    ├── PostRepository
 │    └── UserRepository
 │
 ├── security
 │    ├── JwtFilter
 │    └── JwtUtil
 │
 ├── service
 │    ├── CommentService
 │    ├── PostService
 │    └── UserService
 │
 ├── BoardApplication
 │
 └── resources
      ├── static
      ├── templates
      ├── application.properties
      ├── application-dev.properties
      └── application-prod.properties
