/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  erictgb
 * Created: 25 Aug 2023
 */

CREATE TABLE IF NOT EXISTS Products (
    Product_ID VARCHAR PRIMARY KEY NOT NULL,
    Name VARCHAR NOT NULL,
    Description VARCHAR NOT NULL,
    Category VARCHAR NOT NULL,
    List_Price DECIMAL(10, 2) NOT NULL,
    Quantity_In_Stock DECIMAL(10, 3) NOT NULL
);


CREATE TABLE IF NOT EXISTS Customers (
    Customer_ID INTEGER AUTO_INCREMENT(1000),
    Username VARCHAR NOT NULL PRIMARY KEY,
    First_Name VARCHAR NOT NULL,
    Surname VARCHAR NOT NULL,
    Password VARCHAR NOT NULL,
    Email_Address VARCHAR NOT NULL,
    Shipping_Address VARCHAR NOT NULL
);