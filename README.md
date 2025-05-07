## Magazine Management System

A JavaFX desktop application for managing a magazine subscription service. Users can create magazines, manage supplements, 
register paying and associate customers, and generate monthly billing reports. The system supports data persistence via serialization.

>*The original project was my final assignment for my degree module "Software Architectures".*

### Features
- Magazine Creation
- Supplement management
    - Add/edit/delete weekly supplements
    - Assign supplements to customers
- Customer management
    - Add paying customers with payment details
    - Add associate customers linked to a paying customer
    - Manage supplements per customer
- Billing & Viewing
    - View magazine, supplement and customer information
    - Generate detailed monthly invoices
- File Operations
    - Save and load subscription data using `.ser` files
    - Load preset test data

---

### How to Run
1. **Prerequisites**
  - Java 8 or higher
2. **Run Main Class**
  - Launch `Homepage.java` to start the application
3. **Test with test data**
  - Use the "Load Preset Test Data" button to prefill data for demo/testing.

---

### Data Persistence
- Data is stored in serialized `.ser` files via `FileHandler`
- Users can `Save As` new files or load previous saves

---

Designed and coded by Josie Lim

---

### Screenshots
![magsvc-home](https://github.com/user-attachments/assets/7a05e980-2206-48e9-a422-779aef89dfe7)
![magsvc-view](https://github.com/user-attachments/assets/f29725bf-8b1e-4e03-a26e-2f4f3fc702a9)
![magsvc-create](https://github.com/user-attachments/assets/52323184-7e41-4ceb-b7e8-79f1c032e919)
![magsvc-supp](https://github.com/user-attachments/assets/3daa1925-ba3d-4918-a23a-f6f2d6afc009)
![magsvc-pay](https://github.com/user-attachments/assets/e6f7448c-3907-43b8-af5c-b0044569d4e4)
![magsvc-ass](https://github.com/user-attachments/assets/5caffee0-93ad-49a4-93a1-711bb7a5e299)
