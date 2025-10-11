# Alfonso's Auto Parts Shop - Project Architecture Schema

## 🏗️ Project Overview
This is a Spring Boot web application for inventory management of auto parts and products, featuring a complete MVC architecture with service layers and entity management.

---

## 📁 Project Structure Schema

```
Java Frameworks/
├── 🔧 Core Application (src/main/java/com/example/inventory/)
│   ├── 🚀 InventoryApplication.java          # Main Spring Boot entry point
│   ├── 🎮 MainscreenController.java          # Web controller for UI requests
│   ├── 📦 entity/
│   │   ├── Part.java                         # Part entity model
│   │   └── Product.java                      # Product entity model
│   └── 💼 service/
│       ├── PartService.java                  # Business logic for parts
│       └── ProductService.java               # Business logic for products
│
├── 🌐 Web Resources (src/main/resources/)
│   └── templates/
│       └── mainscreen.html                   # Thymeleaf template for UI
│
├── ⚙️ Configuration
│   └── pom.xml                              # Maven dependencies & build config
│
└── 📚 Exercise Files (PartA-PartK/)
    ├── Various part implementations
    ├── Controller examples
    ├── Form handlers
    └── Validation examples
```

---

## 🔄 Application Architecture Flow

```mermaid
graph TB
    A[Web Browser] -->|HTTP Request| B[Spring Boot Application]
    B --> C[MainscreenController]
    C --> D{Request Type}
    
    D -->|GET /| E[Redirect to /mainscreen]
    D -->|GET /mainscreen| F[Show Main Screen]
    
    F --> G[PartService]
    F --> H[ProductService]
    
    G --> I[Parts Collection<br/>In-Memory List]
    H --> J[Products Collection<br/>In-Memory List]
    
    G --> K[Part Entity Objects]
    H --> L[Product Entity Objects]
    
    K --> M[Thymeleaf Model]
    L --> M
    
    M --> N[mainscreen.html Template]
    N --> O[Rendered HTML]
    O -->|HTTP Response| A
    
    style A fill:#e1f5fe
    style B fill:#f3e5f5
    style C fill:#e8f5e8
    style G fill:#fff3e0
    style H fill:#fff3e0
    style N fill:#fce4ec
```

---

## 🏛️ MVC Architecture Pattern

```
┌─────────────────────────────────────────────────────────────┐
│                         VIEW LAYER                          │
├─────────────────────────────────────────────────────────────┤
│  📱 mainscreen.html (Thymeleaf Template)                   │
│  • Displays parts inventory table                          │
│  • Displays products table                                 │
│  • Provides navigation and action buttons                  │
│  • Uses Bootstrap/CSS for styling                          │
└─────────────────────────────────────────────────────────────┘
                               ↕️
┌─────────────────────────────────────────────────────────────┐
│                      CONTROLLER LAYER                       │
├─────────────────────────────────────────────────────────────┤
│  🎮 MainscreenController.java                              │
│  • @Controller annotation                                  │
│  • @GetMapping("/") - Home redirect                        │
│  • @GetMapping("/mainscreen") - Main page                  │
│  • Dependency injection of services                        │
│  • Model population for view                               │
└─────────────────────────────────────────────────────────────┘
                               ↕️
┌─────────────────────────────────────────────────────────────┐
│                      SERVICE LAYER                          │
├─────────────────────────────────────────────────────────────┤
│  💼 PartService.java        💼 ProductService.java         │
│  • @Service annotation      • @Service annotation          │
│  • Business logic          • Business logic               │
│  • CRUD operations         • CRUD operations              │
│  • Data validation         • Data validation              │
│  • Sample data init        • Sample data init             │
└─────────────────────────────────────────────────────────────┘
                               ↕️
┌─────────────────────────────────────────────────────────────┐
│                        MODEL LAYER                          │
├─────────────────────────────────────────────────────────────┤
│  📦 Part.java              📦 Product.java                 │
│  • Entity properties       • Entity properties             │
│  • Getters/Setters        • Getters/Setters               │
│  • Business rules         • Business rules                │
│  • Relationships          • Relationships                 │
└─────────────────────────────────────────────────────────────┘
```

---

## 🔗 Component Relationships

```
┌──────────────────────────────────────────────────────────────┐
│                    SPRING BOOT CONTAINER                     │
│                                                              │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │  PartService    │    │ ProductService  │                │
│  │  @Service       │    │  @Service       │                │
│  │  • findAll()    │    │  • findAll()    │                │
│  │  • findById()   │    │  • findById()   │                │
│  │  • save()       │    │  • save()       │                │
│  │  • deleteById() │    │  • deleteById() │                │
│  └─────────────────┘    └─────────────────┘                │
│           ↑                       ↑                        │
│           │                       │                        │
│           └───────────┬───────────┘                        │
│                       │                                    │
│            ┌─────────────────────┐                         │
│            │ MainscreenController│                         │
│            │     @Controller     │                         │
│            │ • showMainScreen()  │                         │
│            │ • home()            │                         │
│            └─────────────────────┘                         │
│                       │                                    │
│                       ↓                                    │
│            ┌─────────────────────┐                         │
│            │   Thymeleaf View    │                         │
│            │  mainscreen.html    │                         │
│            └─────────────────────┘                         │
└──────────────────────────────────────────────────────────────┘
```

