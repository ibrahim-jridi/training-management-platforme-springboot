package com.login.jwt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.jwt.dao.FormatterDao;
import com.login.jwt.dao.RoleDao;
import com.login.jwt.dao.UserDao;
import com.login.jwt.entity.Formatter;
import com.login.jwt.entity.Role;
import com.login.jwt.entity.User;
import com.login.jwt.service.UserService;
import com.login.jwt.util.ResourceNotFoundException;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class FormatterController {

	/*
	 * @Autowired private UserDao userDao;
	 * 
	 * @Autowired private RoleDao roleDao;
	 */
	 @Autowired
	  private FormatterDao formatterDao;
	 
	@Autowired
	private UserService userService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
	
	// get all formatter
	@GetMapping("/formatters")
	
		
		//Role formatterRole = new Role();
		public List<Formatter>  getAllFormatters(){
			return formatterDao.findAll();
		
		
	}	
	/*
	 * @GetMapping("/Formatters")
	 * 
	 * 
	 * //Role formatterRole = new Role(); public Iterable<User> getAllFormatterss(){
	 * return userDao.findAll();
	 * }
	 */
	
	// create formatter rest api and save it in formatterDao
	@PostMapping({"/formatterss"}) public Formatter registerNewFormatter(@RequestBody
			  Formatter formatter) { 
		formatter.setUserPassword(getEncodedPassword(formatter.getUserPassword()));
		
		return formatterDao.save(formatter);
			  }
	
	// create formatter rest api and save it in userDao
	@PostMapping({"/formatters"}) public User registerNewFormatter(@RequestBody
			  User formatter) { 
		return userService.registerNewFormatter(formatter);
		
			  }
	
	// get formatter by id rest api
	@GetMapping("/formatterss/{id}")
	public ResponseEntity<Formatter> getFormatterById(@PathVariable Long id) {
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		return ResponseEntity.ok(formatter);
	}
	
	// update formatter rest api
	
	@PutMapping("/formatterss/{id}")
	public ResponseEntity<Formatter> updateFormatter(@PathVariable Long id, @RequestBody Formatter formatterDetails){
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		
		formatter.setUserName(formatterDetails.getUserName());
		
		formatter.setUserFirstName(formatterDetails.getUserFirstName());
		formatter.setUserLastName(formatter.getUserLastName());
		formatter.setEmail(formatterDetails.getEmail());
		formatter.setImage(formatterDetails.getImage());
		formatter.setAdresse(formatterDetails.getAdresse());
		formatter.setSpecialite(formatterDetails.getSpecialite());
		
		Formatter updatedFormatter = formatterDao.save(formatter);
		return ResponseEntity.ok(updatedFormatter);
	}
	
	// delete formatter rest api
	@DeleteMapping("/formatterss/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFormatter(@PathVariable Long id){
		Formatter formatter = formatterDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("formatteur n'est pas trouvé avec ce id :" + id));
		
		formatterDao.delete(formatter);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}