# 🏬 Inventory Management System API

A RESTful **Spring Boot** application that manages inventory products — including creating, updating, deleting, and tracking stock levels with automatic low-stock detection.

---

## 🚀 Features

- ➕ **Add Products** — Create new products with name, description, and stock details.  
- ✏️ **Update Products** — Modify product information and stock quantity.  
- ❌ **Delete Products** — Remove products from the inventory.  
- 📦 **Increase / Decrease Stock** — Manage product quantities dynamically.  
- ⚠️ **Low Stock Alerts** — View products whose stock quantity is below a defined threshold.  
- 📋 **Get All Products** — Fetch all available products from the database.  
- 💬 **Custom Responses** — All API responses are wrapped in a clean, consistent format.

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Backend Framework** | Spring Boot |
| **Language** | Java |
| **Database** | MySQL / H2 (for testing) |
| **Build Tool** | Maven |
| **Documentation** | Swagger / OpenAPI |
| **Version Control** | Git & GitHub |

---

## 📚 API Endpoints

| HTTP Method | Endpoint | Description |
|--------------|-----------|-------------|
| **POST** | `/api/products` | Create a new product |
| **GET** | `/api/products` | Get all products |
| **GET** | `/api/products/{id}` | Get product by ID |
| **PUT** | `/api/products/{id}` | Update product details |
| **DELETE** | `/api/products/{id}` | Delete a product |
| **POST** | `/api/products/{id}/increase?quantity=5` | Increase stock quantity |
| **POST** | `/api/products/{id}/decrease?quantity=2` | Decrease stock quantity |
| **GET** | `/api/products/low-stock` | Fetch products below stock threshold |

---

## 🧩 Example Request (JSON)

**POST /api/products**

```json
{
  "name": "Wireless Mouse",
  "description": "High performance Dell mouse",
  "stockQuantity": 10,
  "lowStockThreshold": 3
}
