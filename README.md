````md
# Proj-03

## Project Structure

```text
.
├── project_3
│   ├── doc
│   ├── pom.xml
│   ├── RemoteSystemsTempFiles
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── in/co/rays/project_3
│       │   │       ├── controller
│       │   │       ├── dto
│       │   │       ├── exception
│       │   │       ├── model
│       │   │       ├── test
│       │   │       └── util
│       │   ├── resources
│       │   │   ├── hibernate.cfg.xml
│       │   │   └── in/co/rays/project_3
│       │   │       ├── bundle
│       │   │       └── hbm
│       │   └── webapp
│       │       ├── doc
│       │       ├── img
│       │       ├── jasper
│       │       ├── js
│       │       ├── jsp
│       │       └── WEB-INF
│       └── test
│           ├── java
│           └── resources
└── README.md
````

---

# Modules

## Controller Layer

Contains all servlet controllers.

### Controllers

* BaseCtl.java
* ChangePasswordCtl.java
* CollegeCtl.java
* CollegeListCtl.java
* CourseCtl.java
* CourseListCtl.java
* DroneDeliveryCtl.java
* DroneDeliveryListCtl.java
* FacultyCtl.java
* FacultyListCtl.java
* ForgetPasswordCtl.java
* FrontController.java
* GetMarksheetCtl.java
* JasperCtl.java
* LoginCtl.java
* MarksheetCtl.java
* MarksheetListCtl.java
* ProductCtl.java
* ProductListCtl.java
* PodCastCtl.java
* PodCastListCtl.java
* RoleCtl.java
* RoleListCtl.java
* StudentCtl.java
* StudentListCtl.java
* SubjectCtl.java
* SubjectListCtl.java
* TimeTableCtl.java
* TimeTableListCtl.java
* UserCtl.java
* UserListCtl.java
* UserRegistrationCtl.java
* WelcomeCtl.java

---

# DTO Layer

Contains all entity classes and Hibernate mappings.

### DTO Classes

* CollegeDTO.java
* CourseDTO.java
* DroneDeliveryDTO.java
* FacultyDTO.java
* MarksheetDTO.java
* ProductDTO.java
* PodCastDTO.java
* RoleDTO.java
* SettingDTO.java
* StudentDTO.java
* SubjectDTO.java
* TimetableDTO.java
* UserDTO.java

---

# Model Layer

Contains JDBC and Hibernate implementations.

### Examples

* CollegeModelHibImp.java
* CollegeModelJDBCImpl.java
* CourseModelHibImp.java
* CourseModelJDBCImpl.java
* FacultyModelHibImp.java
* FacultyModelJDBCImpl.java
* MarksheetModelHibImp.java
* MarksheetModelJDBCImpl.java
* PodCastModelHibImpl.java
* StudentModelHibImp.java
* StudentModelJDBCImpl.java
* SubjectModelHibImp.java
* SubjectModelJDBCImpl.java
* UserModelHibImp.java
* UserModelJDBCImpl.java

---

# Utility Layer

Contains utility/helper classes.

### Utility Classes

* DataUtility.java
* DataValidator.java
* EmailBuilder.java
* EmailMessage.java
* EmailUtility.java
* HibDataSource.java
* JDBCDataSource.java
* PropertyReader.java
* ServletUtility.java

---

# JSP Views

Located inside:

```text
src/main/webapp/jsp
```

### JSP Pages

* LoginView.jsp
* Welcome.jsp
* UserView.jsp
* UserListView.jsp
* StudentView.jsp
* StudentListView.jsp
* RoleView.jsp
* RoleListView.jsp
* CourseView.jsp
* CourseListView.jsp
* SubjectView.jsp
* SubjectListView.jsp
* MarksheetView.jsp
* MarksheetListView.jsp
* ProductView.jsp
* ProductListView.jsp
* PodCastView.jsp
* PodCastListView.jsp

---

# Resources

## Hibernate Configuration

```text
src/main/resources/hibernate.cfg.xml
```

## HBM Mapping Files

```text
src/main/resources/in/co/rays/project_3/hbm
```

---

# Web Configuration

## web.xml

```text
src/main/webapp/WEB-INF/web.xml
```

---

# Technologies Used

* Java
* JSP
* Servlet
* Hibernate
* JDBC
* Maven
* MySQL
* Jasper Report
* HTML/CSS/JavaScript

---

# Maven Build

## pom.xml

```text
project_3/pom.xml
```

Build command:

```bash
mvn clean install
```

---

# Run Project

## Using Tomcat Server

1. Import project into Eclipse
2. Configure Tomcat
3. Right click project
4. Run As → Run on Server

---

# Notes

* Project follows MVC Architecture
* Separate layers for Controller, DTO, Model, Utility
* Supports Hibernate + JDBC implementation
* Includes Jasper Report support
* Includes Authentication and CRUD modules

```

Source structure extracted from uploaded file. :contentReference[oaicite:0]{index=0}
```
