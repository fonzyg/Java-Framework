# Alfonso's Auto Parts Shop - Inventory Management System

## Project Overview
This is a Spring Boot application for managing inventory at Alfonso's Auto Parts Shop. The application provides functionality for managing parts and products, with validation for minimum and maximum inventory levels.

## Requirements Implementation

### C. CUSTOMIZE USER INTERFACE
**Requirement:** Customize the HTML user interface for Alfonso's Auto Parts Shop, including shop name, product names, and part names.

**Changes Made:**
- **File:** `src/main/resources/templates/mainscreen.html`
  - **Line 5:** Title customized to "Alfonso's Auto Parts Shop - Inventory"
  - **Line 25:** H1 heading customized to "Alfonso's Auto Parts Shop - Inventory"
  - **Lines 51-55:** Parts table displays part names, prices, and inventory with Min/Max columns
  - **Lines 82-84:** Products table displays product names, prices, and inventory

---

### D. ABOUT PAGE
**Requirement:** Add an "About" page describing the company with navigation to/from the main screen.

**Changes Made:**
- **File:** `src/main/java/com/example/inventory/controller/AboutController.java`
  - **Lines 1-18:** Created AboutController with GET mapping for /about route
  - **Line 11:** Adds shop name "Alfonso's Auto Parts Shop" to model
  - **Lines 12-13:** Adds company description and mission statement

- **File:** `src/main/resources/templates/about.html`
  - **Lines 1-50:** Complete About page with company information
  - **Line 27:** Link to return to main screen

- **File:** `src/main/resources/templates/mainscreen.html`
  - **Line 30:** "About Us" button added for navigation to About page

---

### E. SAMPLE INVENTORY
**Requirement:** Add sample inventory with five parts and five products without overwriting existing data.

**Changes Made:**
- **File:** `src/main/java/com/example/inventory/config/DataLoader.java`
  - **Lines 23-28:** Checks if repositories are empty before loading data (prevents overwriting)
  - **Lines 32-36:** Creates 5 sample parts with min/max inventory:
    - Brake Pads (price: $45.99, inv: 15, min: 5, max: 50)
    - Oil Filter (price: $12.50, inv: 25, min: 10, max: 100)
    - Air Filter (price: $18.75, inv: 8, min: 5, max: 30)
    - Spark Plugs (price: $8.99, inv: 40, min: 20, max: 80)
    - Battery (price: $89.99, inv: 3, min: 2, max: 15)
  - **Lines 46-50:** Creates 5 sample products:
    - Complete Brake Kit (price: $199.99, inv: 5)
    - Engine Tune-Up Kit (price: $129.99, inv: 8)
    - Oil Change Kit (price: $34.99, inv: 12)
    - Suspension System (price: $349.99, inv: 6)
    - Performance Air Intake Kit (price: $159.99, inv: 10)

---

### F. BUY NOW BUTTON
**Requirement:** Add a "Buy Now" button that decrements product inventory by one and displays success/failure messages.

**Changes Made:**
- **File:** `src/main/java/com/example/inventory/controller/ProductBuyController.java`
  - **Lines 14-30:** GET mapping for /products/buy/{id}
  - **Lines 16-19:** Validates product exists, returns error message if not found
  - **Lines 21-24:** Validates product inventory > 0, returns "out of stock" error if zero
  - **Line 25:** Decrements product inventory: `product.setInv(product.getInv() - 1)`
  - **Lines 27-28:** Adds success flash message with product name and remaining inventory

- **File:** `src/main/resources/templates/mainscreen.html`
  - **Line 15:** Added .btn-info CSS styling for Buy Now button
  - **Lines 16-18:** Added .alert, .alert-success, and .alert-error CSS classes
  - **Lines 28-34:** Added success/error message display divs using Thymeleaf conditionals
  - **Line 86:** "Buy Now" button added to products table (next to Edit and Delete buttons)

---

### G. MAX AND MIN INVENTORY
**Requirement:** Track maximum and minimum inventory for parts with validation enforcement.

