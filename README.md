# StudentMvcWebService
Student MVC Web Service realization
Релизация web Restful сервиса

Технологии используемые в проекте:
Front-end:
- HTML
- AJAX

Back-end:
- Spring boot
- Spring JPA
- Apache Tomcat
- Lombok

DB:
- h2 (in-memory)
- Liquibase

Тестирование:
- JUnit

Формат обмена данными:
- JSON

Сервис выполняет базовые CRUD операции над сущностью Student.
Во входящих/исходящих потоках обмена данными используются сущности DTO.

Описание методов контроллера StudentController
***
@GetMapping
public List<StudentDto> getAllStudents()

При вызове метода GET отдает JSON с перечнем всех сущностей StudentDto, преобразованных из бизнесс сущностей, хранимых в БД
***
@GetMapping("{id}")
public StudentDto getStudentById(@PathVariable Long id)

При вызове метода GET c указанием ID будет передан JSON с описанием сущности StudentDto, преобразованных из бизнесс сущности Student, запрошенной через ID
***
 @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
 public StudentDto addStudent(@RequestBody StudentDto studentDto)
 
 При вызове метода POST в теле которого будет описана сущность UserDTO формата:
{
    "firstName": "NewStudentF",
    "middleName": "NewStudentM",
    "lastName": "NewStudentL",
    "birthdayDate": "1990-12-12",
    "groupName": "group-1"
}

Сущность будет десериализованна в StudentDto, на основе которой будет сгенерирована бизнесс сущность Student и записана в БД
***
@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public StudentDto updateStudent(@RequestBody StudentDto studentDto)

При вызове метода PUT в теле которого будет описана сущность User формата:
{
        "id": 13,
        "firstName": "PalikoFEdit",
        "middleName": "PalikoFEdit",
        "lastName": "PalikoFEdit",
        "birthdayDate": "1991-12-12",
        "groupName": "group-2"
}

По переданному ID сущность будет перезаписана согласно переданному сообщению JSON
***
@DeleteMapping("{id}")
public void deleteStudent(@PathVariable Long id)

При вызове метода DELETE c указанием ID будет произведено удаление соответствующей сущности из БД
***
Приложение имеет веб интерфейс по умолчанию развернутое на стандартном порту - http://localhost:8080/?#

В Веб интерфейсе есть следующие возможности:
- Создавать новые записи
- Удалять записи
- Осуществлять поиск по заданному id
- Редактировать записчь по заданному id