---

## 📊 Data Model Schema

```
┌─────────────────────────────────────┐
│              Part Entity             │
├─────────────────────────────────────┤
│ 🔑 id: Long                         │
│ 📝 name: String                     │
│ 💰 price: double                    │
│ 📦 inv: int (current inventory)     │
│ ⬇️  min: int (minimum threshold)    │
│ ⬆️  max: int (maximum threshold)    │
│ 🔗 products: Set<Product>           │
└─────────────────────────────────────┘
                    │
                    │ Many-to-Many
                    │ Relationship
                    │
┌─────────────────────────────────────┐
│           Product Entity            │
├─────────────────────────────────────┤
│ 🔑 id: Long                         │
│ 📝 name: String                     │
│ 💰 price: double                    │
│ 📦 inv: int (current inventory)     │
│ 🔗 parts: Set<Part>                 │
└─────────────────────────────────────┘

Inheritance Hierarchy:
┌─────────────────┐
│      Part       │
│   (Base Class)  │
└─────────────────┘
         ↑
    ┌────┴────┐
    │         │
┌───────┐ ┌──────────┐
│InHouse│ │Outsourced│
│ Part  │ │   Part   │
└───────┘ └──────────┘
```

---

## 🚀 Application Startup Flow

```mermaid
sequenceDiagram
    participant JVM
    participant SpringBoot
    participant AppContext
    participant Services
    participant Controller
    participant WebServer

    JVM->>SpringBoot: main() method called
    SpringBoot->>AppContext: Initialize Application Context
    AppContext->>Services: Create @Service beans
    Services->>Services: Initialize sample data
    AppContext->>Controller: Create @Controller beans
    Controller->>Services: Inject dependencies
    SpringBoot->>WebServer: Start embedded Tomcat
    WebServer->>WebServer: Listen on port 8080
    Note over WebServer: Application Ready
```

---

## 🌊 Request-Response Flow

```mermaid
sequenceDiagram
    participant Browser
    participant Controller
    participant PartService
    participant ProductService
    participant Model
    participant Template

    Browser->>Controller: GET /mainscreen
    Controller->>PartService: findAll()
    PartService-->>Controller: List<Part>
    Controller->>ProductService: findAll()
    ProductService-->>Controller: List<Product>
    Controller->>Model: addAttribute("parts", parts)
    Controller->>Model: addAttribute("products", products)
    Controller->>Template: return "mainscreen"
    Template->>Template: Process Thymeleaf
    Template-->>Browser: Rendered HTML
```

---

## 🔧 Technology Stack Integration

```
┌─────────────────────────────────────────────────────────────┐
│                    TECHNOLOGY LAYERS                        │
├─────────────────────────────────────────────────────────────┤
│ 🌐 Presentation: HTML5 + CSS + Thymeleaf                   │
│ 🎮 Web Layer: Spring MVC (@Controller)                     │
│ 💼 Business: Spring Service (@Service)                     │
│ 📦 Domain: POJOs (Part, Product entities)                  │
│ 🗄️  Data: In-Memory Collections (List<>)                   │
│ ⚙️  Framework: Spring Boot + Auto-configuration            │
│ 🚀 Server: Embedded Tomcat                                 │
│ 🔨 Build: Maven                                            │
│ ☕ Runtime: Java 17+                                       │
└─────────────────────────────────────────────────────────────┘
```

---

## 📋 Key Features Summary

### ✅ Implemented Features:
- ✅ Spring Boot application with auto-configuration
- ✅ MVC architecture with clear separation of concerns
- ✅ Dependency injection for loose coupling
- ✅ Service layer for business logic
- ✅ Entity models with proper encapsulation
- ✅ Thymeleaf templating for dynamic HTML
- ✅ Responsive web interface
- ✅ In-memory data storage with sample data
- ✅ RESTful URL patterns
- ✅ Professional code documentation

### 🔄 Data Flow:
1. **HTTP Request** → Spring DispatcherServlet
2. **Routing** → MainscreenController
3. **Business Logic** → Service Layer (PartService, ProductService)
4. **Data Retrieval** → In-memory collections
5. **Model Population** → Spring Model
6. **View Rendering** → Thymeleaf Template Engine
7. **HTTP Response** → Rendered HTML to browser

### 🏗️ Design Patterns Used:
- **MVC (Model-View-Controller)** - Application architecture
- **Dependency Injection** - Spring IoC container
- **Service Layer** - Business logic separation
- **Template Method** - Thymeleaf rendering
- **Factory Pattern** - Spring bean creation

This schema shows how Alfonso's Auto Parts Shop is a well-structured Spring Boot application that follows industry best practices for enterprise web development.