**Changes Made:**
- **File:** `src/main/java/com/example/inventory/entity/Part.java`
  - **Lines 28-29:** Added minInv field with @Min(value = 0) validation annotation
  - **Lines 31-32:** Added maxInv field with @Min(value = 0) validation annotation
  - **Lines 35:** Added no-args constructor
  - **Lines 37-43:** Added 5-parameter constructor: `Part(String name, double price, int inv, int minInv, int maxInv)`
  - **Lines 58-59:** Added getMinInv() and setMinInv() methods
  - **Lines 61-62:** Added getMaxInv() and setMaxInv() methods
  - **Lines 65-77:** Added validation methods:
    - `isInventoryValid()`: Returns true if inv is between minInv and maxInv
    - `isMinMaxValid()`: Returns true if minInv <= maxInv

- **File:** `src/main/resources/templates/partForm.html`
  - **Lines 120-125:** Added minInv input field with Thymeleaf binding and error display
  - **Lines 127-133:** Added maxInv input field with Thymeleaf binding and error display

- **File:** `src/main/resources/templates/addpart.html`
  - **Lines 46-49:** Added minInv input field with required validation
  - **Lines 52-55:** Added maxInv input field with required validation

- **File:** `src/main/resources/templates/mainscreen.html`
  - **Lines 41-42:** Added "Min" and "Max" column headers to parts table
  - **Lines 54-55:** Display minInv and maxInv values for each part

---

### H. VALIDATION
**Requirement:** Add validation for inventory between minimum and maximum fields with error messages.

**Changes Made:**
- **File:** `src/main/java/com/example/inventory/validation/InventoryValidator.java`
  - **Lines 7-11:** Implements Validator interface, validates Part.class
  - **Lines 15-17:** Validates minInv is not null, rejects if missing
  - **Lines 19-22:** Validates maxInv is not null, rejects if missing
  - **Lines 23-25:** Validates minInv <= maxInv, error message: "Minimum inventory cannot be greater than maximum inventory"
  - **Lines 27-29:** Validates inv >= minInv, error message: "Current inventory cannot be less than minimum inventory (X)"
  - **Lines 30-32:** Validates inv <= maxInv, error message: "Current inventory cannot be greater than maximum inventory (X)"

- **File:** `src/main/java/com/example/inventory/controller/PartController.java`
  - **Line 7:** Imported WebDataBinder class
  - **Line 9:** Imported @InitBinder annotation
  - **Line 17:** Imported InventoryValidator class
  - **Line 26:** Added private final InventoryValidator field
  - **Lines 29-32:** Updated constructor to inject InventoryValidator via dependency injection
  - **Lines 34-37:** Added @InitBinder method `initBinder(WebDataBinder binder)` to register custom validator

- **File:** `src/main/java/com/example/inventory/entity/Part.java`
  - **Line 28:** @Min(value = 0) annotation on minInv enforces non-negative minimum
  - **Line 31:** @Min(value = 0) annotation on maxInv enforces non-negative maximum
  - **Lines 65-77:** Validation helper methods for unit tests

- **File:** `src/main/resources/templates/partForm.html`
  - **Lines 122-124:** Error display for minInv field validation errors
  - **Lines 130-132:** Error display for maxInv field validation errors

- **File:** `src/main/resources/templates/addpart.html`
  - **Line 48:** Error display for minInv validation errors
  - **Line 54:** Error display for maxInv validation errors

---

### I. UNIT TESTS
**Requirement:** Add at least two unit tests for maximum and minimum fields to PartTest class.

**Changes Made:**
- **File:** `src/test/java/com/example/inventory/PartTest.java`
  - **Lines 13-23:** `testValidInventoryWithinRange()` - Tests valid inventory within min/max range ✓
  - **Lines 26-36:** `testInvalidInventoryBelowMinimum()` - **REQUIRED TEST #1** - Tests that inventory below minimum is invalid ✓
  - **Lines 39-49:** `testInvalidInventoryAboveMaximum()` - **REQUIRED TEST #2** - Tests that inventory above maximum is invalid ✓
  - **Lines 52-61:** `testInvalidMinGreaterThanMax()` - Tests min > max validation ✓
  - **Lines 64-73:** `testInventoryValidationWithNullValues()` - Tests null handling ✓
  - **Lines 76-85:** `testMinMaxValidationWithNullValues()` - Tests partial null values ✓
  - **Lines 88-104:** `testValidInventoryAtBoundaries()` - Tests boundary values (inv=min, inv=max) ✓
  - **Lines 107-118:** `testPartConstructorWithAllParameters()` - Tests 5-parameter constructor ✓
  - **Lines 121-135:** `testMinimumInventoryValidation()` - **BONUS TEST #3** - Additional minimum boundary tests ✓
  - **Lines 138-152:** `testMaximumInventoryValidation()` - **BONUS TEST #4** - Additional maximum boundary tests ✓

