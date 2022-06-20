class Acme {
    // ruleid: rest-PutMapping
    @PutMapping("/put")
    public @ResponseBody ResponseEntity<String> put() {

        return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
    }
}


@RestController
public class PUTRestController {
    @Autowired
    private FakultetRepository fakultetRepository;

    @Autowired
    private DepartmanRepository departmanRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StatusRepository statusRepository;

    // ruleid: rest-PutMapping
    @PutMapping("fakultet")
    @ApiOperation(value="Modifikuje postojeci fakultet u bazi podataka")
    public ResponseEntity<HttpStatus> updateFakultet(@RequestBody Fakultet fakultet){
        if(fakultetRepository.existsById(fakultet.getId())) {
            fakultetRepository.save(fakultet);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    // ruleid: rest-PutMapping
    @PutMapping("departman")
    @ApiOperation(value = "Modifikuje postojeci departman u bazi")
    public ResponseEntity<Departman> updateDepartman(@RequestBody Departman departman){
        if(!departmanRepository.existsById(departman.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        departmanRepository.save(departman);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ruleid: rest-PutMapping
    @PutMapping("status")
    @ApiOperation(value="Modifikuje postojeci status u bazu podataka")
    public ResponseEntity<Status> updateStatus(@RequestBody Status status){
        if(statusRepository.existsById(status.getId())) {
            statusRepository.save(status);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ruleid: rest-PutMapping
    @PutMapping("student")
    @ApiOperation(value="Modifikuje postojeceg studenta iz baze podataka")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student){
        if(!studentRepository.existsById(student.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        studentRepository.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
