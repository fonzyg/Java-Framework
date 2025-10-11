# Class Diagram and Component Dependencies

## 🎯 UML Class Diagram

```
┌─────────────────────────────────────────────────────────┐
│                    InventoryApplication                  │
├─────────────────────────────────────────────────────────┤
│ @SpringBootApplication                                  │
│ + main(String[] args): void                             │
└─────────────────────────────────────────────────────────┘
                              │
                              │ starts
                              ▼
┌─────────────────────────────────────────────────────────┐
│              Spring Application Context                 │
│                   (IoC Container)                       │
└─────────────────────────────────────────────────────────┘
                              │
                    ┌─────────┼─────────┐
                    ▼         ▼         ▼
    
┌──────────────────────┐  ┌──────────────────────┐  ┌────────────────────┐
│   MainscreenController│  │     PartService      │  │   ProductService   │
├──────────────────────┤  ├──────────────────────┤  ├────────────────────┤
│ @Controller          │  │ @Service             │  │ @Service           │
│                      │  │                      │  │                    │
│ - partService        │  │ - parts: List<Part>  │  │ - products:        │
│ - productService     │  │                      │  │   List<Product>    │
│                      │  │ + findAll()          │  │                    │
│ + showMainScreen()   │  │ + findById()         │  │ + findAll()        │
│ + home()             │  │ + save()             │  │ + findById()       │
└──────────────────────┘  │ + deleteById()       │  │ + save()           │
           │              └──────────────────────┘  │ + deleteById()     │
           │                         │              └────────────────────┘
           │ uses                    │ manages                │ manages
           │                         ▼                        ▼
           │              ┌──────────────────────┐  ┌────────────────────┐
           │              │        Part          │  │      Product       │
           │              ├──────────────────────┤  ├────────────────────┤
           │              │ - id: Long           │  │ - id: Long         │
           │              │ - name: String       │  │ - name: String     │
           │              │ - price: double      │  │ - price: double    │
           │              │ - inv: int           │  │ - inv: int         │
           │              │ - min: int           │  │ - parts: Set<Part> │
           │              │ - max: int           │  │                    │
           │              │ - products:          │  │ + getId()          │
           │              │   Set<Product>       │  │ + getName()        │
           │              │                      │  │ + getPrice()       │
           │              │ + getId()            │  │ + getInv()         │
           │              │ + getName()          │  │ + getParts()       │
           │              │ + getPrice()         │◄─┤ + setParts()       │
           │              │ + getInv()           │  └────────────────────┘
           │              │ + getMin()           │
           │              │ + getMax()           │
           │              │ + getProducts()      │
           │              │ + setProducts()      │
           │              └──────────────────────┘
           │                         ▲
           │                         │ extends
           │                         │
           │              ┌──────────┴──────────┐
           │              │                     │
           │     ┌─────────────────┐   ┌─────────────────┐
           │     │   InhousePart   │   │ OutsourcedPart  │
           │     ├─────────────────┤   ├─────────────────┤
           │     │ - machineId:int │   │ - companyName:  │
           │     │                 │   │   String        │
           │     │ + getMachineId()│   │                 │
           │     │ + setMachineId()│   │ + getCompanyName│
           │     └─────────────────┘   │ + setCompanyName│
           │                           └─────────────────┘
           │
           ▼ renders
┌─────────────────────────────────────────────────────────┐
│                  mainscreen.html                        │
├─────────────────────────────────────────────────────────┤
│ Thymeleaf Template                                      │
│ • Parts inventory table                                 │
│ • Products table                                        │
│ • Action buttons (Add, Edit, Delete, Buy)              │
│ • Bootstrap styling                                     │
└─────────────────────────────────────────────────────────┘
```

## 🔄 Dependency Injection Flow

```
Spring Container Startup:
1. @SpringBootApplication scans packages
2. Creates @Service beans (PartService, ProductService)
3. Initializes sample data in service constructors
4. Creates @Controller bean (MainscreenController)
5. Injects services into controller via constructor
6. Starts embedded Tomcat server
7. Maps request handlers

Request Processing:
Browser → DispatcherServlet → MainscreenController → Services → Model → View
```

## 📊 Component Interaction Matrix

| Component | Depends On | Used By | Purpose |
|-----------|------------|---------|---------|
| InventoryApplication | Spring Boot | JVM | Application entry point |
| MainscreenController | PartService, ProductService | DispatcherServlet | Handle web requests |
| PartService | Part entity | MainscreenController | Parts business logic |
| ProductService | Product entity | MainscreenController | Products business logic |
| Part | Java Collections | PartService | Data model for parts |
| Product | Java Collections, Part | ProductService | Data model for products |
| InhousePart | Part | PartService | Specialized part type |
| OutsourcedPart | Part | PartService | Specialized part type |
| mainscreen.html | Thymeleaf, Model | Spring MVC | User interface |

## 🏗️ Layered Architecture

```
┌─────────────────────────────────────────────────────────┐
│                  Presentation Layer                     │
│  • mainscreen.html (Thymeleaf template)                │
│  • CSS styling and Bootstrap                           │
│  • JavaScript (if any)                                 │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                    Web Layer                            │
│  • MainscreenController (@Controller)                  │
│  • Request mappings (@GetMapping)                      │
│  • Model population                                    │
│  • Response handling                                   │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                   Service Layer                         │
│  • PartService (@Service)                              │
│  • ProductService (@Service)                           │
│  • Business logic and validation                       │
│  • Transaction boundaries                              │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                   Domain Layer                          │
│  • Part, Product entities                              │
│  • InhousePart, OutsourcedPart                        │
│  • Business rules and constraints                      │
│  • Entity relationships                                │
└─────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────┐
│                  Data Access Layer                      │
│  • In-memory Lists (simulated database)                │
│  • Future: JPA repositories                            │
│  • Data persistence operations                         │
└─────────────────────────────────────────────────────────┘
```

## 🌐 HTTP Request Flow

```mermaid
graph LR
    A[Browser] -->|GET /| B[DispatcherServlet]
    B --> C[MainscreenController]
    C --> D[home() method]
    D --> E[redirect:/mainscreen]
    E --> F[showMainScreen() method]
    F --> G[PartService.findAll()]
    F --> H[ProductService.findAll()]
    G --> I[Return parts list]
    H --> J[Return products list]
    I --> K[Add to Model]
    J --> K
    K --> L[Return 'mainscreen']
    L --> M[Thymeleaf resolver]
    M --> N[mainscreen.html]
    N --> O[Rendered HTML]
    O --> A
```

## 🔧 Configuration Dependencies

```
pom.xml dependencies:
├── spring-boot-starter-web
│   ├── Spring MVC
│   ├── Embedded Tomcat
│   └── JSON processing
├── spring-boot-starter-thymeleaf
│   ├── Thymeleaf engine
│   └── Template processing
├── spring-boot-starter-data-jpa
│   ├── Hibernate
│   ├── JPA
│   └── Database abstraction
└── h2
    └── In-memory database
```

This comprehensive schema shows how all components in Alfonso's Auto Parts Shop work together in a cohesive Spring Boot application architecture.