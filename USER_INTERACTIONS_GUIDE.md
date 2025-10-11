# 🌐 Alfonso's Auto Parts Shop - User Interactions Guide

## 🎯 What Users Can Do With The Application

### 🏠 **Main Dashboard Interactions** (`http://localhost:8080/mainscreen`)

#### **📋 View Inventory**
- **Parts Table Display**: See all 5 auto parts with details
  - Part Name (Engine, Brake Pad, Oil Filter, Transmission, Alternator)
  - Current Price ($12.50 - $2500.00)
  - Current Inventory Count
  - Minimum Stock Level
  - Maximum Stock Level
- **Products Table Display**: See all 5 automotive products
  - Product Name (Car Engine Kit, Brake System, etc.)
  - Product Price ($89.99 - $2500.00)
  - Available Inventory

#### **🛒 Purchase Products** (E-commerce Functionality)
- **Buy Now Buttons**: Click "Buy Now" on any product
- **Real-time Stock Updates**: Inventory automatically decreases by 1
- **Purchase Confirmation**: Success message shows remaining inventory
- **Stock Validation**: Prevents purchase of out-of-stock items
- **Error Handling**: Shows "Product not found" or "Out of stock" messages

#### **🔗 Navigation Links**
- **About Us**: Navigate to company information page
- **Add New Part**: Go to part creation form

---

## 🛠️ **Parts Management Interactions** (`/parts/`)

#### **➕ Add New Parts** (`/parts/add`)
- **Form Fields**: Fill out complete part information
  - Part Name (text input)
  - Price (decimal input, must be > 0)
  - Current Inventory (integer input)
  - Minimum Stock Level (integer input)
  - Maximum Stock Level (integer input)
- **Form Validation**: Real-time business rule enforcement
- **Save Action**: Submit form to create new part
- **Cancel Option**: Return to main screen without saving

#### **✏️ Edit Existing Parts** (`/parts/edit/{id}`)
- **Pre-filled Form**: Existing part data loaded automatically
- **Modify Any Field**: Update name, price, inventory levels
- **Validation on Save**: Business rules enforced on updates
- **Save Changes**: Update part in system
- **Cancel Changes**: Return without saving modifications

#### **🗑️ Delete Parts** (`/parts/delete/{id}`)
- **Confirmation Process**: One-click deletion with feedback
- **Success Message**: Confirmation of successful deletion
- **Error Handling**: Graceful handling of non-existent parts
- **Automatic Redirect**: Return to main dashboard

---

## 🏢 **Company Information Interactions** (`/about`)

#### **📖 View Company Details**
- **Business Information**: Read about Alfonso's Auto Parts Shop
- **Service Offerings**: Learn about available automotive services
  - Engine Parts & Repair
  - Brake Systems & Maintenance
  - Electrical Components & Diagnostics
  - Transmission Parts & Service
- **Company History**: Understand business background and expertise
- **Professional Presentation**: Clean, informative company profile

#### **🔙 Navigation**
- **Back to Inventory**: Return to main dashboard
- **Seamless Navigation**: Easy movement between pages

---

## ✅ **Form Validation Interactions**

#### **Real-time Validation Feedback**
- **Price Validation**: Must be greater than zero
  - Error: "Price must be greater than zero"
- **Inventory Range Validation**: Current stock within min/max limits
  - Error: "Current inventory below minimum level"
  - Error: "Current inventory above maximum level"
- **Min/Max Logic**: Minimum cannot exceed maximum
  - Error: "Minimum inventory cannot be greater than maximum"
- **Name Validation**: Part name cannot be empty
  - Error: "Part name cannot be empty"
- **Negative Value Prevention**: Min/max cannot be negative

#### **Error Display System**
- **Field-Specific Errors**: Errors appear next to relevant fields
- **Error Highlighting**: Invalid fields visually highlighted
- **Success Messages**: Green confirmation messages for successful actions
- **Error Messages**: Red warning messages for failed actions

---

## 💻 **User Interface Interactions**

#### **📱 Responsive Design**
- **Professional Layout**: Clean, modern web interface
- **Button Interactions**: Hover effects and click feedback
- **Form Styling**: Professional input fields and buttons
- **Table Display**: Organized, readable data presentation

#### **🎨 Visual Feedback**
- **Success Alerts**: Green messages for successful operations
- **Error Alerts**: Red messages for failed operations
- **Loading States**: Smooth page transitions
- **Professional Styling**: Consistent theme throughout application

---

## 🔄 **Complete User Journey Examples**

### **Scenario 1: Customer Purchasing a Product**
1. **Visit Site**: Navigate to `http://localhost:8080`
2. **Browse Products**: View available automotive products
3. **Select Product**: Choose "Brake System" ($450.00, Inventory: 15)
4. **Click Buy Now**: Purchase the product
5. **See Confirmation**: "Successfully purchased Brake System! Remaining inventory: 14"
6. **Updated Display**: Inventory count automatically reduced

### **Scenario 2: Shop Owner Adding New Part**
1. **Access Dashboard**: View main inventory screen
2. **Click Add Part**: Navigate to part creation form
3. **Fill Form**: Enter part details
   - Name: "Spark Plug"
   - Price: $8.99
   - Inventory: 50
   - Min: 10, Max: 100
4. **Submit Form**: Save new part to inventory
5. **Validation Check**: System validates all business rules
6. **Success**: "Part 'Spark Plug' saved successfully!"
7. **Return to Dashboard**: New part appears in inventory table

### **Scenario 3: Inventory Management**
1. **Review Current Stock**: Check parts with low inventory
2. **Edit Part**: Click edit for "Engine" part
3. **Update Inventory**: Increase current stock from 10 to 15
4. **Validate Changes**: System ensures 15 is within min(5)/max(20) range
5. **Save Update**: Confirm inventory adjustment
6. **Updated Display**: New inventory level reflected immediately

---

## 🎯 **Business Process Support**

#### **📊 Inventory Tracking**
- **Real-time Updates**: Instant inventory changes on purchases
- **Stock Level Monitoring**: Visual display of current vs. min/max levels
- **Business Rule Enforcement**: Prevents invalid inventory states

#### **💰 Sales Processing**
- **Purchase Workflow**: Complete buy-now functionality
- **Inventory Integration**: Automatic stock deduction
- **Customer Feedback**: Clear purchase confirmation messages

#### **📈 Business Management**
- **Part Lifecycle**: Complete CRUD operations for inventory items
- **Data Integrity**: Validation ensures consistent business data
- **Professional Interface**: Suitable for customer-facing operations

---

## 🌟 **Key Interactive Features Summary**

### **What Users Can Do:**
✅ **Browse** complete parts and products inventory
✅ **Purchase** products with real-time inventory updates
✅ **Add** new parts to the inventory system
✅ **Edit** existing part information and stock levels
✅ **Delete** parts from the inventory
✅ **Validate** all data entry with business rules
✅ **Navigate** seamlessly between all pages
✅ **View** professional company information
✅ **Experience** responsive, professional web interface

### **System Responses:**
✅ **Real-time feedback** on all user actions
✅ **Validation messages** for data integrity
✅ **Success confirmations** for completed operations
✅ **Error handling** for invalid operations
✅ **Professional presentation** throughout the application

**Alfonso's Auto Parts Shop provides a complete, interactive inventory management and e-commerce experience suitable for both business operations and customer interactions!** 🚀