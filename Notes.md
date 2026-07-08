Q. delete entity or with id -> difference
Q. complete Mapping internal working 
Q. Because save() returns the managed entity. If the database generates an id, savedStudent contains it.
Q. how repository methods internall works
Q. @Column(nullable = false) v/s NotBlank
Q. Why validation requestdto rather entity
Q. Why admission year is Integer
Q. diff btw @LastModifiedDate @LastModifiedBy
Q. how complete student request dto with annotations internally works
Q. How complete student schema with annotations internall works
Q. what is Jpa Auditing
Q. diff btw boolean and Boolean 
Q. Apart from changing application yaml change database through queries in PgAdminGUI
Q. to use truncate ,drop to delte table 
Q.All ddl auto ..validate .. create..update
Q. learn when 401 unauthorized comes etc
Q. Spring Security is protecting all endpoints by default.
Q. when this comes
    "timestamp": "2026-07-07T14:09:05.069+00:00",

    "status": 500,

    "error": "Internal Server Error",

    "trace": "jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 
Q. Enum (Gender)
    Integer
    Long
    Boolean
    LocalDate
Q. how mapstruct will react with rersponse dto -> id and student entity has studentId     gave null
Q. students.stream().map(studentMapper::toResponseDto)
.toList();

Q.     "timestamp": "2026-07-08T06:42:42.795+00:00",
        "status": 405,
        "error": "Method Not Allowed",
        "trace": "org.springframework.web.
Q. http://localhost:8080/api/v1/student/roll/CBSE2026005

Q. complete ApiResponse internal working
Q. without ApiResponse vs With ApiResponse
Q. Already 8080 is in use
Q.   @ExceptionHandler(StudentAlreadyExistException.class)
    public ResponseEntity<String> studentAlreadyExistException(StudentAlreadyExistException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
     what result and why full stack trace?
Q. RestController Advice
Q. MethodArgumentNotValidException complete process
Q. How GlobalException works internally
Q.     public static <T> ApiResponse error(Object error,String message){   -> making it object from what difference and problem 
Q. why Auto increment id if you delete 2 ids in between then you have something like 1, 2, 3, 4, 7
Q. what is Audit fields
Q. Option 1: Roll number can never be reused (Most common for schools/universities) asroll number is a permanent identifier.
            // roll no is unique for every student even if he leaves the school


@ write manually queries for repositories