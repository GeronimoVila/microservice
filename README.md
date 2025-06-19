#  Microservices

Integrantes: Gerónimo Vila y Juan Cruz Soria

Este proyecto implementa dos microservicios desarrollados con **Spring Boot** y **PostgreSQL**:

- **Product**: gestiona productos y categorías.
- **Orders**: gestiona clientes, pedidos y envíos.

Ambos servicios están conectados:  
> *El microservicio de órdenes verifica la existencia y el stock de productos a través del microservicio de productos antes de guardar un pedido.*

---

## 📋 Requisitos

- Java 17+
- PostgreSQL (mediante Docker)
- Docker + Docker Compose
- IntelliJ
- Git

---

## 🚀 Configuración del entorno

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/GeronimoVila/microservice.git
   cd microservice
   ```

2. **Iniciar las bases de datos**
   ```bash
   docker-compose up -d
   ```

   Esto iniciará dos contenedores PostgreSQL:
   - `db-product` en el puerto 5431 (DB: `product`)
   - `db-orders` en el puerto 5432 (DB: `orders`)


3. **Ejecutar los microservicios**
   Desde tu IDE ejecutar las clases:
   - `ProductApplication.java` (puerto `8080`)
   - `OrdersApplication.java` (puerto `8084`)

---

## ✅ Microservicio: Product

📄 Swagger: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

🔐 Autenticación:
- Usuario: `user`
- Contraseña: `1234`

📦 Funcionalidades:
- Crear, obtener, modificar y eliminar productos.
- Asociar productos a categorías.
- Consultar stock y precio por producto.
- Endpoints:
  - `GET /api/products/{id}/stock`
  - `GET /api/products/{id}/price`
  - `PUT /api/products/{id}/stock` (para actualizar stock)

---

## 🛠️ Ejemplo de product

Category
```json
POST http://localhost:8080/api/categories
Authorization: Basic user / 1234

{
"name": "Tecnología",
"status": true
}
```
Product

```json
POST http://localhost:8080/api/products
Authorization: Basic user / 1234

{
"name": "Auriculares Bluetooth",
"price": 8500.0,
"description": "Auriculares con cancelación de ruido",
"stock": 10,
"status": true,
"category": {
"id": 1
}
}

```

## ✅ Microservicio: Orders

📄 Swagger: [http://localhost:8084/swagger-ui/index.html](http://localhost:8084/swagger-ui/index.html)

🔐 Autenticación:
- Usuario: `user`
- Contraseña: `1234`

🧾 Funcionalidades:
- Crear órdenes de compra con:
  - Items de producto
  - Cliente
  - Datos de envío
- Validación de stock y precio con ProductService
- Cambio de estado de órdenes (Pending, Processing, Delivered, Cancelled)

---

## 🛠️ Ejemplo de orden


Customer(Cliente)
```json
POST http://localhost:8084/api/customers
Authorization: Basic user / 1234

{
"firstName": "Juan",
"lastName": "Pérez",
"email": "juan.perez@example.com",
"phone": "123456789"
}
```

Order

```json
POST http://localhost:8084/api/orders
Authorization: Basic user / 1234
{
  "customerId": 1,
  "shippingDetails": {
    "streetAddress": "Av. Siempre Viva 742",
    "city": "Springfield",
    "state": "SJ",
    "zipCode": "5400",
    "country": "Argentina",
    "shippingMethod": "Standard",
    "shippingCost": 300.0
  },
  "orderItems": [
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}
```

🧾 Cuando se utiliza el método POST para crear una orden:

✅ Se guardan datos en la tabla orders.

✅ Se generan registros en la tabla order_items, que actúa como tabla intermedia entre Order y Product.

✅ También se guarda información en la tabla shipping_details, que contiene los datos de envío asociados a la orden.

📌 La orden solo se creará si:
- El producto existe.
- El stock es suficiente.

---

## 🔄 Comunicación entre microservicios

🛠️ Se usa **RestTemplate** para que `OrdersService` consulte a `ProductService`:

- `GET /api/products/{id}/stock`
- `GET /api/products/{id}/price`
- `PUT /api/products/{id}/stock` → actualiza stock al confirmar/cancelar orden

✔ Si el producto no existe → `ProductNotFoundException`  
✔ Si no hay stock suficiente → `InsufficientStockException`

---

## ✅ Características técnicas

- ✔ Arquitectura **MVC**
- ✔ Comunicación entre microservicios REST
- ✔ Autenticación básica (Spring Security)
- ✔ Excepciones personalizadas y controladas
- ✔ Validaciones a nivel DTO y entidad
- ✔ Documentación Swagger
- ✔ Organización por capas: Controller, Service, Repository, DTO, Mapper, Entity
- ✔ Proyecto ejecutable de forma local con Docker para bases de datos

---