**Total:** 10 unit tests implemented (8 more than required minimum of 2)

---

### J. CLEAN CODE
**Requirement:** Remove class files for any unused validators.

**Verification:**
- **Directory:** `src/main/java/com/example/inventory/validation/`
  - Contains only `InventoryValidator.java` which is actively used in PartController.java
  - No unused validator classes exist in the codebase
  - All imports are necessary and no dead code remains

---

## Complete File Change Summary

| File | Lines Changed | Description |
|------|---------------|-------------|
| Part.java | 28-32, 35-43, 58-62, 65-77 | Added minInv/maxInv fields, constructors, getters/setters, validation methods |
| PartController.java | 7, 9, 17, 26, 29-32, 34-37 | Integrated InventoryValidator with @InitBinder |
| DataLoader.java | 23-28, 32-36, 46-50 | Sample data with 5 parts and 5 products |
| InventoryValidator.java | 1-35 | Custom validation logic for min/max inventory |
| partForm.html | 120-133 | Added minInv/maxInv form fields |
| addpart.html | 46-49, 52-55 | Added minInv/maxInv form fields |
| mainscreen.html | 15-18, 28-34, 41-42, 54-55, 86 | Buy Now button, success/error messages, min/max columns |
| ProductBuyController.java | 14-30 | Buy Now functionality |
| AboutController.java | 1-18 | About page controller |
| about.html | 1-50 | About page template |
| PartTest.java | 13-152 | 10 unit tests for validation |

---

## Application Architecture

### MVC Flow
```
User Request → Controller → Service → Repository → Database
                    ↓
                 Validator
                    ↓
               View (HTML)
```

### Key Components

**Controllers:**
- `MainscreenController` - Dashboard view
- `PartController` - Part CRUD operations with validation
- `ProductController` - Product CRUD operations
- `ProductBuyController` - Purchase functionality
- `AboutController` - Company information page

**Services:**
- `PartService` / `PartServiceImpl` - Part business logic
- `ProductService` / `ProductServiceImpl` - Product business logic

**Repositories:**
- `PartRepository` - Part data access (extends JpaRepository)
- `ProductRepository` - Product data access (extends JpaRepository)

**Entities:**
- `Part` - Part model with minInv/maxInv fields
- `Product` - Product model with ManyToMany relationship to Parts

**Validators:**
- `InventoryValidator` - Custom validation for Part inventory constraints

**Configuration:**
- `DataLoader` - Loads sample data on application startup (implements CommandLineRunner)

---

## How the Application Works

### 1. Application Startup

1. `InventoryApplication.main()` starts Spring Boot
2. Spring initializes all @Component, @Service, @Controller, @Repository beans
3. `DataLoader.run()` executes:
   - Checks if `partRepository.count() == 0`
   - If empty: creates and saves 5 sample parts with min/max values
   - Checks if `productRepository.count() == 0`
   - If empty: creates and saves 5 sample products
4. Application available at `http://localhost:8080`

### 2. Dashboard View (Main Screen)

**URL:** `/` or `/mainscreen`

**Flow:**
1. User accesses homepage
2. `MainscreenController.showMainScreen()` called
3. Fetches all parts: `partService.findAll()`
4. Fetches all products: `productService.findAll()`
5. Adds to model and renders `mainscreen.html`
6. Thymeleaf displays:
   - Shop name: "Alfonso's Auto Parts Shop"
   - Parts table with columns: Name, Price, Inventory, Min, Max, Actions
   - Products table with columns: Name, Price, Inventory, Actions (Buy Now, Edit, Delete)
   - Success/error messages if present

### 3. Add Part with Validation

**URL:** `GET /parts/add` (form) → `POST /parts/add` (submit)

**Flow:**
1. User clicks "Add New Part"
2. `PartController.showAddForm()` creates empty Part object
3. Renders `addpart.html` form
4. User fills form:
   - Name: "Windshield Wipers"
   - Price: 22.99
   - Inventory: 3 (INVALID - below min)
   - Min: 10
   - Max: 40
