# Book Store Web Application

Ứng dụng web quản lý cửa hàng sách được xây dựng bằng Spring Boot, MySQL và Redis.

## Công nghệ sử dụng

- **Backend:**
  - Spring Boot 3.x
  - Spring Security
  - Spring Data JPA
  - Spirng boot cache
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
# OAuth2 Configuration
GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=

# Facebook OAuth Configuration (nếu cần)
FACEBOOK_CLIENT_ID=
FACEBOOK_CLIENT_SECRET=

# JWT Configuration
JWT_SECRET_KEY=
JWT_REFRESH_KEY=
JWT_RESET_KEY=
JWT_VERIFY_KEY=

# Cloudinary
CLOUD_NAME=
CLOUD_KEY=
CLOUD_SECRET=

# VNPAY Configuration
TMN_CODE=
SECRET_KEY=
API_URL= 
VNP_PAY_URL=
VNP_RETURN_URL=
VNP_IPN_URL=

REDIS_HOST=redis
REDIS_PORT=6379
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
- Cập nhật thông tin kết nối trong `application.yml`

3. Cài đặt và chạy Redis:
- Cài đặt Redis server
- Cập nhật thông tin kết nối trong `application.yml`

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

## Tính năng chính
- Quản lý sách (thêm, sửa, xóa, tìm kiếm)
- Quản lý đơn hàng
- Xác thực và phân quyền người dùng
- Giỏ hàng
- Tìm kiếm và lọc sách
- Quản lý nhân viên
- Quản lý người dùng
- Quản lý tiến trình đơn hàng
- Quên mật khẩu và xác thực tài khoản qua gmail
- Gửi thông tin đơn hàng về mail
- Quản lý các thông tin liên quan về sách tác giả, thể loại, nhà xuất bản
- Thống kê số liệu 

## Đóng góp

Mọi đóng góp đều được hoan nghênh! Vui lòng tạo issue hoặc pull request để đóng góp.

## Giấy phép

Dự án này được cấp phép theo giấy phép MIT - xem file [LICENSE](LICENSE) để biết thêm chi tiết. 
