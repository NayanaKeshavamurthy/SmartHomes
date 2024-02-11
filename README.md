# SmartHomes Online Retailer Project

## Overview

This servlet-based web application allows customers to place orders online from SmartHomes website. It includes functionality for store management, customer accounts, order processing, and additional features like product reviews, trending, and data analytics.

## Features

- Customer Account Management
- Order Placement, Tracking, and Cancellation
- Product Management (Add/Delete/Update)
- Accessories Display
- Warranty Options
- Credit Card Payment
- Special Discounts and Manufacturer Rebates
- Product Reviews (Stored in MongoDB)
- Trending & Data Analytics
- Inventory and Sales Reports (Accessible to Store Manager)
- Search Auto-Completion

## Technologies Used

- Java Servlets
- MySQL (for customer accounts, transactions, and order updates)
- MongoDB (for product reviews)
- JavaScript (for Auto-Completion)
- HTML/CSS

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/smarthomes.git
   cd smarthomes
   
2. Database Setup:
    Create a MySQL database for customer accounts, transactions, and order updates.
    Create a MongoDB database for product reviews.
   
3. Database Configuration:
    Update MySQLDataStoreUtilities.java with MySQL database credentials.
    Update MongoDBDataStoreUtilities.java with MongoDB database credentials.
   
4. Auto-Completion Setup:
    Place ProductCatalog.xml in the project directory.
    Ensure proper implementation in AjaxUtility.java.

5. Run the application:
    Deploy the project on a servlet container (e.g., Apache Tomcat).

## Usage

- Visit the SmartHomes website and navigate through the features.
- Explore trending products, analytics, and reports.
- Store managers can access additional features like inventory and sales reports.