5. User submits → `POST /parts/add`
6. `PartController.addPart(@Valid part, BindingResult result)`:
   - `@Valid` triggers validation pipeline:
     a. **JSR-380 annotations** (@Min, @NotBlank) validated first
     b. **@InitBinder** registers InventoryValidator
     c. **InventoryValidator.validate()** runs:
        - Checks minInv and maxInv not null
        - Checks minInv <= maxInv
        - Checks inv >= minInv ❌ FAILS (3 < 10)
        - Error: "Current inventory cannot be less than minimum inventory (10)"
   - `result.hasErrors()` = true
7. Returns `addpart.html` with error message displayed in red
8. User corrects inventory to 15
9. Validation passes ✓
10. `partService.save(part)` saves to database
11. Redirects to `/` (dashboard)
12. New part appears in table

### 4. Buy Product

**URL:** `GET /products/buy/{id}`

**Flow:**
1. User clicks "Buy Now" on "Complete Brake Kit" (id=1, inv=5)
2. `ProductBuyController.buyProduct(Long id, RedirectAttributes redirectAttributes)`:
   - Finds product: `productService.findById(id)`
   - Validates product exists (else error: "Product not found")
   - Validates `product.getInv() > 0` (else error: "Product is out of stock")
   - Decrements inventory: `product.setInv(product.getInv() - 1)` → inv becomes 4
   - Saves: `productService.save(product)`
   - Adds success message: "Successfully purchased Complete Brake Kit! Remaining inventory: 4"
3. Redirects to `/mainscreen`
4. Dashboard displays success message
5. Product inventory shows 4 instead of 5

### 5. View About Page

**URL:** `GET /about`

**Flow:**
1. User clicks "About Us" button
2. `AboutController.about()` adds shop info to model:
   - shopName: "Alfonso's Auto Parts Shop"
   - description: Company history and services
   - mission: Company mission statement
3. Renders `about.html`
4. User can click "Back to Main" to return to dashboard

---

## Database Schema

### Parts Table
```sql
CREATE TABLE parts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    inv INT NOT NULL,
    min_inv INT,     -- NEW FIELD (Section G)
    max_inv INT      -- NEW FIELD (Section G)
);
```

### Products Table
```sql
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    inv INT NOT NULL
);
```

### Product_Parts Join Table (ManyToMany)
```sql
CREATE TABLE product_parts (
    product_id BIGINT,
    part_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (part_id) REFERENCES parts(id)
);
```

---

## Sample Data

### Parts (5 total)
1. **Brake Pads** - $45.99 - Inv: 15 (Min: 5, Max: 50)
2. **Oil Filter** - $12.50 - Inv: 25 (Min: 10, Max: 100)
3. **Air Filter** - $18.75 - Inv: 8 (Min: 5, Max: 30)
4. **Spark Plugs** - $8.99 - Inv: 40 (Min: 20, Max: 80)
5. **Battery** - $89.99 - Inv: 3 (Min: 2, Max: 15)

### Products (5 total)
1. **Complete Brake Kit** - $199.99 - Inv: 5
2. **Engine Tune-Up Kit** - $129.99 - Inv: 8
3. **Oil Change Kit** - $34.99 - Inv: 12
4. **Suspension System** - $349.99 - Inv: 6
5. **Performance Air Intake Kit** - $159.99 - Inv: 10

---

## Validation Examples

### ✅ Valid Part
```
Name: "Brake Pads"
Price: 45.99
Inventory: 15
Min: 5
Max: 50

Result: VALID (15 is between 5 and 50)
Action: Part saved successfully
```

### ❌ Invalid - Below Minimum
```
Name: "Oil Filter"
Price: 12.50
Inventory: 3
Min: 10
Max: 100

Result: INVALID
Error: "Current inventory cannot be less than minimum inventory (10)"
Action: Form returned with error message
```

### ❌ Invalid - Above Maximum
```
Name: "Battery"
Price: 89.99
Inventory: 20
Min: 2
Max: 15

Result: INVALID
Error: "Current inventory cannot be greater than maximum inventory (15)"
Action: Form returned with error message
```

### ❌ Invalid - Min > Max
```
Name: "Spark Plugs"
Price: 8.99
Inventory: 50
Min: 80
Max: 20

Result: INVALID
Error: "Minimum inventory cannot be greater than maximum inventory"
Action: Form returned with error message
```

