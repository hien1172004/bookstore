# Book Store Web Application

Ứng dụng web quản lý cửa hàng sách được xây dựng bằng Spring Boot, MySQL và Redis.

## Công nghệ sử dụng

- **Backend:**
  - Spring Boot 3.x
  - Spring Security
  - Spring Data JPA
  - MySQL 8.0
  - Redis 6.2
  - Maven

## Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- Docker và Docker Compose
- MySQL 8.0
- Redis 6.2

## Cài đặt và Chạy ứng dụng

### Sử dụng Docker Compose (Khuyến nghị)

1. Clone repository:
```bash
git clone https://github.com/hien1172004/bookstore.git
cd bookstore
```

2. Tạo file `.env` trong thư mục gốc với nội dung:
```
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

3. Build và chạy ứng dụng:
```bash
docker-compose up --build
```

Ứng dụng sẽ chạy tại:
- Backend API: http://localhost:8080
- MySQL: localhost:3307
- Redis: localhost:6379

### Cài đặt thủ công

1. Clone repository:
```bash
git clone https://github.com/hien1172004/bookstore.git
cd bookstore
```

2. Cài đặt và chạy MySQL:
- Tạo database tên `testdb`
- Cập nhật thông tin kết nối trong `application.properties`

3. Cài đặt và chạy Redis:
- Cài đặt Redis server
- Cập nhật thông tin kết nối trong `application.properties`

4. Build và chạy backend:
```bash
./mvnw clean package
java -jar target/api-service.jar
```


## Cấu trúc dự án

```
bookstore/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ptit/example/btlwebbook/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── BtlwebbookApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── frontend/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - Đăng nhập
- `POST /api/auth/register` - Đăng ký
- `POST /api/auth/logout` - Đăng xuất

### Books
- `GET /api/books` - Lấy danh sách sách
- `GET /api/books/{id}` - Lấy thông tin sách theo ID
- `POST /api/books` - Thêm sách mới
- `PUT /api/books/{id}` - Cập nhật thông tin sách
- `DELETE /api/books/{id}` - Xóa sách

### Orders
- `GET /api/orders` - Lấy danh sách đơn hàng
- `GET /api/orders/{id}` - Lấy thông tin đơn hàng
- `POST /api/orders` - Tạo đơn hàng mới
- `PUT /api/orders/{id}` - Cập nhật trạng thái đơn hàng

## Tính năng chính

- Quản lý sách (thêm, sửa, xóa, tìm kiếm)
- Quản lý đơn hàng
- Xác thực và phân quyền người dùng
- Giỏ hàng
- Tìm kiếm và lọc sách
- Quản lý người dùng

## Đóng góp

Mọi đóng góp đều được hoan nghênh! Vui lòng tạo issue hoặc pull request để đóng góp.

## Giấy phép

Dự án này được cấp phép theo giấy phép MIT - xem file [LICENSE](LICENSE) để biết thêm chi tiết. 