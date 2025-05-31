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
  - Cloudinary


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
# Database Configuration
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
TMN_CODE=68D9FLWZ
SECRET_KEY=
API_URL=  # <-- Bạn điền API_URL nếu có
VNP_PAY_URL=https://sandbox.vnpayment.vn/paymentv2/vpcpay.html
VNP_RETURN_URL=http://localhost:8080/api/vnpay/return
VNP_IPN_URL=https://8d7e-117-5-45-152.ngrok-free.app/api/vnpay/ipn

REDIS_HOST=redis
REDIS_PORT=6379
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


## Tính năng chính

- Quản lý sách (thêm, sửa, xóa, tìm kiếm)
- Quản lý đơn hàng
- Xác thực và phân quyền người dùng
- Giỏ hàng
- Tìm kiếm và lọc sách
- quản lý các tác giả, nhà xuất bản
- gửi thông tin đơn hàng và xác thực tài khoản qua mail
- Thống kế đơn hàng, số lượng hàng....
- Quản lý người dùng
- Thanh toan VNPAY và MOMO
- Oauth2
- Thêm và xóa ảnh lên Cloudniary

## Đóng góp

Mọi đóng góp đều được hoan nghênh! Vui lòng tạo issue hoặc pull request để đóng góp.

## Giấy phép

Dự án này được cấp phép theo giấy phép MIT - xem file [LICENSE](LICENSE) để biết thêm chi tiết. 