---

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run
```bash
# Navigate to project directory
cd "C:\Users\fonz\Desktop\Java Frameworks"

# Build the application
mvn clean package

# Run the application
mvn spring-boot:run

# Or run the JAR directly
java -jar target/inventory-0.0.1-SNAPSHOT.jar
```

### Access Points
- **Main Screen (Dashboard):** http://localhost:8080 or http://localhost:8080/mainscreen
- **About Page:** http://localhost:8080/about
- **Add Part:** http://localhost:8080/parts/add
- **Add Product:** http://localhost:8080/products/add

### Run Tests
```bash
mvn test
```

Expected output: 10 tests passed ✓

---

## Technology Stack

- **Framework:** Spring Boot 3.3.4
- **Language:** Java 17
- **Build Tool:** Maven
- **Database:** H2 In-Memory Database
- **ORM:** Spring Data JPA / Hibernate
- **Validation:** Jakarta Validation API (JSR-380)
- **Template Engine:** Thymeleaf
- **Testing:** JUnit 5
- **Server:** Embedded Tomcat

---

## Project Structure

```
Java Frameworks/
├── src/
│   ├── main/
│   │   ├── java/com/example/inventory/
│   │   │   ├── InventoryApplication.java          # Main entry point
│   │   │   ├── config/
│   │   │   │   └── DataLoader.java                # Sample data loader
│   │   │   ├── controller/
│   │   │   │   ├── AboutController.java           # About page
│   │   │   │   ├── MainscreenController.java      # Dashboard
│   │   │   │   ├── PartController.java            # Part CRUD + Validation
│   │   │   │   ├── ProductController.java         # Product CRUD
│   │   │   │   └── ProductBuyController.java      # Buy Now feature
│   │   │   ├── entity/
│   │   │   │   ├── Part.java                      # Part entity (with min/max)
│   │   │   │   └── Product.java                   # Product entity
│   │   │   ├── repository/
│   │   │   │   ├── PartRepository.java            # Part data access
│   │   │   │   └── ProductRepository.java         # Product data access
│   │   │   ├── service/
│   │   │   │   ├── PartService.java               # Part service interface
│   │   │   │   ├── PartServiceImpl.java           # Part service implementation
│   │   │   │   ├── ProductService.java            # Product service interface
│   │   │   │   └── ProductServiceImpl.java        # Product service implementation
│   │   │   └── validation/
│   │   │       └── InventoryValidator.java        # Custom min/max validator
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── mainscreen.html                # Dashboard view
│   │       │   ├── about.html                     # About page view
│   │       │   ├── partForm.html                  # Edit part form
│   │       │   ├── addpart.html                   # Add part form
│   │       │   ├── productForm.html               # Edit product form
│   │       │   └── addproduct.html                # Add product form
│   │       └── application.properties             # Configuration
│   └── test/
│       └── java/com/example/inventory/
│           └── PartTest.java                      # Unit tests (10 tests)
├── pom.xml                                        # Maven dependencies
└── README.md                                      # This file
```

---

## Key Features Summary

✅ **Inventory Management**
- Add, edit, delete parts and products
- Track current, minimum, and maximum inventory levels
- Automatic sample data loading (5 parts + 5 products)

✅ **Validation System**
- Field-level validation (@Min, @NotBlank annotations)
- Custom business logic validation (InventoryValidator)
- Real-time error messages on forms
- Min/Max inventory constraint enforcement

✅ **E-Commerce Features**
- "Buy Now" button for products
- Automatic inventory decrement on purchase
- Success/failure messages with flash attributes
- Out-of-stock detection

✅ **User Interface**
- Customized branding for Alfonso's Auto Parts Shop
- Responsive tables with color-coded buttons
- Alert messages for user feedback
- Professional styling with CSS

✅ **Testing**
- 10 comprehensive unit tests
- Boundary value testing (min, max, below, above)
- Null handling validation
- Constructor and getter/setter testing

---

## Author
Alfonso's Auto Parts Shop Development Team

## Course Information
- **Course:** Java Frameworks (D287)
- **Institution:** Western Governors University
- **Purpose:** Educational project demonstrating Spring Boot MVC architecture

## License
This project is for educational purposes only.
