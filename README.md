# 💰 Daily Finance Website  End-to-End Automation Suite
# Project Overview 
- This project automates a comprehensive user journey and admin operations on the Daily Finance website using Selenium WebDriver, TestNG, and Java. The test suite validates critical workflows such as registration, email verification, password reset, item management, and admin dashboard validation.

# Website Link
https://dailyfinance.roadtocareer.net/
# Test Case Link
https://docs.google.com/spreadsheets/d/1Za8MokrvDA0TzmkvWXATar8bqi4sNMk33gbUZaY3Pik/edit?usp=sharing

## ✅ Features Covered
# 1️⃣ User Workflow Automation
## User Registration

- Registers a new user using dynamic email (e.g. gmailuser+<random>@gmail.com).

- Asserts that a “Congratulations” email is received (Gmail integration).

- Password Reset

- Clicks on "Reset Password"

- Negative Test Case 1: Enters an invalid email → Assert appropriate error message.

- Negative Test Case 2: Leaves the email field blank → Assert validation error.

- Inputs a valid registered email and requests a password reset.

- Retrieves password reset email from Gmail and sets a new password.

- Logs in using the new password to assert login success.

## Item Management

- Adds 2 items:

- One with all fields filled

- One with only mandatory fields

- Asserts both items appear in the item list.

## User Profile Update

- Updates the user’s email with a new Gmail address.

- Logs out and logs in using the new email → Assert login success.

- Tries to log in using old email → Assert login failure.

# 2️⃣ Admin Workflow Automation
## Admin Login

- Login using admin credentials provided securely via terminal input.

- User Management

- Searches the updated user email in the admin dashboard.

- Asserts the updated user email is displayed correctly.

- Bulk User Registration

- Registers 3 users using data from a CSV file.

- Admin logs in and retrieves the complete user list from the dashboard.

- Writes all user data to a local text file.
# Allure Report Overview
![allurefinal1](https://github.com/user-attachments/assets/81344c44-0598-4194-b706-7b19b47d26d5)
# Allure Report Behaviors
![allurefinal2](https://github.com/user-attachments/assets/9e5e6120-7cee-4556-97b0-9bc8ced1ded1)
![allurefinal3](https://github.com/user-attachments/assets/2cde289b-8933-4327-a134-bf288d37fe99)

# Recorded Video of the Automation Process

https://github.com/user-attachments/assets/eee8bf8b-238f-40fe-bdae-9270c049849f

🛠️ Tech Stack
- Language: Java

- Automation Tool:Selenium

- Test Framework: TestNG

- Build Tool: Gradle

- Email Handling: JavaMail API 

- CSV Handling: Apache Commons CSV
  
- Terminal Input Handling: Java Console
  
- Design Pattern: Page Object Model (POM)

# How to run?
- Clone this repository from GitHub.
- git clone https://github.com/ShabitRony/Selenium-Automation-TestNG-Daily-FinanceApp.git
- Run the test( ./gradlew test)
- Install necessary dependencies from mvnrepository.
- After Automation generate Allure report.


