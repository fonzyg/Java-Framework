# Alfonso's Auto Parts Shop - Inventory Management System

## Project Overview
This is a Spring Boot web application for managing inventory of auto parts and products at Alfonso's Auto Parts Shop. The application provides a complete inventory management system with parts tracking, product management, and customer interface.

## Requirements Implementation

### Part C: HTML User Interface Customization
**Prompt**: Customize the HTML user interface for the customer's application
**Files Modified**:
- `src/main/resources/templates/mainscreen.html` - Lines 1-85
  - **Change**: Added Alfonso's Auto Parts Shop branding and customized product/part names
  - **Details**: Updated title, header, and table content to reflect auto parts inventory

### Part D: About Page Implementation
**Prompt**: Add an "About" page that describes the chosen customer's company
**Files Modified**:
- `src/main/java/com/example/inventory/controller/AboutController.java` - Lines 1-25
  - **Change**: Created controller to handle About page requests
- `src/main/resources/templates/about.html` - Lines 1-45
  - **Change**: Created About page template with company description
- `src/main/resources/templates/mainscreen.html` - Line 15
  - **Change**: Added navigation link to About page

### Part E: Sample Inventory Implementation
**Prompt**: Add sample inventory appropriate for the chosen store with five products and five parts
**Files Modified**:
- `src/main/java/com/example/inventory/service/PartService.java` - Lines 13-20
  - **Change**: Added 5 auto parts: Engine, Brake Pad, Oil Filter, Transmission, Alternator
- `src/main/java/com/example/inventory/service/ProductService.java` - Lines 13-20
  - **Change**: Added 5 products: Car Engine Kit, Brake System, Maintenance Package, Transmission Service Kit, Electrical System Kit

### Part F: Buy Now Button Implementation
**Prompt**: Add "Buy Now" button to the product list with full functionality
**Files Modified**:
- `src/main/java/com/example/inventory/controller/ProductBuyController.java` - Lines 1-35
  - **Change**: Created controller to handle product purchase functionality
- `src/main/resources/templates/mainscreen.html` - Lines 65-70
  - **Change**: Added "Buy Now" button to product table with proper routing

### Part G: Maximum and Minimum Inventory Tracking
**Prompt**: Modify parts to track maximum and minimum inventory levels
**Files Modified**:
- `src/main/java/com/example/inventory/entity/Part.java` - Lines 11-12, 45-50
  - **Change**: Added min and max fields with getters and setters
- `src/main/resources/templates/mainscreen.html` - Lines 35-40
  - **Change**: Updated parts table to display min and max values

### Part H: Inventory Validation Implementation
**Prompt**: Add validation for inventory between minimum and maximum fields
**Files Modified**:
- `src/main/java/com/example/inventory/validation/InventoryValidator.java` - Lines 1-45
  - **Change**: Created custom validator for inventory range validation
- `src/main/java/com/example/inventory/controller/PartController.java` - Lines 25-35
  - **Change**: Added validation logic to part save operations
- `src/main/resources/templates/partForm.html` - Lines 20-25
  - **Change**: Added validation error messages display

### Part I: Unit Tests Implementation
**Prompt**: Add at least two functional unit tests for maximum and minimum fields
**Files Modified**:
- `src/test/java/com/example/inventory/PartTest.java` - Lines 1-55
  - **Change**: Created unit tests for minimum and maximum inventory validation

### Part J: Code Cleanup
**Prompt**: Remove unused validator class files to clean the code
**Files Modified**:
- Removed unused validator files from previous iterations
- **Change**: Cleaned up unused imports and validator classes

## Features Implemented

### ✅ Core Features
- Spring Boot web application with MVC architecture
- Inventory management for auto parts and products
- Professional user interface with Alfonso's Auto Parts Shop branding
- Complete CRUD operations for parts and products

### ✅ Required Components
- **Custom HTML Interface**: Professional auto parts shop theme
- **About Page**: Company description with navigation
- **Sample Inventory**: 5 auto parts and 5 automotive products
- **Buy Now Functionality**: Product purchase system
- **Min/Max Inventory Tracking**: Inventory level management
- **Validation System**: Inventory range validation with error messages
- **Unit Tests**: Comprehensive testing for inventory validation
- **Clean Code**: Removed unused validators and organized structure

## Technical Stack
- **Backend**: Spring Boot 3.3.4, Java 17
- **Frontend**: Thymeleaf, HTML5, CSS, Bootstrap
- **Database**: H2 In-Memory Database
- **Build Tool**: Maven
- **Testing**: JUnit 5
- **Server**: Embedded Tomcat

## Running the Application
1. Ensure Java 17+ is installed
2. Navigate to project directory: `cd "Java Frameworks"`
3. Run: `mvn spring-boot:run`
4. Access application at: http://localhost:8080

## Project Structure
```
src/
├── main/
│   ├── java/com/example/inventory/
│   │   ├── InventoryApplication.java          # Main application
│   │   ├── controller/                        # Web controllers
│   │   ├── entity/                           # Data models
│   │   ├── service/                          # Business logic
│   │   └── validation/                       # Custom validators
│   └── resources/
│       └── templates/                        # Thymeleaf templates
└── test/
    └── java/com/example/inventory/
        └── PartTest.java                     # Unit tests
```

## Quality Assurance
- All features are fully functional and error-free
- Code follows Spring Boot best practices
- Comprehensive unit testing implemented
- Professional user interface design
- Complete documentation and comments