# SmartHomes Online Retailer Project

## Overview

This servlet-based web application allows customers to place orders online from SmartHomes website. It includes functionality for store management, customer accounts, order processing, and additional features like product reviews, trending, and data analytics.

## Features

### Product Management
   - StoreManager can add, delete, and update products to the data store.
   - Products are categorized into smart home categories such as Smart Doorbells, Smart Doorlocks, Smart Speakers, Smart Lightings, and Smart Thermostats.
   - Each product has attributes such as Name, Price, Description, and may have accessories available for purchase.

### Order Management
   - Customers can create accounts online.
   - Customers can place orders for products and select either store pickup or home delivery.
   - Salesman can create customer accounts and manage customer orders.
   - Orders are stored in the MySQL database and can be inserted, deleted, or updated.

### Checkout Process
   - Customers can add or remove items from their shopping cart.
   - During checkout, customers provide personal information, select delivery options, and make payments using credit cards.
   - Confirmation numbers and delivery/pickup dates are provided to customers.

### Reviews
   - Customers can submit product reviews.
   - Product reviews are stored in a NoSQL MongoDB database.

### Trending & Data Analytics
   - A Trending link is available on the left navigation bar.
   - Trending page displays:
      - Top five most liked products
      - Top five zip-codes where the maximum number of products sold
      - Top five most sold products regardless of rating

### Inventory and Sales Reports (For Store Manager)
   - Inventory page displays:
      - Table of all products and their availability
      - Bar chart showing product names and total number of available items
      - Tables of products on sale and with manufacturer rebates
   - Sales Report page displays:
      - Table of all products sold and number of items sold
      - Bar chart showing product names and total sales
      - Table of total daily sales transactions

### Search Auto-Completion
   - Search auto-completion feature suggests product names as users type in the search bar.
   - Product data is read from ProductCatalog.xml file, stored in a hashmap, and then stored in MySQL database.
   - All product management operations reflect changes in the hashmap and MySQL database.

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
